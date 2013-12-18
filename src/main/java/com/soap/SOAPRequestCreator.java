package com.soap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.soap.DefaultValues.capHeaderFields;
import static com.soap.DefaultValues.commonParametersFields;
import static com.soap.DefaultValues.fieldsDirectlyToBody;
import static com.soap.DefaultValues.messageHeaderFields;


public class SOAPRequestCreator {

    SOAPEnvelope envelope;
    SOAPBody soapBody;
    SOAPMessage soapMessage;
    SOAPPart soapPart;
    SOAPElement getCalendarAndFlightAvailabilityRequestHeader;

    public SOAPRequestCreator() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        soapMessage = messageFactory.createMessage();
        soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tget", "http://www.ba.com/schema/availabilitycontroller/tGetCalendarAndFlightAvailabilityV1");

        // SOAP Body
        soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");

        getCalendarAndFlightAvailabilityRequestHeader = soapBody.addChildElement("getCalendarAndFlightAvailabilityRequestHeader");
    }

    public SOAPMessage createSOAPRequest(Map<String, String> mapFromFeatureFile) throws Exception {

        addDirectlyToBody(fieldsDirectlyToBody(), mapFromFeatureFile);
        soapMessage.saveChanges();
        updateHeader("MessageHeader", messageHeaderFields(), mapFromFeatureFile);
        soapMessage.saveChanges();
        updateHeader("CAPHeader", capHeaderFields(), mapFromFeatureFile);
        soapMessage.saveChanges();
        updateHeader("CommonParameters", commonParametersFields(), mapFromFeatureFile);
        soapMessage.saveChanges();
        // Check the input
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;

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

    public void addDirectlyToBody(List<String> defaultFields, Map<String, String> mapFromFeatureFile) throws SOAPException {

        Map<String, String> newMap = updateMapWithDefaultMissingValues(defaultFields, mapFromFeatureFile);

        SOAPElement soapElement = null;
        for (Map.Entry<String, String> entry : newMap.entrySet()) {
            soapElement = getCalendarAndFlightAvailabilityRequestHeader.addChildElement(entry.getKey());
            soapElement.addTextNode(entry.getValue());
        }
        soapMessage.saveChanges();
    }


    public void updateHeader(String headerName, List<String> defaultFields, Map<String, String> mapFromFeatureFile) throws SOAPException {
        SOAPElement soapBodyElem = getCalendarAndFlightAvailabilityRequestHeader.addChildElement(headerName);
        Map<String, String> newMap = updateMapWithDefaultMissingValues(defaultFields, mapFromFeatureFile);

        Map<String, SOAPElement> map2 = new HashMap<String, SOAPElement>();

        for(Map.Entry<String, String> entry : newMap.entrySet()){

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
        soapMessage.saveChanges();
    }


}
