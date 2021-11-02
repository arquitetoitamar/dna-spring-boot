package com.mercadolivre.dna.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.mercadolivre.dna.repository")
@EntityScan(value = "com.mercadolivre.dna.model")
@EnableTransactionManagement
@Profile("default")
public class DatabaseConfig {


}
