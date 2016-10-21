package com.dengo.erp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://tachometerMicroService")
public interface TachometerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/tachometer/api/test/{nameTest}")
    String test(@PathVariable("nameTest") String testName);
}
