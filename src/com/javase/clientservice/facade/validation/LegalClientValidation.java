package com.javase.clientservice.facade.validation;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.LegalClientDto;
import com.javase.clientservice.service.exception.ValidationException;

public class LegalClientValidation implements IValidation<ClientDto> {
    @Override
    public void validate(ClientDto client) throws ValidationException {
        String personName = ((LegalClientDto) client).getContactPerson();
        boolean hasEmptyName = personName == null || personName.trim().isEmpty();
        if (hasEmptyName) {
            throw new ValidationException("Customer must not be empty or null!");
        }
    }
}
