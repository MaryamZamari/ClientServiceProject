package com.javase.clientservice.facade.validation;

import com.javase.clientservice.dto.LegalClientDto;
import com.javase.clientservice.service.exception.ValidationException;

public class LegalClientValidation implements IValidation<LegalClientDto> {
    @Override
    public void validate(LegalClientDto client) throws ValidationException {
        String personName =  client.getContactPerson();
        boolean hasEmptyName = personName == null || personName.trim().isEmpty();
        if (hasEmptyName) {
            throw new ValidationException("Customer must not be empty or null!");
        }
    }
}
