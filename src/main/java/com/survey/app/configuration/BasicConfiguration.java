package com.survey.app.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Other way to read data from the application.properties file is to
 * use @ConfigurationProperties() annotation. Data would be entered in
 * application prop as basic.value = 100, basic.message = hello etc.
 * 
 * Alternative of @Value annotation. Also spring document recommends this style
 * 
 * @author Mukul
 *
 */

@Component
@ConfigurationProperties("basic") // basic is prefix here
public class BasicConfiguration {
	private boolean value;
	private String message;
	private int number;

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}