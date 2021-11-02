package com.mercadolivre.dna.service;

import com.mercadolivre.dna.dto.DnaCreateRequestDto;
import com.mercadolivre.dna.dto.DnaCreateResponseDto;
import com.mercadolivre.dna.enumerator.DnaType;
import com.mercadolivre.dna.model.Codon;
import com.mercadolivre.dna.model.Nucleo;
import com.mercadolivre.dna.repository.StatsRepository;
import com.mercadolivre.dna.service.impl.CalculatorServiceImpl;
import com.mercadolivre.dna.service.impl.DnaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class DnaServiceTest {

    public static final List<String> SIMIAN_SEQUENCE = List.of("CTGAGA","CTATGC","TATTGT","AGAGGG","CCCCTA","TCACTG");
    public static final List<String> HUMAN_SEQUENCE = List.of("TCGACA","AGAGGG","CTATGC","TATTGT","GCCCTA","CTGAGA");
    public static final List<String> INVALID_LIST_SIZE = List.of("TCGACA","TCGACA","TATTGT");
    public static final List<String> INVALID_CHARS = List.of("AGAGGG","CTATGC","TATTGT","XYZ","CTGAGA","CTGAGA");
    @InjectMocks
    private DnaServiceImpl dnaService;
    @Mock
    private CalculatorServiceImpl coreCalculator;
    @Mock
    private StatsRepository statsRepository;


    @Test
    public void testCreateAnalysisWithSimianSequence() {
        DnaCreateRequestDto requestDto = DnaCreateRequestDto.builder().build();
        requestDto.setBases(SIMIAN_SEQUENCE);
        Nucleo nucleo = Nucleo.builder()
                .dnaSequence("SIMIAN_SEQUENCE")
                .tape(SIMIAN_SEQUENCE.stream().map(Codon::new)
                        .collect(Collectors.toList()))
                .dnaType(DnaType.SIMIAN)
                .build();
        Mockito.when(coreCalculator.verifyTypeDna(Mockito.any())).thenReturn(true);
        Mockito.when(statsRepository.save(Mockito.any())).thenReturn(nucleo);
        DnaCreateResponseDto analysis = dnaService.createAnalysis(requestDto);

        boolean isSimian = analysis.getIsSimian();
        assertTrue(isSimian, "Must be simian Clone");
    }
    @Test
    public void testCreateAnalysisWithHumanSequence() {
        DnaCreateRequestDto requestDto = DnaCreateRequestDto.builder().build();
        requestDto.setBases(HUMAN_SEQUENCE);
        Nucleo nucleo = Nucleo.builder()
                .dnaSequence("HUMAN_SEQUENCE")
                .tape(HUMAN_SEQUENCE.stream().map(Codon::new)
                        .collect(Collectors.toList()))
                .dnaType(DnaType.HUMAN)
                .build();
        Mockito.when(coreCalculator.verifyTypeDna(Mockito.any())).thenReturn(false);
        Mockito.when(statsRepository.save(Mockito.any())).thenReturn(nucleo);
        DnaCreateResponseDto analysis = dnaService.createAnalysis(requestDto);

        boolean isSimian = analysis.getIsSimian();
        assertFalse(isSimian, "Must be HUMAN");
    }

    @Test(expected = ResponseStatusException.class)
    public void testValidateListSize() {

        DnaCreateRequestDto requestDto = DnaCreateRequestDto.builder().build();
        requestDto.setBases(INVALID_LIST_SIZE);
        Nucleo nucleo = Nucleo.builder()
                .dnaSequence("HUMAN_SEQUENCE")
                .tape(HUMAN_SEQUENCE.stream().map(Codon::new)
                        .collect(Collectors.toList()))
                .dnaType(DnaType.HUMAN)
                .build();
        Mockito.when(coreCalculator.verifyTypeDna(Mockito.any())).thenReturn(false);
        Mockito.when(statsRepository.save(Mockito.any())).thenReturn(nucleo);
        DnaCreateResponseDto analysis = dnaService.createAnalysis(requestDto);
    }
    @Test(expected = ResponseStatusException.class)
    public void testValidateInvalidChars() {

        DnaCreateRequestDto requestDto = DnaCreateRequestDto.builder().build();
        requestDto.setBases(INVALID_CHARS);
        Nucleo nucleo = Nucleo.builder()
                .dnaSequence("HUMAN_SEQUENCE")
                .tape(HUMAN_SEQUENCE.stream().map(Codon::new)
                        .collect(Collectors.toList()))
                .dnaType(DnaType.HUMAN)
                .build();
        Mockito.when(coreCalculator.verifyTypeDna(Mockito.any())).thenReturn(false);
        Mockito.when(statsRepository.save(Mockito.any())).thenReturn(nucleo);
        DnaCreateResponseDto analysis = dnaService.createAnalysis(requestDto);
    }
    @Test(expected = ResponseStatusException.class)
    public void testValidateNull() {

        DnaCreateRequestDto requestDto = DnaCreateRequestDto.builder().build();
        requestDto.setBases(null);
        Nucleo nucleo = Nucleo.builder()
                .dnaSequence("HUMAN_SEQUENCE")
                .tape(HUMAN_SEQUENCE.stream().map(Codon::new)
                        .collect(Collectors.toList()))
                .dnaType(DnaType.HUMAN)
                .build();
        Mockito.when(coreCalculator.verifyTypeDna(Mockito.any())).thenReturn(false);
        Mockito.when(statsRepository.save(Mockito.any())).thenReturn(nucleo);
        DnaCreateResponseDto analysis = dnaService.createAnalysis(requestDto);
    }
}