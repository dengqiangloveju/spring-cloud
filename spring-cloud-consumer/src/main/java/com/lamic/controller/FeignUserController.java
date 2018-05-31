package com.lamic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lamic.biz.IUserBiz;


@RestController 
@RequestMapping(value = "/user") 
public class FeignUserController {
    @Autowired 
    private IUserBiz userBiz; 

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET) 
    public String get(@PathVariable(value = "id") int id) { 
        return userBiz.view(id); 
    } 
}