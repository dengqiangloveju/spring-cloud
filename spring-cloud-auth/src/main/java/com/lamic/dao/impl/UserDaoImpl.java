package com.lamic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lamic.dao.UserDao;
import com.lamic.domain.SysRole;
import com.lamic.domain.SysUser;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public SysUser findByUsername(String username) {
		SysUser sysUser = null;
		if("admin".equals(username)) {
			List<SysRole> roles = new ArrayList<SysRole>();
			SysRole admin = new SysRole(1l, "ROLE_ADMIN");
			roles.add(admin);
			
			sysUser = new SysUser(1l, "admin", "admin", new Date());
			sysUser.setRoles(roles);
		} else {
			List<SysRole> roles = new ArrayList<SysRole>();
			SysRole user = new SysRole(2l, "ROLE_USER");
			roles.add(user);
			
			sysUser = new SysUser(2l, "user", "user", new Date());
			sysUser.setRoles(roles);
		}
		return sysUser;
	}
}
