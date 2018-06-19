package com.lamic.domain;

import lombok.Data;

@Data
public class User {
	private int id;
	private String name;
	
	public static void main(String[] args) {
		User user = new User();
		user.getId();
		System.out.println(user.getName());
	}
}
