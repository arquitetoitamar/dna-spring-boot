package com.mercadolivre.dna.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * StatsResponse
 */
@Validated
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {
  @JsonProperty("count_simian_dna")
  private Integer countSimianDna;
  @JsonProperty("count_human_dna")
  private Integer countHumanDna;
  @JsonProperty("ratio")
  private Double ratio;

}
