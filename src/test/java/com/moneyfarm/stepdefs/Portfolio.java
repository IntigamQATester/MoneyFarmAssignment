package com.moneyfarm.stepdefs;

import com.moneyfarm.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class Portfolio {
    String api_url = ConfigurationReader.get("api_url");

    @Test
    public void test2() {
        Response response = when().get(api_url + "/portfolio");

        //verify status code
        Assert.assertEquals(response.statusCode(), 201);

        //verify content type
        Assert.assertEquals(response.contentType(), "application/json");
//execute all datas
        response.prettyPrint();
    }
    @Test
    public void GetMethodWithID() {
        Response response = when().get(api_url + "/portfolio");

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
        Response response = when().get(api_url + "/portfolio");
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

    assertEquals(response.path("The request is malformed"),"bad-request");

}
}
