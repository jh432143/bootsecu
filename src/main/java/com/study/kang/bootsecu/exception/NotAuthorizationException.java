package com.study.kang.bootsecu.exception;

public class NotAuthorizationException extends RuntimeException {
    public NotAuthorizationException () {
        super("token 정보가 유효하지 않습니다.");
    }
}
