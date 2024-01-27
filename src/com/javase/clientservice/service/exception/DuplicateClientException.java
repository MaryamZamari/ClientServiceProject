package com.javase.clientservice.service.exception;

public class DuplicateClientException extends Throwable {
    String message;
    public DuplicateClientException(String messages) {
        this.message= message;
    }
}
