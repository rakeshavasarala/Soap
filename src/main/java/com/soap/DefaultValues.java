package com.soap;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DefaultValues {

    /**
     * Have not added all the fields.
     * @return
     */
    public static List<String> fieldsDirectlyToBody() {
        List<String> fields = new ArrayList();
        fields.add("CUGIdentifer");
        fields.add("PointOfSale");
        fields.add("AvailabilityEngineSelectionCriteria");
        fields.add("TypeOfAvailabilitySearch");
        return fields;
    }

    /**
     * All the fields for Message Header
     * @return
     */
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

    /**
     * Not Used an more
     * @return
     */
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


    /**
     * All the fields for CAPHeader.
     * See how the heirarcy of elements is specified under capHeader
     * UserSellingProfile->SellingRevenueType->CommercialRevenueType->SubRevenueType
     * @return
     */
    public static List<String> capHeaderFields() {
        List<String> fields = new ArrayList();
        fields.add("SessionID");

        fields.add("Parameters->ParameterName");
        fields.add("Parameters->ParameterValue");

        fields.add("CallerLoggingText");
        fields.add("LanguageCode");

        fields.add("UserSellingProfile->Audience");

        fields.add("UserSellingProfile->SellingRevenueType->CommercialRevenueType->SubRevenueType");
        fields.add("UserSellingProfile->SellingRevenueType->RedemptionRevenueType->BAMiles->SubRevenueType");
        fields.add("UserSellingProfile->SellingRevenueType->RebateRevenueType->SubRevenueType");

        fields.add("AgentCredentials->OfficeID");
        fields.add("AgentCredentials->AgentID");

        fields.add("AgentIATACode");
        fields.add("DiagnosticsID");
        fields.add("CachingRequired");
        fields.add("TrackingID");

        return fields;
    }


    public static List<String> commonParametersFields() {
        List<String> fields = new ArrayList();
        fields.add("OriginDestinationOption->OriginLocationCode");
        fields.add("OriginDestinationOption->OriginDateTime");
        fields.add("OriginDestinationOption->DestinationLocationCode");
        fields.add("OriginDestinationOption->DestinationDateTime");

        fields.add("OriginDestinationOption->CabinRestriction->CabinCode");
        fields.add("OriginDestinationOption->CabinRestriction->RestrictionType");
        fields.add("OriginDestinationOption->CabinRestriction->HBOFare");

        fields.add("OriginLocationType");
        fields.add("DestinationLocationType");

        fields.add("PassengerCount->NumberOfAdults");
        fields.add("PassengerCount->NumberOfChildren");
        fields.add("PassengerCount->NumberOfInfants");


        fields.add("CabinRestriction->CabinCode");
        fields.add("CabinRestriction->RestrictionType");
        fields.add("CabinRestriction->HBOFare");


        fields.add("OutboundDateRange");
        fields.add("InboundDateRange");
        fields.add("SelectedValue");
        fields.add("SelectedHBOValue");


        return fields;
    }


    public static List<String> originalDestination() {
        List<String> fields = new ArrayList();
        fields.add("Departure->CityCode");
        fields.add("TravelDateTime->DateData->Date");
        fields.add("TravelDateTime->TimeData->Time");
        fields.add("Arrival->CityCode");
        return fields;
    }


    public static List<String> travelerQNames() {
        List<String> fields = new ArrayList();
        fields.add("Traveler->PaxType=ADT");
        fields.add("Traveler->PaxType=CHD");
        fields.add("Traveler->PaxType=INF");
        return fields;
    }

    /**
     * Default Values
     * @return
     */
    public static Map<String, String> airShoppingDefaultValues() {
       Map<String, String> map = new HashMap<>();

        map.put("OriginDestination[0].Departure.CityCode", "?");
        map.put("OriginDestination[0].Date", "?");
        map.put("OriginDestination[0].Time", "?");
        map.put("OriginDestination[0].Arrival.CityCode", "?");
        map.put("OriginDestination[1].Departure.CityCode", "?");
        map.put("OriginDestination[1].Date", "?");
        map.put("OriginDestination[1].Time", "?");
        map.put("OriginDestination[1].Arrival.CityCode", "?");
        map.put("PaxType.ADT", "?");
        map.put("PaxType.CHD", "?");
        map.put("PaxType.INF", "?");
        map.put("Cabin", "?");

        return map;
    }


}
