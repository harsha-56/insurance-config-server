package com.example.insurance_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@RefreshScope
public class InsuranceClientServiceApplication {

	@Autowired
	@Lazy
	private RestTemplate template;

	@Value("${insurance.provider.uri}")
	private String url;

	@GetMapping("/getPlan")
	public List<String> getPlans() {
		List<String> plans = template.getForObject(url, List.class);
		return plans;
	}



	public static void main(String[] args) {
		SpringApplication.run(InsuranceClientServiceApplication.class, args);
	}


	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}





}


