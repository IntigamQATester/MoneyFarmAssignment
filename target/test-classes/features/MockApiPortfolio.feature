Feature: Sending request to mock api to retrive required data
  Scenario: Get request
When users sends a get request to uri/portfolio
Then status code should be 200
And content type should be application/json
And json body should contain all existing datas

  Scenario: verification of data
    Given accept type is json
    And verify required id is true
    And verify required name is true
    And verify required totalamount is true
    And verify required risklevel is true
    When user sends a get request to "/portfolio/{id}"
    And content-type is "application/json"
    Then status code is 200

    Scenario: Posting new  data with wrong schema
      Given accept type is json
      And path param id is 10
      When user sends a get request to "/portfolio/{id}"
      And content-type is "application/json"
      And response body is "bad request"
      Then status code is 400

  @addPortfolio
  Scenario: Add a new portfolio
    Given a valid risklevel 3.4
    When I create the new portfolio with name "10" using access token
    Then the status code should be 200
    And I should have a new portfolio with name "10"

  @get
  Scenario: Retrieve portfolios with token
    Given get request is sent to retrieve the whole portfolio
    Then the status code should be 200
    And the first portfolio name is "Portfolio 1"
    And the portfolio "id" must be different for different portfolio

  @get
  Scenario: Retrieve portfolios without token
    Given get request is sent without token
    Then the status code should be 401
    Then the response has the message "Authorization header required"



