package com.mercadolivre.dna.enumerator;

public enum DnaType {
    HUMAN,SIMIAN;
    public static DnaType valueOf(boolean simian){
        if(simian)
            return DnaType.SIMIAN;
        else
            return DnaType.HUMAN;
    }
}
