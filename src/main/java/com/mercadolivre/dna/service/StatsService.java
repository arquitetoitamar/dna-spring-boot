package com.mercadolivre.dna.service;

import com.mercadolivre.dna.dto.StatsResponseDto;

/**
 *  stats service
 *
 */
public interface StatsService {
    /**
     * get stats
     *
     * @return {@link StatsResponseDto}
     * @see StatsResponseDto
     */
    StatsResponseDto getStats();
}
