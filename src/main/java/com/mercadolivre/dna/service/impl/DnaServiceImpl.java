package com.mercadolivre.dna.service.impl;

import com.mercadolivre.dna.dto.ApiResponseMessage;
import com.mercadolivre.dna.dto.DnaCreateRequestDto;
import com.mercadolivre.dna.dto.DnaCreateResponseDto;
import com.mercadolivre.dna.enumerator.DnaType;
import com.mercadolivre.dna.model.Codon;
import com.mercadolivre.dna.model.Nucleo;
import com.mercadolivre.dna.repository.StatsRepository;
import com.mercadolivre.dna.service.CalculatorService;
import com.mercadolivre.dna.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *  dna service impl
 *
 */
@Service
public class DnaServiceImpl implements DnaService {
    public static final int DEFAULT_SIZE = 6;
    @Autowired
    private StatsRepository statsRepository;
    @Autowired
    private CalculatorService coreCalculator;

    public DnaServiceImpl(StatsRepository statsRepository, CalculatorServiceImpl coreCalculator) {
        this.statsRepository = statsRepository;
        this.coreCalculator = coreCalculator;
    }
    /**
     * create analysis
     *
     * @param dna dna
     * @return {@link DnaCreateResponseDto}
     * @see DnaCreateResponseDto
     */
    public DnaCreateResponseDto createAnalysis(@Valid DnaCreateRequestDto dna) {
        validate(dna);
        String sequence = dna.getBases().stream().collect(Collectors.joining());
        boolean simian = coreCalculator.verifyTypeDna(dna.getBases());
        final DnaType dnaType = DnaType.valueOf(simian);
        Nucleo nucleo = statsRepository.save(Nucleo.builder()
                .dnaSequence(dnaType.name() + sequence)
                .tape(dna.getBases().stream().map(Codon::new)
                        .collect(Collectors.toList()))
                .dnaType(dnaType)
                .build());
        return DnaCreateResponseDto.builder()
                .isSimian(nucleo.getDnaType().equals(DnaType.SIMIAN))
                .build();
    }

    /**
     * Validate request
     *
     * @param requestDto requestDto
     */
    private void validate(DnaCreateRequestDto requestDto) {
        if(requestDto.getBases() == null || requestDto.getBases().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ApiResponseMessage.BAD_REQUEST_INVALID_LIST_DNA.getMessage(),
                    new Throwable(ApiResponseMessage.BAD_REQUEST.getMessage()));
        }
        if(requestDto.getBases().size() != DEFAULT_SIZE) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ApiResponseMessage.BAD_REQUEST_INVALID_LIST_DNA.getMessage(),
                    new Throwable(ApiResponseMessage.BAD_REQUEST.getMessage()));
        }
        Pattern pattern = Pattern.compile("[ATCG]{6}");
        requestDto.getBases().forEach(dna ->{
            Matcher matcher = pattern.matcher(dna);
            if(!matcher.matches())
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, ApiResponseMessage.BAD_REQUEST.getMessage()  + "in Node: " + dna,
                        new Throwable(ApiResponseMessage.BAD_REQUEST.getMessage()));
        });
    }
}
