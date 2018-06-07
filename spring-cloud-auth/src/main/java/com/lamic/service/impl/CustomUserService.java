package com.lamic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lamic.dao.UserDao;
import com.lamic.domain.SysUser;

@Service
public class CustomUserService implements UserDetailsService{
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		SysUser user = userDao.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		System.out.println("s:" + s);
		System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
		System.out.println("size:" + user.getRoles().size());
		System.out.println("role:" + user.getRoles().get(0).getName());
		
        return user;
	}
}
