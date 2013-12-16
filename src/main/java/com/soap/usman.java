package com.soap;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;


public class usman {

    private SOAPConnection soapConnection;
    private SOAPMessage soapResponse;

  /*  @Given("^I have a request$")
    public void I_have_a_request() throws Throwable {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        soapConnection = soapConnectionFactory.createConnection();
    }

    @When("^the request is posted$")
    public void the_request_is_posted() throws Throwable {
        //Send SOAP Message to SOAP Server
        String url = "http://capdev422:8001/WebserviceWebFB/availabilitycontroller/WSGetCalendarAndFlightAvailabilityV1";
        soapResponse = soapConnection.call(createSOAPRequest("MessageCreationDate"), url);
    }

    @Then("^I should get back a valid response$")
    public void I_should_get_back_a_valid_response() throws Throwable {
        // Process the SOAP Response
        printSOAPResponse(soapResponse);

        soapConnection.close();
    }*/

}