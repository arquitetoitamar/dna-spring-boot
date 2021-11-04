package com.mercadolivre.dna.repository;

import com.mercadolivre.dna.enumerator.DnaType;
import com.mercadolivre.dna.model.Nucleo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends Neo4jRepository<Nucleo, String> {
    int countByDnaType(DnaType dnaType);
    @Query(" MATCH (n:nucleo)" +
            "WHERE n.dnaType = 'HUMAN' " +
            "CALL {" +
            "  WITH n" +
            "  MATCH (s:nucleo)" +
            "  WHERE s.dnaType = 'SIMIAN' " +
            "  RETURN count(s) AS simians " +
            "}" +
            "RETURN tofloat(simians)/toFloat(count(n.dnaType)) as ratio")
    Double countRatio();
}
