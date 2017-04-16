package com.varun;

import com.sun.security.auth.UserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class SpringCloudSecurityAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityAuthorizationServerApplication.class, args);
	}

	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public Principal userPrincipal(Principal userPrincipal){
		return userPrincipal;
	}
}
