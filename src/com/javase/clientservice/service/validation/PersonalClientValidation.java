package com.javase.clientservice.service.validation;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.PersonalClient;
import com.javase.clientservice.service.exception.ValidationException;

public class PersonalClientValidation implements IValidation<Client> {
    @Override
    public void validate(Client client) throws ValidationException {
        String family = ((PersonalClient) client).getSurname();
        boolean hasEmptyName= family == null || family.trim().isEmpty();
        if(hasEmptyName) {
            throw new ValidationException("Customer must not be empty or null!");
        }
    }
}
