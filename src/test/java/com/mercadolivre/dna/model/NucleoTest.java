package com.mercadolivre.dna.model;


import com.mercadolivre.dna.enumerator.DnaType;
import com.mercadolivre.dna.util.DtoTest;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NucleoTest extends DtoTest<Nucleo> {

    public static final String DNA_SEQUENCE = "AAA";

    @Override
    public  Nucleo getInstance() {
        return new Nucleo();
    }

    @Test
    public void testNucleo(){
        List<Codon> codons = List.of(Codon.builder()
                .basesNitrogenizes(DNA_SEQUENCE)
                .build());
        Nucleo build = Nucleo.builder()
                .dnaSequence(DNA_SEQUENCE)
                .dnaType(DnaType.SIMIAN)
                .tape(codons)
                .build();

        Nucleo nucleo = new Nucleo(DNA_SEQUENCE, DnaType.SIMIAN, codons);

        assertEquals(build.getDnaSequence(), nucleo.getDnaSequence());

        assertNotNull(nucleo.toString());

        assertTrue(build.canEqual(nucleo));

        assertEquals(nucleo.hashCode(), build.hashCode());
        Nucleo diff = new Nucleo("AAAA", DnaType.SIMIAN, codons);
        assertFalse(nucleo.equals("AAA"));
        assertFalse(nucleo.equals(diff));
    }
}