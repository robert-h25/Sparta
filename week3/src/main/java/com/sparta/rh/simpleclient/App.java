package com.sparta.rh.simpleclient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes/en106lq"))
                .build();
        HttpResponse<String> httpResponse =  null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(httpResponse);

        Map<String, List<String>> headers = httpResponse.headers().map();

        for(var item:headers.entrySet()){
            System.out.println(item.getKey());
            System.out.println(item.getValue());
        }

        System.out.println("Interrogating the response body using json parser");

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject)jsonParser.parse(httpResponse.body());
            System.out.println(jsonObject.get("status"));

            JSONObject resultObject = (JSONObject)jsonObject.get("result");
            System.out.println("primary care trust: " + resultObject.get("primary_care_trust"));

            JSONObject codes = (JSONObject)resultObject.get("codes");
            System.out.println("Codes are: " + codes.entrySet().toString());

        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
