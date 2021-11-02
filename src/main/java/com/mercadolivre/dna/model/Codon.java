package com.mercadolivre.dna.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
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
@Node("codon")
public class Codon {
  @Id
  private String basesNitrogenizes;

}
