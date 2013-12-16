package com.soap;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Map;


public class DefaultSOAPRequest {


    public SOAPMessage createSOAPRequest(Map<String, String> map) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tget", "http://www.ba.com/schema/availabilitycontroller/tGetCalendarAndFlightAvailabilityV1");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");

        updateMessageHeader(soapBody, map);


        soapMessage.saveChanges();

        // Check the input
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;

    }

    private Map<String, String> getMessageHeaderDefaultsAsMap() {
        Map<String, String> map = new HashMap();

        map.put("MessageCreationTime", "?");
        map.put("OriginatingSystem", "?");
        map.put("OriginatingSystem", "?");
        map.put("OriginatingUser", "?");
        map.put("OriginatingTerminal", "?");
        map.put("OriginatingTransaction", "?");
        map.put("MessageSequenceNumber", "?");
        map.put("MessageBatchNumber", "?");
        map.put("TotalMessagesInBatch", "?");
        map.put("MessageSequenceWithinBatch", "?");
        map.put("Version", "?");
        map.put("ResponseTime", "?");


        return map;
    }


    public SOAPElement updateMessageHeader(SOAPBody soapBody, Map<String, String> map) throws SOAPException {
        SOAPElement soapBodyElem = soapBody.addChildElement("MessageHeader");

        Map<String, String> newMap = getMessageHeaderDefaultsAsMap();
        newMap.putAll(map);

        for(Map.Entry<String, String> entry : newMap.entrySet()){
            SOAPElement soapElement = soapBodyElem.addChildElement(entry.getKey());
            soapElement.addTextNode(entry.getValue());
        }
        return soapBodyElem;
    }

}
