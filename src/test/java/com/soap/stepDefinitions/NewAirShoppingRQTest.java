package com.soap.stepDefinitions;

import com.soap.models.request.OriginalDestination;
import com.soap.models.request.PricingRestriction;
import com.soap.models.request.Traveler;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rakesh
 * Date: 27/12/13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class NewAirShoppingRQTest {

    SOAPElement airShoppingRQ;
    SOAPMessage soapMessage;

    @Given("^default NDC AirShopping Request$")
    public void default_NDC_AirShopping_Request() throws Throwable {
        MessageFactory messageFactory = MessageFactory.newInstance();
        soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody soapBody = envelope.getBody();


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element airShoppingElement = doc.createElement("AirShoppingRQ");


        airShoppingRQ = soapBody.addChildElement("AirShoppingRQ", "ndc", "http://www.iata.org/IATA/NDC");
        airShoppingRQ.addNamespaceDeclaration("com", "http://www.iata.org/IATA/NDC/common");
    }

    @Given("^with Destination details$")
    public void with_Destination_details(List<OriginalDestination> originalDestinationList) throws Throwable {

        for (OriginalDestination originalDestination : originalDestinationList) {
            SOAPElement originalDestination1 = airShoppingRQ.addChildElement("OriginDestination", "ndc");
            // Departure
            SOAPElement departure1 = originalDestination1.addChildElement("Departure", "ndc");
            SOAPElement depatureCityCode1 = departure1.addChildElement("CityCode", "ndc");
            depatureCityCode1.addTextNode(originalDestination.getDepartureCityCode());
            // TravelDateTime
            SOAPElement travelDateTime1 = originalDestination1.addChildElement("TravelDateTime", "ndc");
            SOAPElement dateData1 = travelDateTime1.addChildElement("DateData", "ndc");
            SOAPElement date1 = dateData1.addChildElement("DateData", "ndc");
            date1.addTextNode(originalDestination.getDate());
            SOAPElement timeData1 = travelDateTime1.addChildElement("TimeData", "ndc");
            SOAPElement time1 = timeData1.addChildElement("Time", "ndc");
            time1.addTextNode(originalDestination.getTime());
            // Arrival
            for (String arrivalCityCode : originalDestination.getArrivalCityCodesAsList()) {
                SOAPElement arrival1 = originalDestination1.addChildElement("Arrival", "ndc");
                SOAPElement arrivalCityCode1 = arrival1.addChildElement("CityCode", "ndc");
                arrivalCityCode1.addTextNode(arrivalCityCode);
            }
            soapMessage.saveChanges();
        }
    }

    @Given("^with Travellers$")
    public void with_Traveller_Counts(List<Traveler> travellerCounts) throws Throwable {

        // Traveller Count
        SOAPElement travellerCount = airShoppingRQ.addChildElement("TravelerCount", "ndc");

        for (Traveler traveler : travellerCounts) {
            // traveler
            SOAPElement traveler1 = travellerCount.addChildElement("Traveler", "ndc");
            QName adtPaxType = new QName("http://www.iata.org/IATA/NDC/common", "PaxType", "com");
            traveler1.addAttribute(adtPaxType, traveler.getType());
            traveler1.addTextNode(traveler.getCount());
            soapMessage.saveChanges();
        }
    }

    @Given("^with cabin \"([^\"]*)\"$")
    public void with_cabin(String cabinValue) throws Throwable {
        // Attribute data
        SOAPElement attributeData = airShoppingRQ.addChildElement("AttributeData", "ndc");
        SOAPElement cabin = attributeData.addChildElement("Cabin", "ndc");
        cabin.addTextNode(cabinValue);
    }

    @Given("^with pricing restriction$")
    public void with_pricing_restriction(List<PricingRestriction> pricingRestrictionList) throws Throwable {

        SOAPElement pricingInfo = airShoppingRQ.addChildElement("PricingInfo", "ndc");
        for (PricingRestriction pricingRestriction : pricingRestrictionList) {
            SOAPElement restriction = pricingInfo.addChildElement("Restriction", "ndc");
            SOAPElement min = restriction.addChildElement("MinimumStay", "ndc");
            min.addTextNode(pricingRestriction.getMinimumStay());
            SOAPElement max = restriction.addChildElement("MaximumStay", "ndc");
            max.addTextNode(pricingRestriction.getMaximumStay());
            SOAPElement advance = restriction.addChildElement("Advance", "ndc");
            advance.addTextNode(pricingRestriction.getAdvance());
            SOAPElement penalty = restriction.addChildElement("Penalty", "ndc");
            penalty.addTextNode(pricingRestriction.getPenalty());
        }
        soapMessage.saveChanges();
    }

    private static DocumentBuilderFactory dbf = DocumentBuilderFactory
            .newInstance();

    @Given("^NDC Air Shopping Request is build with above options$")
    public void NDC_Air_Shopping_Request_is_build_with_above_options() throws Throwable {

        soapMessage.saveChanges();

        System.out.println("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        String content = soapMessage.getContentDescription();
        System.out.println("Content = \n" + content);

        Document document = soapMessage.getSOAPBody().extractContentAsDocument();

        //System.out.println(document.toString());

        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation domImpl = db.getDOMImplementation();
        DOMImplementationLS ls = (DOMImplementationLS) domImpl;
        LSSerializer lss = ls.createLSSerializer();
        /*LSOutput lso = ls.createLSOutput();
        lso.setByteStream(System.out);
        lss.write(document, lso);*/

        String documentAsString = lss.writeToString(document);
        String docStrings[] = documentAsString.split("\n");
        System.out.println("\n whole documentAsString = \n" + documentAsString);
        System.out.println("\n documentAsString without xml declaration = \n" + docStrings[1]);


    }

    @When("^the NDC Air Shopping Request is passed through schema$")
    public void the_NDC_Air_Shopping_Request_is_passed_through_schema() throws Throwable {

    }
}
