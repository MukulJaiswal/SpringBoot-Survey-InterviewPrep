package com.survey.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.app.configuration.BasicConfiguration;
import com.survey.app.service.WelcomeService;

@RestController
public class WelcomeController {

	@Autowired
	WelcomeService welcomeService;

	@Value("${welcome.message}")
	private String welcomeMessage;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String displayWelcomePage() {
//		return welcomeService.displayWelcomeMessage();
		return welcomeMessage;
	}

	// Get the profile in coding part
	@Value("${spring.profiles.active}")
	private String profile;

	@Autowired
	private Environment env;

	@Autowired
	private BasicConfiguration configuration;

	@Profile("prod") // This means that this function is going to called only for Prod profile. If it
						// is UAT then it called for UAT.
	@Bean
	public void profileBean() {
		System.out.println("Hello PROD");// This will not print in console when profile is not prod
		System.out.println("profile is : " + profile + " and " + env.getActiveProfiles()[0]);
	}

	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfiguration() {

		Map<String, Comparable> map = new HashMap();
		map.put("message", configuration.getMessage());
		map.put("number", configuration.getNumber());
		map.put("value", configuration.isValue());
		
		throw new ArithmeticException("Intentional Exception is thrown");
	}
}
