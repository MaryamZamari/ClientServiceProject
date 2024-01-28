package com.javase.clientservice.service;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ContactNumber;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ContactService implements IContactService{
    private static Client client;
    public ContactService(Client client) {
        this.client = client;
    }

    public ContactService() {

    }

    //the only field of number that can be set, is the digits themselves. the type can not be changed.
    public void updateNumber(int numberId, String newNumber){
        ContactNumber numberToSet= client.getNumberList()
                .stream()
                .filter(x -> !x.getDeleted())
                .filter(x -> x.getId()== numberId)
                .findFirst()
                .orElseThrow(()->
                        new NoSuchElementException("The client with the Id" + numberId + "does not exist!Try again!"));
        int index= client.getNumberList().indexOf(numberToSet);
        numberToSet.setNumber(newNumber);
        client.getNumberList().set(index, numberToSet);
    }
    public void addNumber(ContactNumber number){
        client.getNumberList().add(number);
    }
    public ContactNumber getNumberById(int numberId){
        Optional<ContactNumber> optionalNumber=
                client.getNumberList().stream()
                        .filter(x -> !x.getDeleted())
                        .filter(x -> x.getId() == numberId).findFirst();
        return optionalNumber.orElseThrow(()->
                new NoSuchElementException("there is no client with the Id " + numberId)) ;
    }
    public void deleteNumberById(int numberId){
        ContactNumber numberToDelete= getNumberById(numberId);
        numberToDelete.setDeleted(true);
    }
    public void printAllNumbers(){
        client.getNumberList().stream()
                .filter(x -> !x.getDeleted())
                .forEach(System.out::println);
    }

}
