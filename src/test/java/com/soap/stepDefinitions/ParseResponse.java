package com.soap.stepDefinitions;

import com.soap.StringToSOAPUtils;
import com.soap.models.response.AvailabilityRequest;
import com.soap.models.response.OriginalDestionationOption;
import com.sun.deploy.util.StringUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPMessage;
import java.io.File;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ParseResponse {

    String pathToFile = "src/main/resources/xmls/resposeXml.xml";
    String defaultSOAPMessageString;
    SOAPMessage soapMessage;
    Document document;
    AvailabilityRequest availabilityRequest;
    Map<String, String> map = new HashMap<>();

    @Given("^read flight availability response from file$")
    public void read_flight_availability_response_from_file() throws Throwable {
        List<String> lines = Files.readAllLines(Paths.get(new File(".").getCanonicalPath(), pathToFile), Charset.defaultCharset());
        defaultSOAPMessageString = StringUtils.join(lines, "\n");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(defaultSOAPMessageString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DocumentBuilderFactory dbf = DocumentBuilderFactory
            .newInstance();

    @Given("^convert it into AvailabilityRequest$")
    public void convert_it_into_AvailabilityRequest() throws Throwable {

        availabilityRequest = new AvailabilityRequest();


        Node firstNode = document.getFirstChild();
        NodeList nodeList = firstNode.getChildNodes();


        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                System.out.println(nodeList.item(i).getNodeType() + " = " + nodeList.item(i).getNodeName());
                if (nodeList.item(i).getChildNodes().getLength() == 1) {
                    //  System.out.println(nodeList.item(i).getNodeType() + " = " + nodeList.item(i).getTextContent());
                    map.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
                }

                if (nodeList.item(i).getNodeName().equals("CommonParameters")) {
                    parseCommonParameters(nodeList.item(i));
                }
            }
        }

        availabilityRequest.setMap(map);


    }


    private void parseCommonParameters(Node node) {

        getTextNodes(node);
        NodeList nodeList = node.getChildNodes();
        List<OriginalDestionationOption> originalDestionationOptions = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals("OriginDestinationOption")) {
                originalDestionationOptions.add(parseOriginalDestinationOption(nodeList.item(i)));
            }

            if (nodeList.item(i).getNodeName().equals("PassengerCount")) {
                getTextNodes(nodeList.item(i));
            }

            if (nodeList.item(i).getNodeName().equals("CabinRestriction")) {
                getTextNodes(nodeList.item(i));
            }

        }

        availabilityRequest.setOriginDestinationOptions(originalDestionationOptions);

    }

    private void getTextNodes(Node node) {

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                System.out.println(nodeList.item(i).getNodeType() + " = " + nodeList.item(i).getNodeName());
                if (nodeList.item(i).getChildNodes().getLength() == 1) {
//                      System.out.println(nodeList.item(i).getNodeType() + " = " + nodeList.item(i).getTextContent());
                    map.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
                }
            }
        }
    }

   /* private void parseCabinRestriction(Node node) {

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                System.out.println(nodeList.item(i).getNodeType() + " = " + nodeList.item(i).getNodeName());
                if (nodeList.item(i).getChildNodes().getLength() == 1) {
//                      System.out.println(nodeList.item(i).getNodeType() + " = " + nodeList.item(i).getTextContent());
                    map.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
                }
            }
        }
    }*/


    private OriginalDestionationOption parseOriginalDestinationOption(Node node) {

        OriginalDestionationOption originalDestionationOption = new OriginalDestionationOption();
        NodeList nodeList = node.getChildNodes();


        for (int i = 0; i < nodeList.getLength(); i++) {

            if (nodeList.item(i).getNodeName().equals("OriginLocationCode")) {
                originalDestionationOption.setOriginLocationCode(nodeList.item(i).getTextContent());
            }
            if (nodeList.item(i).getNodeName().equals("OriginDateTime")) {
                originalDestionationOption.setOriginDateTime(nodeList.item(i).getTextContent());
            }
            if (nodeList.item(i).getNodeName().equals("DestinationLocationCode")) {
                originalDestionationOption.setDestinationLocationCode(nodeList.item(i).getTextContent());
            }


        }

        return originalDestionationOption;
    }


    @Then("^response has following original destination options$")
    public void response_has_following_original_destination_options(List<OriginalDestionationOption> originalDestionationOptions) throws Throwable {

       for(int i=0; i < originalDestionationOptions.size(); i++ ) {
         assertEquals(originalDestionationOptions.get(i).getDestinationLocationCode(), availabilityRequest.getOriginDestinationOptions().get(i).getDestinationLocationCode());
         assertEquals(originalDestionationOptions.get(i).getOriginDateTime(), availabilityRequest.getOriginDestinationOptions().get(i).getOriginDateTime());
         assertEquals(originalDestionationOptions.get(i).getOriginLocationCode(), availabilityRequest.getOriginDestinationOptions().get(i).getOriginLocationCode());
       }

    }

    @Then("^response has following details$")
    public void response_has_following_details(String request) throws Throwable {
        StringToSOAPUtils stringToSOAPUtils = new StringToSOAPUtils();
        Map<String, String> expectedMap = stringToSOAPUtils.getFeatureFileRequestAsMap(request);

        // Use the Map Matchers to assert that availabilityRequest.getMap() == expectedMap

    }

}
