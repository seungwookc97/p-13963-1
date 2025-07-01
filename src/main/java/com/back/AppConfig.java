package com.back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    PersonService personService() {
        System.out.println("postService bean 생성");
        return new PersonService();
    }
}
