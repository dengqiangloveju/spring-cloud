package com.lamic.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
	
	@Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
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
				// 由于使用的是JWT，我们这里不需要csrf
				.csrf().disable()
				// 基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				// 允许对于网站静态资源的无授权访问
				.antMatchers(
						HttpMethod.GET,
						"/",
						"/*.html",
						"/favicon.ico",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js"
				).permitAll()
				// 对于获取token的rest api要允许匿名访问  
                .antMatchers("/auth/**").permitAll()
				.anyRequest().authenticated();
				//.and()
                //.formLogin()
                //.loginPage("/loginfrom")//登陆页面
                //.loginProcessingUrl("/login")//登陆处理路径
                //.usernameParameter("username")//登陆用户名参数  
                //.passwordParameter("password")//登陆密码参数 
                //.failureUrl("/loginfrom")//登陆失败路径
                //.defaultSuccessUrl("/index", true)
                //.permitAll() //登录页面用户任意访问
                //.and()
                //.logout()
                //.logoutUrl("/logout")
                //.logoutSuccessUrl("/loginfrom") 
                //.invalidateHttpSession(true)
                //.permitAll()//注销行为任意访问
				//.and()
				//.sessionManagement()
				//.maximumSessions(1)
				//.expiredUrl("/loginfrom");
				
		// 添加JWT filter  
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);  
        // 禁用缓存  
        httpSecurity.headers().cacheControl();
	}
}
