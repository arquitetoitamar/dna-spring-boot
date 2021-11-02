package com.mercadolivre.dna.service;

import com.mercadolivre.dna.dto.StatsResponseDto;
import com.mercadolivre.dna.repository.StatsRepository;
import com.mercadolivre.dna.service.impl.StatsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StatsServiceTest {

    @InjectMocks
    private StatsServiceImpl statsService;
    @Mock
    private StatsRepository statsRepository;
    @Test
    public void getStats() {
        Mockito.when(statsRepository.countRatio()).thenReturn(1d);
        Mockito.when(statsRepository.countByDnaType(Mockito.any())).thenReturn(1);
        StatsResponseDto stats = statsService.getStats();

        Assertions.assertEquals(stats.getRatio(),1d, "Must be simian Clone");
    }
}