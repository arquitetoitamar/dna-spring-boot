package com.mercadolivre.dna.dto;

import com.mercadolivre.dna.util.DtoTest;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatsResponseDtoTest  extends DtoTest<StatsResponseDto> {

    @Override
    public  StatsResponseDto getInstance() {
        return new StatsResponseDto();
    }
    @Test
    public void testDto(){
        StatsResponseDto build = StatsResponseDto.builder()
                .countHumanDna(0)
                .countSimianDna(0)
                .ratio(1.0)
                .build();

        StatsResponseDto aaa = new StatsResponseDto(0,0,1.0);

        assertEquals(build.getRatio(), aaa.getRatio());

        assertNotNull(aaa.toString());

        assertTrue(build.canEqual(aaa));

        assertEquals(aaa.hashCode(), build.hashCode());

        assertFalse(aaa.equals("1"));

        assertTrue(aaa.equals(StatsResponseDto.builder()
                .ratio(1.0)
                .countSimianDna(0)
                .countHumanDna(0)
                .build()));

        assertFalse(aaa.equals(StatsResponseDto.builder()
                .ratio(2.0)
                .countSimianDna(1)
                .countHumanDna(1)
                .build()));
    }
}