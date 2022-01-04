package com.enonnemacher.course.config;

import com.enonnemacher.course.entities.Order;
import com.enonnemacher.course.entities.User;
import com.enonnemacher.course.repository.OrderRepository;
import com.enonnemacher.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User usuario1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User usuario2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        userRepository.saveAll(Arrays.asList(usuario1, usuario2));

        Order order1 = new Order(null, Instant.parse("2021-12-20T19:53:07Z"), usuario1);
        Order order2 = new Order(null, Instant.parse("2021-12-21T03:42:10Z"), usuario2);
        Order order3 = new Order(null, Instant.parse("2021-12-22T15:21:22Z"), usuario1);
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
    }
}
