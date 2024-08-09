package com.user.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    ERROR_BE1001(HttpStatus.BAD_REQUEST,"BE1001", "회원가입 실패(이메일 중복), 이메일을 확인해주세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
