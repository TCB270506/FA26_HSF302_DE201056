package com.hsf302.ch4;

import com.hsf302.ch4.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Ch4Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Ch4Application.class, args);
        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        System.out.println("Student count: " + studentRepository.count());
    }
}
