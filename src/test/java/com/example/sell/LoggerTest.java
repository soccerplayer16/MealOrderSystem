package com.example.sell;


import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest

public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        Supplier<String> d = new Supplier<String>() {
            @Override
            public String get() {
                return "debug";
            }
        };

        logger.debug(d);
        //logger.debug();
        logger.info(new Supplier<String>() {
            @Override
            public String get() {
                return "info";
            }
        });
        logger.error(new Supplier<String>() {
            @Override
            public String get() {
                return "error";
            }
        });
    }
}
