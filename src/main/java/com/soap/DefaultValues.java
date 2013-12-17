package com.soap;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DefaultValues {


    public static List<String> messageHeaderFields() {
        List<String> fields = new ArrayList();
        fields.add("MessageCreationDate");
        fields.add("MessageCreationTime");
        fields.add("OriginatingSystem");
        fields.add("OriginatingUser");
        fields.add("OriginatingTerminal");
        fields.add("OriginatingTransaction");
        fields.add("MessageSequenceNumber");
        fields.add("MessageBatchNumber");
        fields.add("MessageSequenceWithinBatch");
        fields.add("Version");
        fields.add("ResponseTime");
        return fields;
    }


    public static Map<String, String> getMessageHeaderDefaultsAsMap() {
        Map<String, String> map = new HashMap();
        map.put("MessageCreationDate", "?");
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

    public static List<String> capHeaderFields() {
        List<String> fields = new ArrayList();
        fields.add("SessionID");

        fields.add("Parameters->ParameterName");
        fields.add("Parameters->ParameterValue");

        fields.add("CallerLoggingText");
        fields.add("LanguageCode");

        fields.add("UserSellingProfile->Audience");

        fields.add("UserSellingProfile->SellingRevenueType->CommercialRevenueType->SubRevenueType");
        fields.add("UserSellingProfile->SellingRevenueType->RedemptionRevenueType->SubRevenueType");
        fields.add("UserSellingProfile->SellingRevenueType->RebateRevenueType->SubRevenueType");

        fields.add("AgentCredentials->OfficeID");
        fields.add("AgentCredentials->AgentID");

        fields.add("AgentIATACode");
        fields.add("DiagnosticsID");
        fields.add("CachingRequired");
        fields.add("TrackingID");

        return fields;
    }

}
