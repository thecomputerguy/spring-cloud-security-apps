package com.varun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableResourceServer
@RestController
public class SpringCloudSecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityServiceApplication.class, args);
	}

	@Autowired
	ResourceServerProperties resourceServerProperties;

	@Bean
	public ResourceServerTokenServices resourceServerTokenServices(){
		return new CustomUserInfoTokenServices(resourceServerProperties.getUserInfoUri(),resourceServerProperties.getClientId());
	}


	@RequestMapping(value = "/tolls",method = RequestMethod.GET)
	@PreAuthorize("#oauth2.hasScope('toll_read') and hasAuthority('ROLE_OPERATOR')")
	public List<TollUsage> getUsage(){
		TollUsage instance1 = new TollUsage("100", "station50", "B65GT1W", "2016-09-30T06:31:22");
		TollUsage instance2 = new TollUsage("101", "station19", "AHY673B", "2016-09-30T06:32:50");
		TollUsage instance3 = new TollUsage("102", "station50", "ZN2GP0", "2016-09-30T06:37:01");

		ArrayList<TollUsage> tolls = new ArrayList<TollUsage>();
		tolls.add(instance1);
		tolls.add(instance2);
		tolls.add(instance3);

		//ResponseEntity<ArrayList<TollUsage>> tolls = oauth2RestTemplate.exchange("http://localhost:9001/services/tolldata", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<TollUsage>>(){});

		return tolls;
	}


	public static class TollUsage {

		public String Id;
		public String stationId;
		public String licensePlate;
		public String timestamp;

		public TollUsage() {}

		public TollUsage(String id, String stationid, String licenseplate, String timestamp){
			this.Id = id;
			this.stationId = stationid;
			this.licensePlate = licenseplate;
			this.timestamp = timestamp;
		}


	}
}
