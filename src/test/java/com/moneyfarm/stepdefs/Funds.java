package com.moneyfarm.stepdefs;

import com.moneyfarm.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Funds {
    String api_url= ConfigurationReader.get("api_url");

    public class PUTRequest {


        }

        @Test
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
                    .put("/api/spartans/{id}")
                    .then().log().all()
                    .assertThat().statusCode(201);
        }
}
