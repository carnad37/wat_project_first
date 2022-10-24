package com.hhs.order.except;

import org.springframework.http.HttpStatus;

/**
 * 기능 컨트롤 exception
 */
public class ServiceMessageException extends RuntimeException {

    //에러코드
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ServiceMessageException(String message) {
        super(message);
    }

    public ServiceMessageException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
