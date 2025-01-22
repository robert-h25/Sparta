package com.sparta.rh.simplerestassured;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinglePostCodeTest {

    private static Response response;

    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                    .baseUri("https://api.postcodes.io")
                    .basePath("/postcodes")
                    .header("Accept", "text/json")
                .when()
                    .get("/EC2Y5AS")
                .then()
                    .log().all()
                    .extract().response();
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("The server name in the headers is cloudflare")
    void testServerNameCloudflare() {
        MatcherAssert.assertThat(response.header("Server"), Matchers.is("cloudflare"));
    }

    @Test
    @DisplayName("test correct postcode returned in response")
    void testCorrectPostcode() {
        MatcherAssert.assertThat(response.jsonPath().getString("result.postcode")
                ,Matchers.is("EC2Y 5AS"));
    }

    @Test
    @DisplayName("test for the correct Primary Care Trust")
    void testNameOfPrimaryCareTrust(){
        MatcherAssert.assertThat(response.jsonPath().getString("result.primary_care_trust")
                ,Matchers.is("City and Hackney Teaching"));
    }

    @Test
    @DisplayName("Test for correct number of codes returned")
    void testTotalNumberOfCodes(){
        MatcherAssert.assertThat(response.jsonPath().getMap("result.codes").size()
                ,Matchers.is(14));
    }

}
