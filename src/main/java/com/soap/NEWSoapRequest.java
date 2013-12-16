package com.soap;


import javax.xml.soap.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class NEWSoapRequest {

    private static Properties prop = new Properties();
    private static File baseUserDir = new File(System.getProperty("user.dir"));
    public static File availabilityRequestFile = new File(baseUserDir + "/main/resources/testData/xml/availabilityRequest.properties");
    public static File xmlPropertiesFile = new File(baseUserDir + "/main/resources/testData/xml/request.properties");

    //Method used to create the SOAP Request
    public static SOAPMessage createSOAPRequest(String messageCreationDate) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tget", "http://www.ba.com/schema/availabilitycontroller/tGetCalendarAndFlightAvailabilityV1");

        // SOAP Header
//THIS IS THE ONE----->>>>>        SOAPHeader soapHeader = envelope.getHeader();
//        soapHeader.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");
//        soapHeader.addNamespaceDeclaration("com", "http://www.iata.org/IATA/NDC/common");
//THIS IS THE ONE----->>>>>       soapHeader.addNamespaceDeclaration("ndc", "AirshopperRQ");
//        SOAPHeaderElement headerElement = soapHeader.addHeaderElement(envelope.createName("AirshopperRQ","","http://www.iata.org/IATA/NDC"));

//        SOAPHeaderElement headerElement = soapHeader.addHeaderElement(envelope.createName(
//                "Signature", "http://www.ba.com/schema/availabilitycontroller/tGetCalendarAndFlightAvailabilityV1", "http://schemas.xmlsoap.org/soap/security/2000-12"));

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
//        soapBody.addNamespaceDeclaration("ndc", "AirshopperRQ");
        soapBody.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");
//THIS IS THE ONE----->>>>>     soapHeader.addNamespaceDeclaration("com", "http://www.iata.org/IATA/NDC/common");
        SOAPElement soapBodyElem = soapBody.addChildElement("MessageHeader");
//        if (messageCreationDate.equals("MessageCreationDate")); {
//            SOAPElement soapBodyElem1 = soapBodyElem.addChildElement(messageCreationDate);
//        } else if (messageCreationDate.equals(null)); {
//            System.out.println("THIS IS NOT NEEDED!!!!");
//        }

        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement(messageCreationDate);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("MessageCreationTime");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("OriginatingSystem");
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("OriginatingUser");
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("OriginatingTerminal");
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("OriginatingTransaction");
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("MessageSequenceNumber");
        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("MessageBatchNumber");
        SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("TotalMessagesInBatch");
        SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("MessageSequenceWithinBatch");
        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("Version");
        SOAPElement soapBodyElem12 = soapBodyElem.addChildElement("ResponseTime");

        soapBodyElem1.addTextNode("?");
        soapBodyElem2.addTextNode("?");
        soapBodyElem3.addTextNode("?");
        soapBodyElem4.addTextNode("?");
        soapBodyElem5.addTextNode("?");
        soapBodyElem6.addTextNode("?");
        soapBodyElem7.addTextNode("?");
        soapBodyElem8.addTextNode("?");
        soapBodyElem9.addTextNode("?");
        soapBodyElem10.addTextNode("?");
        soapBodyElem11.addTextNode("?");
        soapBodyElem12.addTextNode("?");

        soapMessage.saveChanges();

        // Check the input
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;


//        MessageFactory messageFactory = MessageFactory.newInstance();
//        SOAPMessage message = messageFactory.createMessage();
//        SOAPPart soapPart = message.getSOAPPart();
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        SOAPHeader soapHeader = envelope.getHeader();
//        SOAPBody body = envelope.getBody();
//
//        //soapHeader.detachNode();
//        envelope.getNamespacePrefixes();
//
//        // Create a SOAPHeaderElement
//        SOAPHeaderElement headerElement = soapHeader.addHeaderElement(envelope.createName("AirshopperRQ","","http://www.iata.org/IATA/NDC"));
//        headerElement.addNamespaceDeclaration("ndc","http://www.iata.org/IATA/NDC/common");
//
//        SOAPElement header1 = headerElement.addChildElement(envelope.createName("OriginDestination"));
//
//        SOAPElement headerChild1 = header1.addChildElement(envelope.createName("Departure"));
//        SOAPElement headerGrantChild1 = headerChild1.addChildElement("CityCode");
////        headerGrantChild1.addNamespaceDeclaration("ndc","http://www.iata.org/IATA/NDC/common");
//        headerGrantChild1.addTextNode("LHR");
////        Name p1=envelope.createName("ndc");
////        headerGrantChild1.addAttribute(p1, "LHR");
//
//        SOAPElement headerChild2 = header1.addChildElement(envelope.createName("TravelDateTime"));
//        SOAPElement headerChild3 = headerChild2.addChildElement(envelope.createName("DateData"));
//        SOAPElement headerGrantChild3 = headerChild3.addChildElement("Date");
//        Name p2=envelope.createName("ndc");
//        headerGrantChild3.addAttribute(p2, "2013-11-30");
//
//        SOAPElement header2 = headerElement.addChildElement(envelope.createName("requestID"));
//        SOAPElement header2Child = header2.addChildElement("substitute");
//        Name p3=envelope.createName("property");
//        header2Child.addAttribute(p3, "requestId");
//
//        // Create a SOAPBodyElement
//        Name bodyName = envelope.createName("netw.Network", "", "xmlapi_1.0");
//        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
//
//        // Insert Content
//        Name name = envelope.createName("instanceFullName");
//        SOAPElement symbol = bodyElement.addChildElement(name);
//        symbol.addTextNode("network:");
//
//        Name prop = envelope.createName("substitute");
//        SOAPElement prop1 = symbol.addChildElement(prop);
//        Name property=envelope.createName("property");
//        prop1.addAttribute(property, "ipAddress");
//
//        Name name1 = envelope.createName("command");
//        SOAPElement symbol1= bodyElement.addChildElement(name1);
//        symbol1.addTextNode("command name");
//
//
//        Name prop2 = envelope.createName("substitute");
//        SOAPElement prop3 = symbol1.addChildElement(prop2);
//        Name property1=envelope.createName("property");
//        prop3.addAttribute(property1, "Id1");
//
//        symbol1.addTextNode("text goes here");
//
//        Name prop4= envelope.createName("substitute");
//        SOAPElement prop5 = symbol1.addChildElement(prop4);
//        Name property2=envelope.createName("property");
//        prop5.addAttribute(property2, "id2");
//
//
//        // Create an endpint point which is either URL or String type
//        URL endpoint = new URL("http://localhost:8080/CreateSoap");
//
//        // Check the input
//        System.out.println("\nSOAP REQUEST:\n");
//        message.writeTo(System.out);
//        System.out.println();
//        return message;
    }

    public static String getRequestDetails(String requestDetail, String detail, File requestFile) {
        try {
            prop.load(new FileInputStream(requestFile));
            requestDetail = prop.getProperty(detail);
//            System.out.println("*****DETAIL IS " + requestDetail);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return requestDetail;
    }

}