package com.lamic.biz;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lamic.config.FeignConfig;

@FeignClient(value = "spring-cloud-provider", configuration = FeignConfig.class)
public interface IUserBiz {

    @RequestMapping(value = "/api/user/get/{id}", method = RequestMethod.GET) 
    String view(@PathVariable(value = "id") int id); 
}
