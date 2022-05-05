Feature: Creating new datas

Scenario: Add mail
Given accept type is Json
And email parameter value is "myemail@gmail.com"
When user sends Post request to /user/{id}
Then response status code should be 201
And response content-type: application/json

  Scenario: Add mail
    Given accept type is Json
    And email parameter value is "myemail@gmail.com"
    When user sends Post request to /user/{id} with out of bound id
    And response content-type: application/json
    Then response status code should be 400
    And response message ais bad request


