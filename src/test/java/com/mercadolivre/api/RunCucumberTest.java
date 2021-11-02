package com.mercadolivre.api;

import com.mercadolivre.dna.DnaApplicationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@CucumberOptions(
        features = "src/test/resources/features/stats.feature",
        plugin = {"pretty", "html:target/cucumber/api.html"},
        glue = {"com.mercadolivre.api"},
        tags = "@IsSimian")
@ActiveProfiles(value = "test")
@SpringBootTest(classes = DnaApplicationTest.class)
public class RunCucumberTest {
}
