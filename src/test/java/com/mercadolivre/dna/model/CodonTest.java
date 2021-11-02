package com.mercadolivre.dna.model;

import com.mercadolivre.dna.util.DtoTest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodonTest extends DtoTest<Codon> {

    @Override
    public  Codon getInstance() {
        return new Codon();
    }
    @Test
    public void testCodon(){
        Codon codon = Codon.builder()
                .basesNitrogenizes("AAA")
                .build();

        Codon equalCodon = new Codon("AAA");
        assertEquals(codon.getBasesNitrogenizes(), equalCodon.getBasesNitrogenizes());
        assertNotNull(equalCodon.toString());

        assertTrue(codon.canEqual(equalCodon));

        assertEquals(equalCodon.hashCode(), codon.hashCode());
        Codon diffCodon = new Codon("BBB");
        assertFalse(equalCodon.equals(diffCodon));
        assertTrue(equalCodon.equals(Codon.builder()
                .basesNitrogenizes("AAA")
                .build()));

    }
}