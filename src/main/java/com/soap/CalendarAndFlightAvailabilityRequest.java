package com.soap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 * Created by IntelliJ IDEA.
 * User: Rakesh Avasarala
 * Date: 12/18/13
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarAndFlightAvailabilityRequest {

    SOAPEnvelope envelope;
    SOAPBody soapBody;
    SOAPMessage soapMessage;
    SOAPPart soapPart;
    SOAPElement getCalendarAndFlightAvailabilityRequestHeader;

    public CalendarAndFlightAvailabilityRequest() throws SOAPException {
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

}
