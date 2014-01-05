Feature: 1

  @now1
  Scenario: 1
    Given read flight availability response from file
    And convert it into AvailabilityRequest
    Then response has following original destination options
      | originLocationCode | originDateTime       | destinationLocationCode |
      | LHR                | 2013-11-30T00:00:00Z | AMS                     |
      | AMS                | 2013-12-07T00:00:00Z | LHR                     |
    And response has following details
    """
    PointOfSale:LHR
    AvailabilityEngineSelectionCriteria:AnyFares
    """

