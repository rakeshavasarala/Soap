package com.soap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class StringToSOAPUtils {


    public Map<String, String> getFeatureFileRequestAsMap(String request) {
        String[] lines = request.split("\n");
        String[] columnNames = new String[lines.length];
        String[] values = new String[lines.length];

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < lines.length; i++) {
            String[] nameValuePair = lines[i].split(":");
            map.put(nameValuePair[0].trim(), values[i] = nameValuePair[1].trim());
        }
        return map;
    }

    public String replaceValues(String originalString, Map<String, String> valuesToReplace) {
        for (Map.Entry<String, String> entry : valuesToReplace.entrySet()) {
            originalString = originalString.replace(entry.getKey(), entry.getValue());
        }
        return originalString;
    }

    public SOAPMessage getSOAPMessageFromString(String soapMessageAsString) throws SOAPException, IOException {
        InputStream is = new ByteArrayInputStream(soapMessageAsString.getBytes());
        return MessageFactory.newInstance().createMessage(null, is);
    }


}
