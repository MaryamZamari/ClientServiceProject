package com.javase.clientservice.service.exception;

import java.io.File;

public class FileException extends Throwable {
    private String message;
    public FileException(){
        super("Error while working with the files!");
    }
}
