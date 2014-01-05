package com.soap.models.response;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Rakesh
 * Date: 30/12/13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class AvailabilityRequest {


    private List<OriginalDestionationOption> originDestinationOptions;
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<OriginalDestionationOption> getOriginDestinationOptions() {
        return originDestinationOptions;
    }

    public void setOriginDestinationOptions(List<OriginalDestionationOption> originDestinationOptions) {
        this.originDestinationOptions = originDestinationOptions;
    }
}
