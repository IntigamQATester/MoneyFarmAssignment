package com.moneyfarm.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class ApiUtilsclass {
    public static void  verifyNoDuplicate(Response response, String unq){

        List<String> Uniqs = response.path(unq);
        for (int i = 0; i < Uniqs.size()-1; i++) {
            for (int j = i+1; j < Uniqs.size(); j++) {
                Assert.assertNotEquals(Uniqs.get(i), Uniqs.get(j));
            }
        }
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public static Response getData(ContentType contentType, String token, String url) {

        Response response=given().accept(contentType)
                .and()
                .header("Authorization",token)
                .when()
                .get(url);
        return response;
    }

    public static Response sendData(ContentType contentType, String messageBody, String token, String url) {

        Response response=given().contentType(contentType)
                .accept(contentType)
                .and()
                .body(messageBody)
                .header("Authorization",token)
                .when()
                .post(url);
        return response;
    }






}


