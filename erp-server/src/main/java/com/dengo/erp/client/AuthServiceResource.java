package com.dengo.erp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://auth-service")
public interface AuthServiceResource {

    @RequestMapping(method = RequestMethod.GET, value = "/uaa/api/principal", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getPrincipal();

}
