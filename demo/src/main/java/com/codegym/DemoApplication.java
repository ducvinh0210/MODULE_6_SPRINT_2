package com.codegym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//        String a = "123123";
//        String b = BCrypt.hashpw(a, BCrypt.gensalt(12));
//        System.out.println(b);
//    }

}
