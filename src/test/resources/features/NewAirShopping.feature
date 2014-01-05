Feature: New Air Shopping RQ Feature

  Scenario: With Pricing Info And Multiple Arrival Codes
    Given default NDC AirShopping Request
    And with Destination details
      | departureCityCode | date        | time           | arrivalCityCodes |
      | LHR               | 2013-11-30Z | 00:00:00+00:00 | AMS,AMS2         |
    And with Travellers
      | type | count |
      | ADT  | 1     |
      | CHD  | 1     |
      | INF  | 1     |
    And with cabin "M"
    And with pricing restriction
      | minimumStay | maximumStay | advance | penalty |
      | Y           | N           | N       | N       |
    And NDC Air Shopping Request is build with above options
    When the NDC Air Shopping Request is passed through schema

  @now
  Scenario: With Multiple Original Destinations
    Given default NDC AirShopping Request
    And with Destination details
      | departureCityCode | date        | time           | arrivalCityCodes |
      | LHR               | 2013-11-30Z | 00:00:00+00:00 | AMS              |
      | AMS               | 2013-11-30Z | 00:00:00+00:00 | LHR              |
    And with Travellers
      | type | count |
      | ADT  | 1     |
      | CHD  | 1     |
      | INF  | 1     |
    And with cabin "M"
    And NDC Air Shopping Request is build with above options
    When the NDC Air Shopping Request is passed through schema


