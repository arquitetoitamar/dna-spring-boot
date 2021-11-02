package com.mercadolivre.dna;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients
@ComponentScan(value ={"com.mercadolivre.dna"})
@OpenAPIDefinition(info = @Info(title = "DNA API", version = "2.0", description = "API to analytics of DNA Simians and Humans"))
@SpringBootApplication(scanBasePackages = {"com.mercadolivre.dna"})
@EnableConfigurationProperties
@PropertySource(value = "classpath:application.yml")
public class DnaApplication {
    @Generated
    public static void main(String[] args) {
        SpringApplication.run(DnaApplication.class, args);
    }
}
