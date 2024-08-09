package com.user.exception.type;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ERROR_BE1002("BE1002", "입력값 검증 실패"),
    ERROR_BE1001("BE1001", "회원가입 실패(이메일 중복), 이메일을 확인해주세요.");


    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
