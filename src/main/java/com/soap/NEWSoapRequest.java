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

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration("ndc", "http://www.iata.org/IATA/NDC");
        SOAPElement soapBodyElem = soapBody.addChildElement("MessageHeader");


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

        SOAPElement soapBodyElementCAPHeader = soapBody.addChildElement("CAPHeader");
        SOAPElement sessionID = soapBodyElementCAPHeader.addChildElement("SessionID");
        sessionID.addTextNode("?");

        SOAPElement parameters = soapBodyElementCAPHeader.addChildElement("Parameters");
        SOAPElement name = parameters.addChildElement("ParameterName");
        name.addTextNode("?");
        SOAPElement value = parameters.addChildElement("ParameterValue");
        value.addTextNode("?");


        soapMessage.saveChanges();

        // Check the input
        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;

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