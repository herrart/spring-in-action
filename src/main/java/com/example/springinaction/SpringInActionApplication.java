package com.example.springinaction;

import com.example.springinaction.model.User;
import com.example.springinaction.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringInActionApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserService userService) {
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("password");
        userService.save(user);
        };

    }
}
