package com.mercadolivre.dna.service.impl;

import com.mercadolivre.dna.dto.StatsResponseDto;
import com.mercadolivre.dna.enumerator.DnaType;
import com.mercadolivre.dna.repository.StatsRepository;
import com.mercadolivre.dna.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * stats service impl
 */
@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsRepository statsRepository;

    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    /**
     * get stats
     *
     * @return {@link StatsResponseDto}
     * @see StatsResponseDto
     */
    public StatsResponseDto getStats() {
        Double ratio = statsRepository.countRatio();
        int simians = statsRepository.countByDnaType(DnaType.SIMIAN);
        int humans = statsRepository.countByDnaType(DnaType.HUMAN);
        return StatsResponseDto.builder()
                .ratio(ratio)
                .countSimianDna(simians)
                .countHumanDna(humans)
                .build();
    }

}
