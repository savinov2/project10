package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url1 = "http://94.198.50.185:7081/api/users";

        ResponseEntity<String> response = restTemplate.getForEntity(url1, String.class);

        String key = response.getHeaders().get("Set-Cookie").get(0).substring(0,response.getHeaders().get("Set-Cookie").get(0).indexOf(';'));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Cookie",key);

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 20);
        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);
        String response2 = restTemplate.postForObject(url1,httpEntity,String.class);

        user.setName("James");
        user.setLastName("Brown");
        HttpEntity<User> httpEntity1 = new HttpEntity<>(user, httpHeaders);
        String response3 = restTemplate.exchange(url1, HttpMethod.PUT,httpEntity,String.class).getBody();

        HttpEntity<User> httpEntity2 = new HttpEntity<>(httpHeaders);
        String response4 = restTemplate.exchange(url1+"/3",HttpMethod.DELETE,httpEntity2,String.class).getBody();

        System.out.println(response2+response3+response4);
    }
}