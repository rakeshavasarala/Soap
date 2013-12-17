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
import static com.soap.DefaultValues.messageHeaderFields;


public class SOAPRequestCreator {

    SOAPEnvelope envelope;
    SOAPBody soapBody;
    SOAPMessage soapMessage;
    SOAPPart soapPart;

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
    }

    public SOAPMessage createSOAPRequest(Map<String, String> map) throws Exception {


        //updateMessageHeader(map);
        updateHeader("MessageHeader", messageHeaderFields(), map);
        soapMessage.saveChanges();
        updateHeader("CAPHeader", capHeaderFields(), map);
        soapMessage.saveChanges();

        // Check the input
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;

    }

    public SOAPElement updateHeader(String headerName, List<String> defaultFields, Map<String, String> map) throws SOAPException {
        SOAPElement soapBodyElem = soapBody.addChildElement(headerName);

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

        Map<String, SOAPElement> map2 = new HashMap<String, SOAPElement>();

        for(Map.Entry<String, String> entry : newMap.entrySet()){

            String key = entry.getKey();

            if (key.contains("->")) {
                String[] keys = key.split("->");
                SOAPElement currentElement = null;
                SOAPElement prevElement = null;
                String tempKey = null;
                for (int i = 0; i < keys.length; i++) {

                    if (null == tempKey) {
                        tempKey = keys[i];
                    } else {
                        tempKey = tempKey + "->" + keys[i];
                    }


                    if (i == 0) {
                        currentElement = map2.get(tempKey);
                        if (null == currentElement) {
                            currentElement = soapBodyElem.addChildElement(keys[i]);
                            map2.put(tempKey, currentElement);
                        }
                        prevElement = currentElement;

                    } else {
                        currentElement = map2.get(tempKey);
                        if (null != prevElement && null == currentElement) {
                            currentElement = prevElement.addChildElement(keys[i]);
                            map2.put(tempKey, currentElement);
                        }
                        prevElement = currentElement;
                    }
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
        return soapBodyElem;
    }

}
