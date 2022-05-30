
Feature: User onboarding

  @smoke @no-fix
  Scenario: Onboard a new user
    Given a valid email "email.user@example.com"
    And the user is not already onboarded
    When I create the User
    And create a new portfolio
    Then I should have only 1 new user
    And I should have 1 new portfolio for this User

  @fixme
  Scenario: Onboard a new portfolio
    Given a valid portfolio id
    When I create the new portfolio with name "12345"
    Then I should have only 1 new user with name "12345"
    And status code is 201
    And  body is json format

  @add-me
  Scenario: Add funds in an existing portfolio

    Given user enters end point "Uri"
    And accept type is json
    When user sends post request to add funds
    Then entered title is "String"
    And entered id is "String"
    And entered detail is "String"
    And body is json format
    And status code is 201
    And message is "Bad request"

  @add-me
  Scenario: Add funds
    Given user enters end point "Uri"
    And accept type is json
    When user sends post request to add funds
    Then entered title is not String
    And entered id is "String"
    And entered detail is "String"
    And body is json format
    And status code is 400
    And message is "Bad request"
