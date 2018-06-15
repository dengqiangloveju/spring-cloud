package com.lamic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lamic.biz.IUserBiz;
import com.lamic.domain.BaseResult;
import com.lamic.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;


@RestController 
@RequestMapping(value = "/user") 
public class FeignUserController {
	private static final String URL = "http://spring-cloud-provider/api/user/get/{id}";
    @Autowired 
    private IUserBiz userBiz;
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getFallback", commandProperties={@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE")})
    public String get(@PathVariable(value = "id") int id) {
        return restTemplate.getForObject(URL, String.class, id);
    }
    
    public String getFallback(int id) {
        System.out.println("调用远程接口异常，返回自定义信息"); 
        return "自定义信息"; 
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST) 
    public String post(@PathVariable(value = "id") int id) { 
        return userBiz.view(id); 
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST) 
    public BaseResult save(@Valid User user, BindingResult result) { 
		return new BaseResult(true, userBiz.save(user)); 
    }
}