package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
                SpringApplication.run(Main.class, args);

                System.out.println(" ========================================");
                System.out.println("Task Management System Started!");
                System.out.println("========================================");
                System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
                System.out.println("H2 Console: http://localhost:8080/h2-console");
                System.out.println("API Docs:   http://localhost:8080/api-docs");
                System.out.println("======================================== ");
    }
}


