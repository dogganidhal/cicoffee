package com.softkall.cicoffe.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.MobileDevice;
import com.softkall.cicoffe.service.DeviceService;
import com.softkall.cicoffe.service.NotificationService;
import lombok.Builder;
import lombok.Data;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;



public class DeviceServiceImpl implements DeviceService{
    @Override
    public void addDevice(MobileDevice mobileDevice) {

    }
}
