package com.mercadolivre.dna.model;

import com.mercadolivre.dna.enumerator.DnaType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.validation.annotation.Validated;

/**
 * DnaCreateResponse
 */
@Validated
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Node("nucleo")
public class Nucleo {
  @Id
  private String dnaSequence;
  @Property(name = "dnaType")
  private DnaType dnaType;
  @Relationship(type="CODON_IN", direction= Relationship.Direction.OUTGOING)
  private List<Codon> tape;
}
