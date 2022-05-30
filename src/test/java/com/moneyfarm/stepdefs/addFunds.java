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
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class addFunds {
    static String token = ConfigurationReader.get("token");
    static Response response;
    static String uri = ConfigurationReader.get("api_URI");

    public class PUTRequest {


    }

   /* @Test
    public void test1() {
        //Create one map for the put request json body
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("title", "string");
        putRequestMap.put("types", "string");
        putRequestMap.put("detail", "string");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", "string")
                .and()
                .body(putRequestMap).
                when()
                .put("/api/funds/{id}")
                .then().log().all()
                .assertThat().statusCode(201);
    }
*/
    String id;

    @Given("a valid id {string} of an existing portfolio")
    public void aValidIdOfAnExistingPortfolio(String ID) {
        id = ID;
        response = ApiUtilsclass.getData(ContentType.JSON, token, uri + "/portfolio");
        List<String> ids = response.path("id");
        Assert.assertTrue("id is not an existing id", ids.contains(id));
    }


    @When("I post the amount of {int}  to the existing id with access token")
    public void iPostTheAmountOfToTheExistingIdWithAccessToken(int amount) {
        String messageBody = "{ amount\": " + amount+ "}";
        response = ApiUtilsclass.sendData(ContentType.JSON, messageBody, token, uri + "/portfolio/" + id + "/add-funds");

    }

    @And("the portfolio amount should have been {int}")
    public void thePortfolioAmountShouldHaveBeen(int amount) {
        response = ApiUtilsclass.getData(ContentType.JSON, token, uri + "/portfolio");
        List<String> ids = response.path("id");
        List<String> totalAmounts = response.path("totalAmount");
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(id)) {
                Assert.assertEquals(amount, totalAmounts.get(i));
            }
        }
    }
}

