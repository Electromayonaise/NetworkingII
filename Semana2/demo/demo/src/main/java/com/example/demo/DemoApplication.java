package com.example.demo;
import java.util.Arrays;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.example.demo.repositories.UserRepository;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = Logger.getLogger(DemoApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private UserRepository userRepository;
	@Autowired // Esto es para que Spring inyecte el UserRepository
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
    public void run(String... args) throws Exception {
        log.info("User: " + Arrays.toString(userRepository.getUsers().toArray()));
		
    }

}
