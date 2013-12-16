package com.soap;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import static com.soap.NEWSoapRequest.createSOAPRequest;
import static com.soap.NEWSoapResponse.printSOAPResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Rakesh
 * Date: 16/12/13
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    public static void main(String[] args) throws Exception {

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();


        String url = "http://capdev422:8001/WebserviceWebFB/availabilitycontroller/WSGetCalendarAndFlightAvailabilityV1";

        createSOAPRequest("MessageCreationDate");
        //createSOAPRequest1();
        //SOAPMessage soapResponse = soapConnection.call(createSOAPRequest("MessageCreationDate"), url);

        //printSOAPResponse(soapResponse);
        soapConnection.close();

    }

}
