package com.soap.stepDefinitions;


import com.soap.DefaultSOAPRequest;
import cucumber.api.java.en.Given;

import java.util.HashMap;
import java.util.Map;

public class SoapTest {

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

        new DefaultSOAPRequest().createSOAPRequest(map);

    }

}
