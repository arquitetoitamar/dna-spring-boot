package com.mercadolivre.dna.dto;

import com.mercadolivre.dna.util.DtoTest;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DnaCreateRequestDtoTest extends DtoTest<DnaCreateRequestDto> {

    @Override
    public DnaCreateRequestDto getInstance() {
        return new DnaCreateRequestDto();
    }

    @Test
    public void testDto(){
        List<String> codons = List.of("AAA");
        DnaCreateRequestDto build = DnaCreateRequestDto.builder()
                .bases(codons)
                .build();

        DnaCreateRequestDto aaa = new DnaCreateRequestDto(codons);

        assertEquals(build.getBases(), aaa.getBases());

        assertNotNull(aaa.toString());

        assertTrue(build.canEqual(aaa));

        assertEquals(aaa.hashCode(), build.hashCode());

        assertFalse(aaa.equals("1"));
    }
}