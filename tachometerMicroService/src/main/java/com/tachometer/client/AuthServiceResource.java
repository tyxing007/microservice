package com.tachometer.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://auth-service")
public interface AuthServiceResource {

    @RequestMapping(method = RequestMethod.GET, value = "/uaa/api/test", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String test();
}
