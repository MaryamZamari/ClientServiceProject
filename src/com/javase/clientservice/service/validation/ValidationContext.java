package com.javase.clientservice.service.validation;

import com.javase.clientservice.service.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class ValidationContext<T> {
    /***
     * this class customises the implementation of the Strategy design pattern.
     */
    private List<IValidation<T>> validations;
    public ValidationContext(){
        this.validations = new ArrayList<>();
    }

    public void addValidationItem(IValidation<T> validation){
        validations.add(validation);
    }

    public void validate(T object) throws ValidationException{
        for(IValidation<T> validation : validations){
            validation.validate(object);
        }
    }

}
