package com.webo.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.webo.demo.model.Users;

@Component
public class UserCommandLineRunner implements CommandLineRunner {
	@Autowired
	UserRepository repository;
	private static final Logger log = LoggerFactory
            .getLogger(UserCommandLineRunner.class);
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Users("Ranga", "Admin"));
        repository.save(new Users("Ravi", "User"));
        repository.save(new Users("Satish", "Admin"));
        repository.save(new Users("Raghu", "User"));
        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (Users user : repository.findByRole("Admin")) {
            log.info(user.toString());
	}

}
} 
