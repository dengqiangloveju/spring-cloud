package com.lamic.biz;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lamic.controller.RcFeignFallback;

@FeignClient(value = "spring-cloud-provider", fallback =RcFeignFallback.class)
public interface IUserBiz {

    @RequestMapping(value="/api/user/get/{id}", method=RequestMethod.POST) 
    String view(@PathVariable(value = "id") int id); 
}
