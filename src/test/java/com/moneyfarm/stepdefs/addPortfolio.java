package com.moneyfarm.stepdefs;

import com.moneyfarm.utilities.ApiUtilsclass;
import com.moneyfarm.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.moneyfarm.stepdefs.addFunds.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;


public class addPortfolio {

    String token=ConfigurationReader.get("token");
    Response response;
    String uri=ConfigurationReader.get("api_URI");

    /*@Test
    public void test2() {
        Response response = when().get(uri + "/portfolio");

        //verify status code
        assertEquals(response.statusCode(), 201);

        //verify content type
        assertEquals(response.contentType(), "application/json");
//execute all datas
        response.prettyPrint();
    }
    @Test
    public void GetMethodWithID() {
        Response response = when().get(uri + "/portfolio");

        given().accept(ContentType.JSON)
                .and().pathParam("id", "\"c3c0d314-e4f8-4aae-8af9-faa71382f2fb\"")
                .when().get("/portfolio/{id}");

        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

        //verify risklevel,totalAmount, id and name with path()
        int id = response.path("id");
        String name = response.path("name");
        double risklevel=response.path("risklevel");
        int totalAmount=response.path("totalAmount");

        assertEquals(id, "c3c0d314-e4f8-4aae-8af9-faa71382f2fb");
        assertEquals(name, "Portfolio 1");
        assertEquals(risklevel, 1.7207453714149104);
        assertEquals(totalAmount, 0);

    }
    @Test
    public void PostMethodWithID() {
        Response response = when().get(uri + "/portfolio");
       given().accept(ContentType.JSON)
            .and().contentType(ContentType.JSON)
            .body(" {\n" +
                    "        \"totalamount\": 1,\n" +
                    "        \"risklevel\": 10,\n" +
                    "        \"name\": \"Portfolio 2\n" +
                    "    }")
            .when().post("/portfolio");

    assertEquals(response.statusCode(),400);
    assertEquals(response.contentType(),"application/json");

    assertEquals(response.path("The request is malformed"),"bad-request");}*/


    Double risk;
    @Given("a valid risklevel {double}")
    public void aValidRisklevel(double risklevel) {
        risk=risklevel;
        Assert.assertTrue("risk must be min 1 max 10",risk>=1 && risk<=10);

    }


    @When("I create the new portfolio with name {string} using access token")
    public void iCreateTheNewPortfolioWithNameUsingAccessToken(String name) {

        String msgBody= "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"riskLevel\": "+risk+"\n" +
                "}";
        response=ApiUtilsclass.sendData(ContentType.JSON,msgBody,token,uri+"/portfolio");
    }


    @And("I should have a new portfolio with name {string}")
    public void iShouldHaveANewPortfolioWithName(String name) {
        response=ApiUtilsclass.getData(ContentType.JSON,token,uri+"/portfolio");
        List<String> names=response.path("name");
        Assert.assertTrue("new portfolio name is not available", names.contains(name));
    }


    @Given("get request is sent to retrieve the whole portfolio")
    public void get_request_is_sent_to_retrieve_the_whole_portfolio() {

        response= ApiUtilsclass.getData(ContentType.JSON,token,uri+"/portfolio");

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {

        Assert.assertEquals(statusCode,response.statusCode());
    }


    @And("the first portfolio name is {string}")
    public void theFirstPortfolioNameIs(String name) {
        Assert.assertEquals(name,response.path("name[0]"));
    }


    @And("the portfolio {string} must be different for different portfolio")
    public void thePortfolioMustBeDifferentForDifferentPortfolio(String str) {
        ApiUtilsclass.verifyNoDuplicate(response,str);
    }

    @Given("get request is sent without token")
    public void getRequestIsSentWithoutToken() {
        response=given().accept(ContentType.JSON)
                .when()
                .get(uri+"/portfolio");

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @Then("the response has the message {string}")
    public void theResponseHasTheMessage(String message) {
        Assert.assertEquals(message,response.path("message"));
    }
@Test
public void SchemaValidator() {

    given().accept(ContentType.JSON)
            .pathParam("id", "c3c0d314-e4f8-4aae-8af9-faa71382f2fb")
            .when().get("/portfolio/{id}")
            .then().assertThat().statusCode(200)
            .and().assertThat().body(matchesJsonSchemaInClasspath("SchemaValidator.json"));
}
}
