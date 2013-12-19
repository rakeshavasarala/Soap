package com.soap;

import javax.xml.namespace.QName;
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


public class SOAPRequestCreator1 {

    SOAPEnvelope envelope;
    SOAPBody soapBody;
    SOAPMessage soapMessage;
    SOAPPart soapPart;
    SOAPElement getCalendarAndFlightAvailabilityRequestHeader;

    public SOAPRequestCreator1() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        soapMessage = messageFactory.createMessage();
        soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        envelope = soapPart.getEnvelope();
        //envelope.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");

        // SOAP Body
        soapBody = envelope.getBody();
        //soapBody.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");

        // getCalendarAndFlightAvailabilityRequestHeader = soapBody.addChildElement("getCalendarAndFlightAvailabilityRequestHeader");
    }

    public SOAPMessage createSOAPRequest(/*Map<String, String> mapFromFeatureFile*/) throws Exception {


        SOAPElement airShoppingRQ = soapBody.addChildElement("AirShoppingRQ", "ndc", "http://www.iata.org/IATA/NDC");
        airShoppingRQ.addNamespaceDeclaration("com", "http://www.iata.org/IATA/NDC/common");

        SOAPElement travellerCount = airShoppingRQ.addChildElement("TravelerCount", "ndc");
        SOAPElement traveler = travellerCount.addChildElement("Traveler", "ndc");
        QName adtPaxType = new QName(/*"http://www.iata.org/IATA/NDC/common",*/ "PaxType"/*, "com"*/);
        traveler.addAttribute(adtPaxType, "ADT");
        traveler.addTextNode("2");

        SOAPElement traveler2 = travellerCount.addChildElement("Traveler", "ndc");
        QName chdPaxType = new QName("http://www.iata.org/IATA/NDC/common", "PaxType", "com");
        traveler2.addAttribute(chdPaxType, "CHD");
        traveler2.addTextNode("2");

        SOAPElement traveler3 = travellerCount.addChildElement("Traveler", "ndc");
        QName infPaxType = new QName("http://www.iata.org/IATA/NDC/common", "PaxType", "com");
        traveler3.addAttribute(infPaxType, "INF");
        traveler3.addTextNode("2");

        soapMessage.saveChanges();

        /*addDirectlyToBody(fieldsDirectlyToBody(), mapFromFeatureFile);
        soapMessage.saveChanges();
        updateHeader("MessageHeader", messageHeaderFields(), mapFromFeatureFile);
        soapMessage.saveChanges();
        updateHeader("CAPHeader", capHeaderFields(), mapFromFeatureFile);
        soapMessage.saveChanges();
        updateHeader("CommonParameters", commonParametersFields(), mapFromFeatureFile);
        soapMessage.saveChanges();*/
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
        soapMessage.saveChanges();
    }


}
