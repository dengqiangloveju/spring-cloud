package com.lamic.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lamic.service.impl.CustomUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	protected UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				// 设置UserDetailsService
				.userDetailsService(customUserService());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
                .formLogin()
                .loginPage("/loginfrom")//登陆页面
                .loginProcessingUrl("/login")//登陆处理路径
                .usernameParameter("username")//登陆用户名参数  
                .passwordParameter("password")//登陆密码参数 
                .failureUrl("/loginfrom")//登陆失败路径
                .defaultSuccessUrl("/index", true)
                .permitAll() //登录页面用户任意访问
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginfrom") 
                .invalidateHttpSession(true)
                .permitAll()//注销行为任意访问
				.and()
				.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/loginfrom");
	}
}
