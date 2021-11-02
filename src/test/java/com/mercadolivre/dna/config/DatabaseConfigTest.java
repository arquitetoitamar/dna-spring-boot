package com.mercadolivre.dna.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.Neo4jContainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataNeo4jTest
@Transactional(propagation = Propagation.NEVER)
@Slf4j
@Profile("test")
public class DatabaseConfigTest {
    private static Neo4jContainer<?> neo4jContainer;

    @BeforeAll
    static void initializeNeo4j() {

        neo4jContainer = new Neo4jContainer<>()
                .withAdminPassword("somePassword");
        neo4jContainer.start();
    }

    @AfterAll
    static void stopNeo4j() {

        neo4jContainer.close();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.neo4j.uri=" + neo4jContainer.getBoltUrl(),
                    "spring.neo4j.authentication.username=neo4j",
                    "spring.neo4j.authentication.password=" + neo4jContainer.getAdminPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
    @Test
    public void testCreate(){
        DatabaseConfig databaseConfig = new DatabaseConfig();
        assertNotNull(databaseConfig);
    }
}