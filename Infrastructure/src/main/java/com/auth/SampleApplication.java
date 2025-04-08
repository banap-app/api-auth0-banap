package com.auth;

import com.auth.utils.SecretManagerUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    CommandLineRunner run(SecretManagerUtil secretManagerUtil) {
        return args -> {
            System.out.println("Access Key: " + secretManagerUtil.getAccessKey());
            System.out.println("Secret Key: " + secretManagerUtil.getSecretKey());
            System.out.println(secretManagerUtil.getSecret("prod/banap/auth_secret", Region.of("sa-east-1")));
           };
    }
}
