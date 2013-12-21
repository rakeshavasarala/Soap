
Feature: Test Feature
  @now
  Scenario: replace values
    Given I have a default AirShopping Request
    When I replace the following values in the Request
    """
    OriginDestination[0].Departure.CityCode:LHR
    OriginDestination[0].Date:2013-11-30Z
    OriginDestination[0].Time:00:00:00+00:00
    OriginDestination[0].Arrival.CityCode:AMS
    OriginDestination[1].Departure.CityCode:AMS
    OriginDestination[1].Date:2013-12-07Z
    OriginDestination[1].Time:00:00:00+00:00
    OriginDestination[1].Arrival.CityCode:LHR
    PaxType.ADT:2
    PaxType.CHD:2
    PaxType.INF:2
    Cabin:M
    """