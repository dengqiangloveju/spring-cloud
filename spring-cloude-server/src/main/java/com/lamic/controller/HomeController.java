package com.lamic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/loginfrom")
	public String loginfrom() {
		return "loginfrom";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/web/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/api/admin")
    @ResponseBody
    public String adminTest() {
        return "role_admin";
    }
	
	@RequestMapping(value="/api/user")
    @ResponseBody
    public String userTest() {
        return "role_user";
    }
}
