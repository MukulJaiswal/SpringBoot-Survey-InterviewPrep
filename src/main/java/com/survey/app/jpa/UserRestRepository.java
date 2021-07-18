package com.survey.app.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;


/**
 * Example of Spring Data Rest
 * @author Mukul
 *
 */
@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByRole(@Param("role") String role);
}
/**
 * http://localhost:8080/users in POSTMAN 
 * http://localhost:8080/users/1
 * http://localhost:8080/users/?size=4
 * http://localhost:8080/users/?sort=name,desc
 * http://localhost:8080/users/search/findByRole?role=Admin
 */

/*
 * Good for quick prototype! Be cautious about using this in Big applications!
 */