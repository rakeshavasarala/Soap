package com.soap;


import java.util.HashMap;
import java.util.Map;

public final class DefaultValues {

    public static Map<String, String> getMessageHeaderDefaultsAsMap() {
        Map<String, String> map = new HashMap();

        map.put("MessageCreationTime", "?");
        map.put("OriginatingSystem", "?");
        map.put("OriginatingSystem", "?");
        map.put("OriginatingUser", "?");
        map.put("OriginatingTerminal", "?");
        map.put("OriginatingTransaction", "?");
        map.put("MessageSequenceNumber", "?");
        map.put("MessageBatchNumber", "?");
        map.put("TotalMessagesInBatch", "?");
        map.put("MessageSequenceWithinBatch", "?");
        map.put("Version", "?");
        map.put("ResponseTime", "?");


        return map;
    }
}
