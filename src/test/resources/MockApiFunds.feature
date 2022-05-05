Feature:Add funds to an existing portfolio
  Scenario: Add funds
    Given user enters end point "Uri"
    And accept type is json
    When user sends post request to add funds
    Then entered title is "String"
    And entered id is "String"
    And entered detail is "String"
    And body is json format
    And status code is 400
    And message is "Bad request"
