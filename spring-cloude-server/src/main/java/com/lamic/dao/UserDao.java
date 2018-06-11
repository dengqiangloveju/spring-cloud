package com.lamic.dao;

import com.lamic.domain.SysUser;

public interface UserDao {
	SysUser findByUsername(String username);
}
