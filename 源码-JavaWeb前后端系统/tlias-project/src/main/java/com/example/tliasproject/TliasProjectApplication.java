package com.example.tliasproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TliasProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasProjectApplication.class, args);
    }

}
