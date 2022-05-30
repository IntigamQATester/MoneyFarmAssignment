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
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class addUser {
    static String token = ConfigurationReader.get("token");
    static Response response;
    static String uri = ConfigurationReader.get("api_URI");

   /* @Test
    public void PatchTest() {

        //Create one map for the patch request json body
        Map<String, Object> patchRequestMap = new HashMap<>();
        patchRequestMap.put("email", "myemail@gmail.com");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", "c3c0d314-e4f8-4aae-8af9-faa71382f2fb")
                .and()
                .body(patchRequestMap).
                when()
                .patch("/user/{id}")
                .then().log().all()
                .assertThat().statusCode(201);

    }

    @Test
    public void PatchNegativeTest() {

        //Create one map for the patch request json body
        Map<String, Object> patchRequestMap = new HashMap<>();
        patchRequestMap.put("email", "myemail@gmail.com");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 5)
                .and()
                .body(patchRequestMap).
                when()
                .patch("/user/{id}")
                .then().log().all()
                .assertThat().statusCode(201);
    }*/

    @Given("a valid email {string}")
    public void a_valid_email(String mail) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Assert.assertTrue("email not valid", ApiUtilsclass.patternMatches(mail, regexPattern));

    }


    @When("I create the User using access token")
    public void iCreateTheUserUsingAccessToken() {
        String messageBody = "{\n" +
                "  \"email\": \"email@example.com\"\n" +
                "}";
        response = ApiUtilsclass.sendData(ContentType.JSON, messageBody, token, uri + "/user");
    }

    @And("the response has the same email {string}")
    public void theResponseHasTheSameEmail(String email) {
        Assert.assertEquals(email, response.path("email"));
    }
}
