package com.lamic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("/bar")
	public String bar() {
		return "dfghjkl";
	}
}
