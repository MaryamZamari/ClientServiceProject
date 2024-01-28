package com.javase.clientservice.service.validation;

import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.service.exception.ValidationException;

public class NumberValidation implements IValidation<ContactNumber>{
    @Override //TODO: implement it in the numberService.
    public void validate(ContactNumber contact) throws ValidationException {
        String number = contact.getNumber();
        if(number == null || number.trim().isEmpty()){
            throw new ValidationException("Number can not be empty or null!");
        }
        if(number.matches("^098\\d{10}$|^00\\{12}$|^+\\d{12}$")){
            throw new ValidationException("Invalid number format!");
        }
    }//TODO: Add regex for email and password.
    //TODO: implement this class in numberService.


}
