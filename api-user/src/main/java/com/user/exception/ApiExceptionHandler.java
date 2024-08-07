package com.user.exception;

import com.user.exception.response.ErrorResponse;
import com.user.exception.type.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse error = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        error.setCode(errorCode.getCode());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * EX)
     * {
     *   "status": 400,
     *   "message": "입력값 검증 실패",
     *   "errors": {
     *     "email": "유효한 이메일 주소를 입력해주세요.",
     *   }
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        FieldError firstError = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .orElse(null);

        ErrorCode errorCode = ErrorCode.ERROR_BE1002; // 기본 에러 코드
        String errorMessage = (firstError != null) ? firstError.getDefaultMessage() : errorCode.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
