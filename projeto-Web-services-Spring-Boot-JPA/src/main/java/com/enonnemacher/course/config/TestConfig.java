package com.enonnemacher.course.config;

import com.enonnemacher.course.entities.User;
import com.enonnemacher.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User usuario1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User usuario2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(usuario1, usuario2));
    }
}
