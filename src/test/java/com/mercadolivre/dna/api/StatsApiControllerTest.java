package com.mercadolivre.dna.api;

import com.mercadolivre.dna.dto.StatsResponseDto;
import com.mercadolivre.dna.repository.StatsRepository;
import com.mercadolivre.dna.service.impl.StatsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class StatsApiControllerTest {
    @Mock
    private StatsServiceImpl statsService;
    @Mock
    private StatsRepository statsRepository;
    @InjectMocks
    private StatsApiController controller;
    @Test
    public void testGetStats() {
        Mockito.when(statsService.getStats()).thenReturn(StatsResponseDto.builder()
              .countSimianDna(0)
                .countHumanDna(1)
                .ratio(0.1)
                .build());

        ResponseEntity<StatsResponseDto> stats = controller.getStats();

        assertNotNull(stats, "Response must be created");

    }
}