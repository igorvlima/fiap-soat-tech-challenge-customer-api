package com.example.fiapsoattechchallengecustomerapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "local|dev|test")
class FiapSoatTechChallengeCustomerApiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Context loaded successfully in local/dev/test");
    }
}
