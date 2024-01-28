package com.javase.clientservice.service.validation;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.service.exception.ValidationException;

public class ClientValidation implements IValidation<Client> {
    @Override
    public void validate(Client client) throws ValidationException{
        String name= client.getName();
        boolean hasEmptyName= name == null || name.trim().isEmpty();
        if(hasEmptyName){
            throw new ValidationException("Customer details must not be empty or null!");
        }

    }
}
