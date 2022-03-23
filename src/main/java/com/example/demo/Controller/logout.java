package com.example.demo.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;

public class logout{

	@Bean
	SecurityWebFilterChain http(ServerHttpSecurity http) throws Exception {
	    DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
	            new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
	    );

	    http
	        .authorizeExchange((exchange) -> exchange.anyExchange().authenticated())
	        .logout((logout) -> logout.logoutHandler(logoutHandler));

	    return http.build();
	}
}
