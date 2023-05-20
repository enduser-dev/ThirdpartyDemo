package com.quec.thirdparty.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@ServletComponentScan
@SpringBootApplication
public class ThirdpartyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdpartyDemoApplication.class, args);
    }

}
