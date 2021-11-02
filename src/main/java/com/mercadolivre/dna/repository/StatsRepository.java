package com.mercadolivre.dna.repository;

import com.mercadolivre.dna.enumerator.DnaType;
import com.mercadolivre.dna.model.Nucleo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends Neo4jRepository<Nucleo, String> {
    int countByDnaType(DnaType dnaType);
    @Query(" MATCH (n:nucleo) "                                     +
            " WITH count(n.dnaType = 'SIMIAN') as countSimianDna, " +
            "      count(n.dnaType = 'HUMAN') as countHumanDna "    +
            " RETURN "                    +
            "        (toFloat(countSimianDna)/toFloat(countHumanDna)) AS ratio")
    Double countRatio();
}
