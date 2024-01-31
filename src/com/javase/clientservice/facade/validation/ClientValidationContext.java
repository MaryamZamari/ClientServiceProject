package com.javase.clientservice.facade.validation;

import com.javase.clientservice.dto.ClientDto;

public class ClientValidationContext extends ValidationContext<ClientDto>{
    public ClientValidationContext(){
        addValidationItem(new ClientValidation());
        addValidationItem(new PersonalClientValidation());
        addValidationItem(new LegalClientValidation());
    }


}
