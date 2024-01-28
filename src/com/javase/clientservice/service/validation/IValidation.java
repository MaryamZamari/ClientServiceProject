package com.javase.clientservice.service.validation;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.service.exception.ValidationException;

public interface IValidation<T> {
    void validate(T t) throws ValidationException;

}
