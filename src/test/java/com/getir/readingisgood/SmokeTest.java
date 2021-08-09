package com.getir.readingisgood;
import static org.assertj.core.api.Assertions.assertThat;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.controller.CustomerController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class SmokeTest {

    @Autowired
    private CustomerController customerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(customerController).isNotNull();
    }
}
