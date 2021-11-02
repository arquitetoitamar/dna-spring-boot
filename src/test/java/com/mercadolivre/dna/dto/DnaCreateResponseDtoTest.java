package com.mercadolivre.dna.dto;

import com.mercadolivre.dna.util.DtoTest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DnaCreateResponseDtoTest extends DtoTest<DnaCreateResponseDto> {

    @Override
    public DnaCreateResponseDto getInstance() {
        return new DnaCreateResponseDto();
    }

    @Test
    public void testDto(){
        DnaCreateResponseDto build = DnaCreateResponseDto.builder()
                .isSimian(true)
                .build();

        DnaCreateResponseDto aaa = new DnaCreateResponseDto(true);

        assertEquals(build.getIsSimian(), aaa.getIsSimian());

        assertNotNull(aaa.toString());

        assertTrue(build.canEqual(aaa));

        assertEquals(aaa.hashCode(), build.hashCode());
        assertEquals(aaa.hashCode(), 1290);
        assertFalse(aaa.equals("1"));

        assertTrue(aaa.equals(DnaCreateResponseDto.builder()
                .isSimian(true)
                .build()));

        assertFalse(aaa.equals(DnaCreateResponseDto.builder()
                .isSimian(false)
                .build()));
    }
}