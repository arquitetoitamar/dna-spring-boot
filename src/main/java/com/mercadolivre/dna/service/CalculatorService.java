package com.mercadolivre.dna.service;

import java.util.List;

/**
 *  calculator service
 *
 */
public interface CalculatorService {
    /**
     * verify type dna
     *
     * @param sequence 序列
     * @return {@link boolean}
     */
    boolean verifyTypeDna(List<String> sequence);
}
