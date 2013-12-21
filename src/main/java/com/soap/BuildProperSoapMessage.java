package com.soap;

import org.w3c.dom.*;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class BuildProperSoapMessage {


    public static void main(String[] args) throws SOAPException, IOException, TransformerException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody soapBody = envelope.getBody();


        SOAPElement airShoppingRQ = soapBody.addChildElement("AirShoppingRQ", "ndc", "http://www.iata.org/IATA/NDC");
        airShoppingRQ.addNamespaceDeclaration("com", "http://www.iata.org/IATA/NDC/common");
        SOAPElement originalDestination1 = airShoppingRQ.addChildElement("OriginDestination", "ndc");
        // Departure
        SOAPElement departure1 = originalDestination1.addChildElement("Departure","ndc");
        SOAPElement depatureCityCode1 = departure1.addChildElement("CityCode", "ndc");
        depatureCityCode1.addTextNode("?");
        // TravelDateTime
        SOAPElement travelDateTime1 = originalDestination1.addChildElement("TravelDateTime","ndc");
        SOAPElement dateData1 = travelDateTime1.addChildElement("DateData", "ndc");
        SOAPElement date1 = dateData1.addChildElement("DateData", "ndc");
        date1.addTextNode("?");
        SOAPElement timeData1 = travelDateTime1.addChildElement("TimeData", "ndc");
        SOAPElement time1 = timeData1.addChildElement("Time", "ndc");
        time1.addTextNode("?");
        // Arrival
        SOAPElement arrival1 = originalDestination1.addChildElement("Arrival", "ndc");
        SOAPElement arrivalCityCode1 = arrival1.addChildElement("CityCode", "ndc");
        arrivalCityCode1.addTextNode("?");


        SOAPElement originalDestination2 = airShoppingRQ.addChildElement("OriginDestination", "ndc");
        // Departure
        SOAPElement departure2 = originalDestination2.addChildElement("Departure","ndc");
        SOAPElement depatureCityCode2 = departure2.addChildElement("CityCode", "ndc");
        depatureCityCode2.addTextNode("?");
        // TravelDateTime
        SOAPElement travelDateTime2 = originalDestination2.addChildElement("TravelDateTime","ndc");
        SOAPElement dateData2 = travelDateTime2.addChildElement("DateData", "ndc");
        SOAPElement date2 = dateData2.addChildElement("DateData", "ndc");
        date1.addTextNode("?");
        SOAPElement timeData2 = travelDateTime2.addChildElement("TimeData", "ndc");
        SOAPElement time2 = timeData2.addChildElement("Time", "ndc");
        time2.addTextNode("?");
        // Arrival
        SOAPElement arrival2 = originalDestination2.addChildElement("Arrival", "ndc");
        SOAPElement arrivalCityCode2 = arrival2.addChildElement("CityCode", "ndc");
        arrivalCityCode2.addTextNode("?");



        // Traveller Count
        SOAPElement travellerCount = airShoppingRQ.addChildElement("TravelerCount", "ndc");
        // traveler 1
        SOAPElement traveler1 = travellerCount.addChildElement("Traveler", "ndc");
        QName adtPaxType = new QName("http://www.iata.org/IATA/NDC/common", "PaxType", "com");
        traveler1.addAttribute(adtPaxType, "ADT");
        traveler1.addTextNode("?");
        // traveler 2
        SOAPElement traveler2 = travellerCount.addChildElement("Traveler", "ndc");
        QName chdPaxType = new QName("http://www.iata.org/IATA/NDC/common", "PaxType", "com");
        traveler2.addAttribute(chdPaxType, "CHD");
        traveler2.addTextNode("?");
        // traveler 2
        SOAPElement traveler3 = travellerCount.addChildElement("Traveler", "ndc");
        QName infPaxType = new QName("http://www.iata.org/IATA/NDC/common", "PaxType", "com");
        traveler3.addAttribute(infPaxType, "INF");
        traveler3.addTextNode("?");

        // Attribute data

        SOAPElement attributeData = airShoppingRQ.addChildElement("AttributeData", "ndc");
        SOAPElement cabin = attributeData.addChildElement("Cabin", "ndc");
        cabin.addTextNode("?");

        soapMessage.saveChanges();

        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();



        // Start to

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapMessage.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
        System.out.println("");

        SOAPPart soapPart1 = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope1 = soapPart1.getEnvelope();
        SOAPBody soapBody1 = soapEnvelope1.getBody();

        /*System.out.println("\ngetNodeType() = " + soapBody1.getFirstChild().getNodeType());
        System.out.println("localName = " +soapBody1.getFirstChild().getLocalName());
        System.out.println("nodeName = " + soapBody1.getFirstChild().getNodeName());

        System.out.println("getChildNodes().getLength() = " + soapBody1.getChildNodes().getLength());

        System.out.println("\ngetNodeType() = " + soapBody1.getLastChild().getNodeType());
        System.out.println("localName = " +soapBody1.getLastChild().getLocalName());
        System.out.println("nodeName = " + soapBody1.getLastChild().getNodeName());
*/

        Iterator<javax.xml.soap.Node> iterator =  soapBody1.getChildElements();

        if(iterator.hasNext()) {
            System.out.println(iterator.next().getNodeName());
        }

    }


    private static void printElements() {



    }


}
