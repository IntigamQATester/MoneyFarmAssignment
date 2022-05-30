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

  @addFunds
  Scenario: Add funds in an existing portfolio
    Given a valid id "c3c0d314-e4f8-4aae-8af9-faa71382f2fb" of an existing portfolio
    When I post the amount of 101  to the existing id with access token
    Then the status code should be 200
    And the portfolio amount should have been 101