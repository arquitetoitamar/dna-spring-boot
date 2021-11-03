package com.mercadolivre.api;

import com.mercadolivre.dna.DnaApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@CucumberOptions(
        features = "src/test/resources/features/stats.feature",
        plugin = {"pretty", "html:target/cucumber/api.html"},
        glue = {"com.mercadolivre.api"})
@ActiveProfiles(value = "test")
@SpringBootTest(classes = DnaApplication.class, properties = {"classpath:application-test.yml"},webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.yml")
public class RunCucumberTest {
}
