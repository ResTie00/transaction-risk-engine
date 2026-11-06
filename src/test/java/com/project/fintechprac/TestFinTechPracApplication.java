package com.project.fintechprac;

import org.springframework.boot.SpringApplication;

public class TestFinTechPracApplication {

    public static void main(String[] args) {
        SpringApplication.from(FinTechPracApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
