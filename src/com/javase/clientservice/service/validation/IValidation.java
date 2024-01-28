package com.javase.clientservice.service.validation;

import com.javase.clientservice.service.exception.ValidationException;

public interface IValidation<T> {
    void validate(T t) throws ValidationException;

}
