package com.javase.clientservice.facade.validation;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.PersonalClientDto;
import com.javase.clientservice.service.exception.ValidationException;

public class PersonalClientValidation implements IValidation<ClientDto> {
    @Override
    public void validate(ClientDto client) throws ValidationException {
        String family = ((PersonalClientDto) client).getSurname();
        boolean hasEmptyName= family == null || family.trim().isEmpty();
        if(hasEmptyName) {
            throw new ValidationException("Customer must not be empty or null!");
        }
    }
}
