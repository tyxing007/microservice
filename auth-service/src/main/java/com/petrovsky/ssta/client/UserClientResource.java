package com.petrovsky.ssta.client;

import com.petrovsky.ssta.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("http://erp-server")
public interface UserClientResource {

    @RequestMapping(method = RequestMethod.GET, value = "/erp/api/user/email/{email}")
    @ResponseBody
    User findByName(@PathVariable("email") String email);

}
