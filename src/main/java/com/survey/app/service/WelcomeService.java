package com.survey.app.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
	
	@Bean
	public String displayWelcomeMessage() {
		return "Welcome";
	}
}
