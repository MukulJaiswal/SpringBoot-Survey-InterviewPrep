package com.survey.app.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Spring Data Jpa with Commandline Runner.
 * @author Mukul
 *
 */
@Component
public interface UserRepository extends CrudRepository<User, Long> {

	// findBy{fieldName} - Spring Data JPA will automatically make a method for
	// this. Other methods :
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
	List<User> findByRole(String role);
}
