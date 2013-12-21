
Feature: Test Feature 1

  Scenario: Scenario 1
    Given I have a request

    """
      MessageCreationDate: Today
      OriginatingSystem: XP
      UnWantedField:willNotBeAdded
      SessionID:SessionId
      OfficeID:BAOffice
      AgentID:Agent1
      ParameterName:name
      ParameterValue:value
      CommercialRevenueType->SubRevenueType:commercial
      BAMiles->SubRevenueType:redemption
      RebateRevenueType->SubRevenueType:rebate
      CUGIdentifer:AddDirectlyToBody
      PointOfSale:AddDirectlyToBody
      Traveler->PaxType=ADT:FromFeatureFile
    """