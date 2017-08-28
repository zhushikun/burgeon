package com.lwrs.app.service.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHandler {

    private RestTemplate template = new RestTemplate();


    public String get(String url){
       return template.getForObject(url, String.class);
    }

}
