package com.softkall.cicoffe.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.MobileDevice;
import com.softkall.cicoffe.service.NotificationService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:00 AM
 * SoftKall™ All rights reserved.
 */


@Slf4j
@Service
public class OneSignalNotificationService implements NotificationService {

    private final WebClient webClient = WebClient.create();
    private final String apiUrl = "https://onesignal.com/api/v1/notifications";

    @Override
    public void sendNotification(Collection<Member> members, String heading, String message) {
        Map<OneSignalLocale, String> contents = new HashMap<>();
        Map<OneSignalLocale, String> headings = new HashMap<>();
        contents.put(OneSignalLocale.ENGLISH, message);
        headings.put(OneSignalLocale.ENGLISH, heading);
        val request = CreateNotificationRequest.builder()
                .appId("1f57b5ae-8c76-441e-8dfd-228898defbcc")
                .contents(contents)
                .headings(headings)
                .deviceIds(members.stream()
                        .flatMap(member -> member.getMobileDevices().stream())
                        .map(MobileDevice::getIdentifier)
                        .collect(Collectors.toList())
                )
                .build();
        webClient
                .post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .doOnError(throwable -> log
                        .error(throwable.getMessage(), throwable)
                )
                .subscribe(response -> log
                        .info("Successfully sent notification via OneSignal which responded with {}", response)
                );
    }


    @Data
    @Builder
    private static class CreateNotificationRequest {
        @JsonProperty("app_id")
        private String appId;
        private Map<OneSignalLocale, String> contents;
        private Map<OneSignalLocale, String> headings;
        @JsonProperty("include_player_ids")
        private Collection<String> deviceIds;
        private Map<String, Object> data;

    }

    private enum OneSignalLocale {
        @JsonProperty("en")
        ENGLISH,
        @JsonProperty("fr")
        FRENCH
    }

    @Data
    private static class CreateNotificationResponse {
        private UUID id;
        private long recipients;
        @JsonProperty("ëxternal_id")
        private UUID externalId;
        private Object errors;
    }

}
