package com.lamic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private DataSource dataSource;
    
    /*@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}*/
    
    @Bean
    public JdbcClientDetailsService clientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }
    
    @Bean
    public JdbcTokenStore tokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	/*clients.inMemory()
        .withClient("android")
        .scopes("xx")
        .secret("android")
        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
        .and()
        .withClient("webapp")
        .scopes("xx")
        .authorizedGrantTypes("implicit");*/
    	clients.withClientDetails(clientDetailsService(dataSource));
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore(dataSource))
                .accessTokenConverter(accessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }
    
}