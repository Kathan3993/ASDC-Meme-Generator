package com.group5.memegenerator.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemegeneratorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void helloTest() {
        String check = "hello";
        Assertions.assertEquals(check, "hello");
    }
}
