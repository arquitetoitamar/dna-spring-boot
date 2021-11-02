# Meli DNA Test Java Skills for back dev


How to use Spring Boot, Spring Data, and Neo4j together.

Spring Data Neo4j enables convenient integration of Neo4j in your Spring-based application.
It provides object-graph mapping (OGM) functionality and other features common to the Spring Data projects.

 * This project is fictitiously
 * Not has a valid DNA estructure

### Swagger Documentation
https://agile-tor-46740.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
## The Stack

These are the components of our Rest Application:

* Application Type:         Spring-Boot Java Web Application
* Web framework:            Spring-Boot enabled Spring-WebMVC
* Persistence Access:       Spring-Data-Neo4j 6.x
* Database:                 Neo4j-Server 3.5, or 4.x with multi-database
* Frontend:                 jquery, bootstrap, http://d3js.org/[d3.js]

Provision a database quickly with https://sandbox.neo4j.com/?usecase=movies[Neo4j Sandbox] or https://neo4j.com/cloud/aura/[Neo4j Aura].

### Configuration

PORT: 8080 <br>
NEO4J_URI: bolt://54.159.139.0:7687<br>
NEO4J_USER: meli <br>
NEO4J_PASSWORD: secret123 <br>
NEO4J_DATABASE: neo4j <br>
NEO4J_VERSION: 4 <br>

### Queries Exemples

http://54.159.139.0:7474/browser/

Get Relationships <br>
```
MATCH p=()-[r:CODON_IN]->() RETURN p LIMIT 25
```

![alt text](https://github.com/imktec/meli-test/blob/master/github/dna.gif "Exemplo 1")

