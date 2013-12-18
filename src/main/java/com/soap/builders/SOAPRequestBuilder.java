package com.soap.builders;


import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SOAPRequestBuilder {

    SOAPEnvelope envelope;
    SOAPBody soapBody;
    SOAPMessage soapMessage;
    SOAPPart soapPart;

    Map<String, SOAPElement> parentElements;

    public SOAPRequestBuilder() throws SOAPException {
        parentElements = new HashMap<>();

        MessageFactory messageFactory = MessageFactory.newInstance();
        soapMessage = messageFactory.createMessage();
        soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        envelope = soapPart.getEnvelope();
    }

    public SOAPRequestBuilder SOAPEnvelopeNamespace(String prefix, String uri) throws SOAPException {
        envelope.addNamespaceDeclaration(prefix, uri);
        return this;
    }

    public SOAPRequestBuilder SOAPBodyNamespace(String prefix, String uri) throws SOAPException {
        soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration(prefix, uri);
        return this;
    }

    public SOAPMessage getSoapMessage() {
        return soapMessage;
    }

    /**
     * all-in-1 Function
     *
     * @param parentElement      If Parent Element is null then will be added to soapBody
     * @param headerName
     * @param defaultFields
     * @param mapFromFeatureFile
     * @return
     * @throws SOAPException
     */
    public SOAPRequestBuilder addOrUpdateHeader(String parentElement, String headerName, List<String> defaultFields, Map<String, String> mapFromFeatureFile) throws SOAPException {

        if (null == parentElement) {
            return addElementHeaderToSOAPBody(headerName)
                    .addTextElementsToHeader(headerName, defaultFields, mapFromFeatureFile);
        }


        SOAPElement parentSOAPElement = parentElements.get(parentElement);

        if (null != parentSOAPElement) {

            SOAPElement soapBodyElem = parentSOAPElement.addChildElement(headerName);
            Map<String, String> newMap = updateMapWithDefaultMissingValues(defaultFields, mapFromFeatureFile);

            Map<String, SOAPElement> map2 = new HashMap<String, SOAPElement>();

            for (Map.Entry<String, String> entry : newMap.entrySet()) {

                String key = entry.getKey();

                if (key.contains("->")) {
                    String[] keys = key.split("->");
                    SOAPElement currentElement;
                    SOAPElement prevElement = null;
                    String tempKey = null;
                    for (int i = 0; i < keys.length; i++) {

                        if (null == tempKey) {
                            tempKey = keys[i];
                        } else {
                            tempKey = tempKey + "->" + keys[i];
                        }
                        currentElement = map2.get(tempKey);

                        if (i == 0) {
                            if (null == currentElement) {
                                currentElement = soapBodyElem.addChildElement(keys[i]);
                                map2.put(tempKey, currentElement);
                            }
                        } else {
                            if (null != prevElement && null == currentElement) {
                                currentElement = prevElement.addChildElement(keys[i]);
                                map2.put(tempKey, currentElement);
                            }
                        }

                        prevElement = currentElement;

                        if (i == keys.length - 1) {
                            if (entry.getKey().endsWith(keys[i])) {
                                currentElement.addTextNode(newMap.get(entry.getKey()));
                            }
                        }
                    }
                } else {
                    SOAPElement soapElement = soapBodyElem.addChildElement(entry.getKey());
                    soapElement.addTextNode(entry.getValue());
                }
            }
        } else {
            throw new UnknownError("Parent Element [" + parentElement + "] has not been added to the SOAP yet.");
        }
        soapMessage.saveChanges();
        return this;
    }

    public SOAPRequestBuilder addElementHeaderToSOAPBody(String header) throws SOAPException {
        SOAPElement soapElement = soapBody.addChildElement(header);
        parentElements.put(header, soapElement);
        soapMessage.saveChanges();
        return this;
    }

    public SOAPRequestBuilder saveChanges() throws SOAPException {
        soapMessage.saveChanges();
        return this;
    }

    public void print() throws IOException, SOAPException {
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
    }


    private Map<String, String> updateMapWithDefaultMissingValues(List<String> defaultFields, Map<String, String> map) {
        Map<String, String> newMap = new HashMap<String, String>();

        for (String field : defaultFields) {
            String value = map.get(field);
            if (null != value) {
                newMap.put(field, value);
            } else {
                boolean found = false;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (field.endsWith(entry.getKey())) {
                        String value1 = entry.getValue();
                        found = true;
                        if (null != value1) {
                            newMap.put(field, value1);
                        } else {
                            newMap.put(field, "?");
                        }
                    } else {
                        if (!found) {
                            newMap.put(field, "?");
                        }
                    }
                }
            }
        }

        return newMap;
    }

    private SOAPRequestBuilder addTextElementsToHeader(String parentElement, List<String> defaultFields, Map<String, String> mapFromFeatureFile) throws SOAPException {

        Map<String, String> newMap = updateMapWithDefaultMissingValues(defaultFields, mapFromFeatureFile);

        SOAPElement soapElement = null;
        for (Map.Entry<String, String> entry : newMap.entrySet()) {
            soapElement = parentElements.get(parentElement).addChildElement(entry.getKey());
            soapElement.addTextNode(entry.getValue());
        }
        soapMessage.saveChanges();
        return this;
    }


}
