package com.lamic.controller;

import java.security.Principal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lamic.domain.User;

@RestController
@RequestMapping(value = "/user")
public class ApiUserController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User view(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        user.setName("无境");
        user.setCreateTime(new Date());
        logger.info("请求接口返回：{}", user);
        return user;
    }
    
    @RequestMapping(value = "/getResource")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getResource(Principal principal) {
        return "SUCCESS，授权成功拿到资源啦.当前用户：" + principal.getName();
    }
}
