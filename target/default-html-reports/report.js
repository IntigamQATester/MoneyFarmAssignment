$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/MockApiFunds.feature");
formatter.feature({
  "name": "Add funds to an existing portfolio",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add funds in an existing portfolio",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@addFunds"
    }
  ]
});
formatter.step({
  "name": "a valid id \"c3c0d314-e4f8-4aae-8af9-faa71382f2fb\" of an existing portfolio",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I post the amount of 101  to the existing id with access token",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the status code should be 200",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the portfolio amount should have been 101",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});