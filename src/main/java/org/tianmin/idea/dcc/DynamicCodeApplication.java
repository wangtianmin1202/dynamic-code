package org.tianmin.idea.dcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DynamicCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicCodeApplication.class, args);
    }

}
