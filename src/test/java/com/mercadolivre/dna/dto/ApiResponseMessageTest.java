package com.mercadolivre.dna.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseMessageTest {

    @Test
    public void testValues() {
        assertEquals("Unknown Error", ApiResponseMessage.UNKNOWN_ERRO.getMessage());
        assertEquals("Invalid DNA Node Request, Pattern must be [ATCG]", ApiResponseMessage.BAD_REQUEST.getMessage());
        assertEquals("Invalid DNA List, must be [ATCG,ATCG,ATCG,ATCG,ATCG,ATCG]", ApiResponseMessage.BAD_REQUEST_INVALID_LIST_DNA.getMessage());
    }

    @Test
    public void testValueOf() {
        ApiResponseMessage bad_request = ApiResponseMessage.valueOf("BAD_REQUEST");

        assertEquals(ApiResponseMessage.BAD_REQUEST, bad_request);

        ApiResponseMessage invalid_list = ApiResponseMessage.valueOf("BAD_REQUEST_INVALID_LIST_DNA");

        assertEquals(ApiResponseMessage.BAD_REQUEST_INVALID_LIST_DNA, invalid_list);

        ApiResponseMessage unknow_erro = ApiResponseMessage.valueOf("UNKNOWN_ERRO");

        assertEquals(ApiResponseMessage.UNKNOWN_ERRO, unknow_erro);
    }
}