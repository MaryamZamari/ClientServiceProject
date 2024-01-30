package com.javase.clientservice.facade;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.ContactNumberDto;

public interface IContactFacade {
    void updateNumber(int numberId, String newNumber);

    void addNumber(ContactNumberDto number);

    ContactNumberDto getNumberById(int numberId);

    void deleteNumberById(int numberId);

    void printAllNumbers();


}
