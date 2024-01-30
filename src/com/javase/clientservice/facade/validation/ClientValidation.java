package com.javase.clientservice.facade.validation;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.service.exception.ValidationException;

public class ClientValidation implements IValidation<ClientDto> {
    @Override
    public void validate(ClientDto client) throws ValidationException{
        String name= client.getName();
        boolean hasEmptyName= name == null || name.trim().isEmpty();
        if(hasEmptyName){
            throw new ValidationException("Customer details must not be empty or null!");
        }

    }
}
