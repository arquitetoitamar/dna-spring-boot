package com.mercadolivre.dna.dto;


import org.springframework.http.HttpStatus;

public enum ApiResponseMessage {
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"Invalid DNA Node Request, Pattern must be [ATCG]"),
    BAD_REQUEST_INVALID_LIST_DNA(HttpStatus.BAD_REQUEST,"Invalid DNA List, must be [ATCG,ATCG,ATCG,ATCG,ATCG,ATCG]"),
    UNKNOWN_ERRO(HttpStatus.INTERNAL_SERVER_ERROR,"Unknown Error");

    private HttpStatus code;
    private String message;

    ApiResponseMessage(HttpStatus codeInformed,
                       String messageInformed){
        this.code = codeInformed;
        this.message = messageInformed;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
