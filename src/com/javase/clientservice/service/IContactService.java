package com.javase.clientservice.service;

import com.javase.clientservice.model.ContactNumber;

public interface IContactService {
    void updateNumber(int numberId, String newNumber);

    void addNumber(ContactNumber number);

    ContactNumber getNumberById(int numberId);

    void deleteNumberById(int numberId);

    void printAllNumbers();
}
