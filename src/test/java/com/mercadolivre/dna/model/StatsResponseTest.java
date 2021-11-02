package com.mercadolivre.dna.model;


import com.mercadolivre.dna.util.DtoTest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatsResponseTest extends DtoTest<StatsResponse> {

    @Override
    public  StatsResponse getInstance() {
        return new StatsResponse();
    }
    @Test
    public void testStatsResponse(){
        StatsResponse build = StatsResponse.builder()
                .ratio(1.0)
                .countSimianDna(0)
                .countHumanDna(0)
                .build();

        StatsResponse response = new StatsResponse(0,0,1.0);

        assertEquals(build.getRatio(), response.getRatio());

        assertNotNull(response.toString());

        assertTrue(build.canEqual(response));

        assertEquals(response.hashCode(), build.hashCode());

        assertEquals(StatsResponse.builder()
                .ratio(1.0)
                .countSimianDna(0)
                .countHumanDna(0)
                .build(), response);

        assertNotEquals(StatsResponse.builder()
                .ratio(2.0)
                .countSimianDna(1)
                .countHumanDna(1)
                .build(), response);
    }
}