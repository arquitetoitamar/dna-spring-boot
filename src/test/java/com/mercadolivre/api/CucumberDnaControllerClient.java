package com.mercadolivre.api;

import com.mercadolivre.dna.dto.DnaCreateRequestDto;
import com.mercadolivre.dna.dto.DnaCreateResponseDto;
import com.mercadolivre.dna.dto.StatsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "feignClient",value="simple-books-client", url="http://localhost:8080", qualifiers = {"feignClient"})
public interface CucumberDnaControllerClient {
    @RequestMapping(value = "/simian",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<DnaCreateResponseDto> analiseDna(@Valid @RequestBody DnaCreateRequestDto request);
    @RequestMapping(value = "/stats",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<StatsResponseDto> getStats();
}
