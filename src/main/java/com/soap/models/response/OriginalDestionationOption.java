package com.soap.models.response;

/**
 * Created with IntelliJ IDEA.
 * User: Rakesh
 * Date: 30/12/13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class OriginalDestionationOption {

    private String originLocationCode;
    private String originDateTime;
    private String destinationLocationCode;

    public String getOriginLocationCode() {
        return originLocationCode;
    }

    public void setOriginLocationCode(String originLocationCode) {
        this.originLocationCode = originLocationCode;
    }

    public String getOriginDateTime() {
        return originDateTime;
    }

    public void setOriginDateTime(String originDateTime) {
        this.originDateTime = originDateTime;
    }

    public String getDestinationLocationCode() {
        return destinationLocationCode;
    }

    public void setDestinationLocationCode(String destinationLocationCode) {
        this.destinationLocationCode = destinationLocationCode;
    }
}
