package com.javase.clientservice.service.validation;

import com.javase.clientservice.model.Client;

public class ClientValidationContext extends ValidationContext<Client>{
    public ClientValidationContext(){
        addValidationItem(new ClientValidation());
        addValidationItem(new PersonalClientValidation());
        addValidationItem(new LegalClientValidation());
    }
}
