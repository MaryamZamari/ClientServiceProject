package com.javase.clientservice.facade;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.ContactNumberDto;

public class ContactFacade implements IContactFacade{
    private static final ContactFacade INSTANCE;
    private ContactFacade(){

    }
    static{
        INSTANCE = new ContactFacade();
    }

    public static ContactFacade getinstance(){
        return INSTANCE;
    }
    @Override
    public void updateNumber(int numberId, String newNumber) {

    }

    @Override
    public void addNumber(ContactNumberDto number) {

    }

    @Override
    public ContactNumberDto getNumberById(int numberId) {
        return null;
    }

    @Override
    public void deleteNumberById(int numberId) {

    }

    @Override
    public void printAllNumbers() {

    }


}
