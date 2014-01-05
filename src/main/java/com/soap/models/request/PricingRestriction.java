package com.soap.models.request;

/**
 * Created with IntelliJ IDEA.
 * User: Rakesh
 * Date: 27/12/13
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class PricingRestriction {

    private String maximumStay;
    private String minimumStay;
    private String advance;
    private String penalty;


    public String getMaximumStay() {
        return maximumStay;
    }

    public String getMinimumStay() {
        return minimumStay;
    }

    public String getAdvance() {
        return advance;
    }

    public String getPenalty() {
        return penalty;
    }
}
