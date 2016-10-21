package com.dengo.erp.client;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("http://tachometerMicroService")
public interface TachometerClient {

}
