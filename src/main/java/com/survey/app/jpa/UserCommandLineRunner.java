package com.survey.app.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Spring Data Jpa example with Commandline Runner
 * @author Mukul
 *
 */
@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private Log log = LogFactory.getLog(UserCommandLineRunner.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Ranga", "Admin"));
		userRepository.save(new User("Ravi", "User"));
		userRepository.save(new User("Satish", "Admin"));
		userRepository.save(new User("Raghu", "User"));

		for (User user : userRepository.findAll()) {
			log.info(user.toString());
		}

		log.info("---------------------------------");

		for (User user : userRepository.findByRole("Admin")) {
			log.info(user.toString());
		}
	}
}
