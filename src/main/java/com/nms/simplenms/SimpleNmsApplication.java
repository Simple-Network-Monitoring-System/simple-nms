package com.nms.simplenms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EntityScan(basePackages = {
        "com.nms.routersimulator.model"
})
@OpenAPIDefinition(info = @Info(
        title = "Simple NMS API",
        version = "v1",
        description = "Router metric status API"
))
public class SimpleNmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleNmsApplication.class, args);
    }

}
