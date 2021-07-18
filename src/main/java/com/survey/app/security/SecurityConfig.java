package com.survey.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Here we are using In-Memory Authentication. Use of NoOpPasswordEncoder is
	 * deprecated so using BCryptPasswordEncoder() This is authentication
	 * 
	 * @param auth
	 * @throws Exception
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("user1").password("secret1").roles("USER").and().withUser("admin1").password("secret1")
				.roles("USER", "ADMIN");
	}

	/**
	 * Below menthod is used to override the existing configure menthod which shows
	 * on screen whenever we login the app. We are trying to say that for "/login"
	 * permitAll and for "/" and "todo" page access is required
	 * 
	 * This is authorization.
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**").hasRole("USER").antMatchers("/users/**")
				.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and().csrf().disable().headers().frameOptions()
				.disable();
	}
}
