package com.soap.stepDefinitions;


import com.soap.builders.SOAPRequestBuilder;
import cucumber.api.java.en.Given;

import java.util.HashMap;
import java.util.Map;

import static com.soap.DefaultValues.capHeaderFields;
import static com.soap.DefaultValues.commonParametersFields;
import static com.soap.DefaultValues.fieldsDirectlyToBody;
import static com.soap.DefaultValues.messageHeaderFields;

public class SoapTest {

    private static final String PARENT_ELEMENT = "tget:getCalendarAndFlightAvailabilityRequestHeader";

    @Given("^I have a request$")
    public void I_have_a_request(String requestString) throws Throwable {

        String[] lines = requestString.split("\n");
        String[] columnNames = new String[lines.length];
        String[] values = new String[lines.length];

        Map<String, String> map = new HashMap();

        for (int i = 0; i < lines.length; i++) {
            String[] nameValuePair = lines[i].split(":");
            map.put(nameValuePair[0].trim(), values[i] = nameValuePair[1].trim());
        }

        // new SOAPRequestCreator().createSOAPRequest(map);

        new SOAPRequestBuilder()
                .SOAPEnvelopeNamespace("tget", "http://www.ba.com/schema/availabilitycontroller/tGetCalendarAndFlightAvailabilityV1")
                .SOAPBodyNamespace("ndc", "http://www.iata.org/IATA/NDC")
                .addOrUpdateHeader(null, PARENT_ELEMENT, fieldsDirectlyToBody(), map)
                .addOrUpdateHeader(PARENT_ELEMENT, "MessageHeader", messageHeaderFields(), map)
                .addOrUpdateHeader(PARENT_ELEMENT, "CAPHeader", capHeaderFields(), map)
                .addOrUpdateHeader(PARENT_ELEMENT, "CommonParameters", commonParametersFields(), map)
                .saveChanges()
                .print();


    }

}
