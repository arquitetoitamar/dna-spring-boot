package com.mercadolivre.dna.api;

import com.mercadolivre.dna.dto.ApiResponseMessage;
import com.mercadolivre.dna.dto.DnaCreateRequestDto;
import com.mercadolivre.dna.dto.DnaCreateResponseDto;
import com.mercadolivre.dna.service.DnaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
public class DnaApiController {
    @Autowired
    private DnaService dnaService;

    public DnaApiController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    /**
     * 创建
     *
     * @param request DnaCreateRequestDto
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see DnaCreateResponseDto
     */
    @Operation(summary = "Send data to analysis", description = "", tags={ "dna" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DnaCreateResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Request invalid or malformed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseMessage.class))),
            @ApiResponse(responseCode = "500", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseMessage.class))) })
    @PostMapping(value = "/simian",
            produces = { "application/json" },
            consumes = { "application/json" })
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<DnaCreateResponseDto> analizeDna(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody DnaCreateRequestDto request) {
        log.info(request.toString());
        return ResponseEntity.ok().body(dnaService.createAnalysis(request));
    }
}
