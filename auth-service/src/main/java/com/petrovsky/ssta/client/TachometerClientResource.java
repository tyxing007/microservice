package com.petrovsky.ssta.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://tachometerMicroService")
public interface TachometerClientResource {

    @RequestMapping(method = RequestMethod.GET, value = "/tachometer/api/principal", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getPrincipalFromTachometer();
}
