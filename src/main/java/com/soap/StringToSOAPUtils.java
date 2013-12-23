package com.soap;

import com.sun.deploy.util.StringUtils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class StringToSOAPUtils {

    /**
     *  Reads String from feature file and converts it into a map (key, value pairs)
     * @param request - String read from feature file
     * @return
     */
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

    /**
     * Replaces the xml request i.e
     * @param originalString  - default xml request
     * @param valuesToReplace  - values to be replaced as key, value pair
     * @return
     */
    public String replaceValues(String originalString, Map<String, String> valuesToReplace) {
        for (Map.Entry<String, String> entry : valuesToReplace.entrySet()) {
            String key = ">" + entry.getKey() + "<";
            String value = ">" + entry.getValue() + "<";
            originalString = originalString.replace(key, value);
        }
        return originalString;
    }

    /**
     * Converts string into SOAPMessage
     * @param soapMessageAsString
     * @return
     * @throws SOAPException
     * @throws IOException
     */
    public SOAPMessage getSOAPMessageFromString(String soapMessageAsString) throws SOAPException, IOException {
        InputStream is = new ByteArrayInputStream(soapMessageAsString.getBytes());
        return MessageFactory.newInstance().createMessage(null, is);
    }


}
