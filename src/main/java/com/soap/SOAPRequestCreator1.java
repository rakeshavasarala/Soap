package com.soap;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.soap.DefaultValues.*;


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
        envelope.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");
        envelope.addNamespaceDeclaration("com", "http://www.iata.org/IATA/NDC/common");

        // SOAP Body
        soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");

        getCalendarAndFlightAvailabilityRequestHeader = soapBody.addChildElement("ndc:AirShoppingRQ");
    }

    public SOAPMessage createSOAPRequest() throws Exception {

        SOAPElement soapElement = soapBody.addChildElement("ndc:OriginDestination");

        soapMessage.saveChanges();
        // Check the input
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;

    }



}
