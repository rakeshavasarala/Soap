package com.soap.models.request;


import java.util.Arrays;
import java.util.List;

/**
 * Not used
 */
public class OriginalDestination {

    private String departureCityCode;
    private String date;
    private String time;
    private String arrivalCityCodes;

    public String getDepartureCityCode() {
        return departureCityCode;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<String> getArrivalCityCodesAsList() {
        return Arrays.asList(arrivalCityCodes.split(","));
    }


}
