package com.lamic.controller;

import org.springframework.stereotype.Component;

import com.lamic.biz.IUserBiz;
import com.lamic.domain.User;

@Component
public class RcFeignFallback implements IUserBiz {

	@Override
	public String view(int id) {
		System.out.println("调用远程接口异常，返回自定义信息");
        return "system error";
	}

	@Override
	public String save(User user) {
		System.out.println("添加接口调用失败");
        return "system error";
	}

}
