package com.moneyfarm.stepdefs;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class User {
    @Test
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
    }
}
