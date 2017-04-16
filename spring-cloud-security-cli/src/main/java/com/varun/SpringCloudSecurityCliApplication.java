package com.varun;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import java.util.Arrays;

@SpringBootApplication
public class SpringCloudSecurityCliApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityCliApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientId("pluralsight");
		resourceDetails.setClientSecret("pluralsight");
		resourceDetails.setAccessTokenUri("http://localhost:9000/services/oauth/token");
		resourceDetails.setUsername("sumit");
		resourceDetails.setPassword("setia");
		resourceDetails.setScope(Arrays.asList("toll_read"));

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
		System.out.println("Access Token : " + restTemplate.getAccessToken());
		String response = restTemplate.getForObject("http://localhost:9001/services/tolls",String.class);
		System.out.println("Response from the server : " + response);
	}
}
