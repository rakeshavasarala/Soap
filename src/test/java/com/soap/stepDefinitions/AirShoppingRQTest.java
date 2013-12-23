package com.soap.stepDefinitions;

import com.soap.DefaultValues;
import com.soap.StringToSOAPUtils;
import com.sun.deploy.util.StringUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import javax.xml.soap.SOAPMessage;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class AirShoppingRQTest {

    String pathToFile = "src/main/resources/xmls/IntSOAPRQ_Default.xml";
    String defaultSOAPMessageString;

    /**
     * Reads the xml from 'pathToFile' as String
     * @throws Throwable
     */
    @Given("^I have a default AirShopping Request$")
    public void I_have_a_default_AirShopping_Request() throws Throwable {
        List<String> lines = Files.readAllLines(Paths.get(new File(".").getCanonicalPath(), pathToFile), Charset.defaultCharset());
        defaultSOAPMessageString = StringUtils.join(lines, "\n");
    }

    /**
     *
     * @param request
     * @throws Throwable
     */
    @When("^I replace the following values in the Request$")
    public void I_replace_the_following_values_in_the_Request(String request) throws Throwable {
        StringToSOAPUtils stringToSOAPUtils = new StringToSOAPUtils();
        // Gets the default fields for this xml
        Map<String, String> map = DefaultValues.airShoppingDefaultValues();
        // Read the field and values from feature file and merge with default values
        map.putAll(stringToSOAPUtils.getFeatureFileRequestAsMap(request));
        // Update the xml request with values read from feature file (if none provided then with default values)
        String updatedRequest = stringToSOAPUtils.replaceValues(defaultSOAPMessageString, map);
        // Make the SOAPMessage
        SOAPMessage soapMessage = stringToSOAPUtils.getSOAPMessageFromString(updatedRequest);
        soapMessage.writeTo(System.out);
    }
}
