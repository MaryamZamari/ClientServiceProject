package com.javase.clientservice.service.validation;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.LegalClient;
import com.javase.clientservice.service.exception.ValidationException;

public class LegalClientValidation implements IValidation<Client> {
    @Override
    public void validate(Client client) throws ValidationException {
        String personName = ((LegalClient) client).getContactPerson();
        boolean hasEmptyName = personName == null || personName.trim().isEmpty();
        if (hasEmptyName) {
            throw new ValidationException("Customer must not be empty or null!");
        }
    }
}
