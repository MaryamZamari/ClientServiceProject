package com.javase.clientservice.facade.validation;

import com.javase.clientservice.service.exception.ValidationException;
@FunctionalInterface
public interface IValidation<T> {
    void validate(T t) throws ValidationException;

}
