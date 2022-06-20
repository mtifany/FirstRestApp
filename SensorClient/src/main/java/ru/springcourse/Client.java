package ru.springcourse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {

    public static void main(String[] args) {
        final String sensorName = "sensorName";

    regSensor(sensorName);

        Random random = new Random();

        for (int i =0; i <500; i++){
            sendMeasurement(random.nextFloat() * 100.0f,random.nextBoolean(),sensorName);
        }
    }


    private static void regSensor(String sensorName){
        final String url = "http://localhost:8080/sensors/registration";

        Map<String,Object> jsondata = new HashMap<>();
        jsondata.put("name",sensorName);

        makePostRequest(url, jsondata);

    }

    private static void sendMeasurement(Float value, boolean raining, String sensorname){
        final String url = "http://localhost:8080/measurements/add";

        Map<String,Object> jsondata = new HashMap<>();
        jsondata.put("value", value);
        jsondata.put("raining", raining);
        jsondata.put("sensor", Map.of("name", "sensorName"));


        makePostRequest(url, jsondata);
    }

    private static void makePostRequest(String url, Map<String,Object> jsondata){
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsondata,headers);

        try{
            restTemplate.postForObject(url, request,String.class);
            System.out.println("Measurement sent successfully");
        }
        catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
        }
    }
}
