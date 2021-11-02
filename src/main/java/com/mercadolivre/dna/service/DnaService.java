package com.mercadolivre.dna.service;

import com.mercadolivre.dna.dto.DnaCreateRequestDto;
import com.mercadolivre.dna.dto.DnaCreateResponseDto;

public interface DnaService {
    DnaCreateResponseDto createAnalysis(DnaCreateRequestDto dna);
}
