package com.mercadolivre.dna.api;

import com.mercadolivre.dna.dto.ApiResponseMessage;
import com.mercadolivre.dna.dto.StatsResponseDto;
import com.mercadolivre.dna.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StatsApiController {
    @Autowired
    private StatsService statsService;

    public StatsApiController(StatsService statsService) {
        this.statsService = statsService;
    }

    @Operation(summary = "Get Stats", description = "", tags = {"stats"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stats of Dnas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StatsResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseMessage.class)))})
    @GetMapping(value = "/stats",
            produces = {"application/json"})
    public ResponseEntity<StatsResponseDto> getStats() {
        log.info("Get Stats");
        return ResponseEntity.ok(statsService.getStats());
    }
}
