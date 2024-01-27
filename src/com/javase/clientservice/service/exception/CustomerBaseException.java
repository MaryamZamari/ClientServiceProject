package com.javase.clientservice.service.exception;

public class CustomerBaseException extends Exception {
    public CustomerBaseException(String message){
        super("Customer not found");
    }
}
