package com.softkall.cicoffe.service;

import org.springframework.web.reactive.function.client.WebClient;

public class DynamicLinkServiceImpl implements DynamicLinkService{

    private final WebClient webClient = WebClient.create();
    private final String apiKey = "";
    private final String apiUrl = "https://firebasedynamiclinks.googleapis.com/v1/shortLinks?key=" + apiKey;

    @Override
    public void createDynamicLink(String teamId){


    }
}
