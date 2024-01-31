package com.javase.clientservice.view.component;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.ContactNumberDto;
import com.javase.clientservice.facade.ClientFacade;
import com.javase.clientservice.model.*;
import com.javase.clientservice.service.exception.ValidationException;
import com.javase.clientservice.utility.IdGeneratorUtil;
import com.javase.clientservice.utility.ScannerWrapperUtil;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.function.Function;

public class Console{

    //menu + get details from user for various operations needed in the service classes.
    private final ScannerWrapperUtil scannerWrapper;
    private final ClientFacade clientFacade= ClientFacade.getInstance();
    public Console(){
        scannerWrapper= ScannerWrapperUtil.getInstance();
    }

    public void printMenu() {
        System.out.println();
        System.out.println("Welcome to ClientDto Management Portal! \n" +
                "--- select a menu item: --- \n" +
                "0.Exit\n" +
                "1.Add a new client.\n" +
                "2.Search a client \n" +
                "3.Edit a client\n" +
                "4.Removing a client \n" +
                "5.Printing all the Clients.\n" +
                "6.Number Menu _ Functionalities related to numbers of a client \n" +
                "7.Save data.\n" +
                "8.Load data.\n"
        );
    }

    public void printNumberActionMenu() {  //nestedSwitch will be
        System.out.println("----Number Actions Menu------\n" +
                "X.Exit \n" +
                "A.Add a number \n" +
                "B.Get numbers of the contact by id \n" +
                "C.Delete a number \n" +
                "D.Update a number \n"
        );
    }

    public ClientDto getClientDetailsFromUser() throws ParseException {
        ClientDto newClient = null;
        AbstractCustomerUI customerUI = null;
        char clientType= scannerWrapper.getUserInput("what type of client? " +
                                                                "P: Personal,  " +
                                                                "B: Business. ", x -> x.toUpperCase().charAt(0));
        scannerWrapper.clearExcessiveInput();
        ClientType type = switch(clientType){
            case 'P' -> ClientType.P;
            case 'B' -> ClientType.B;
            default -> throw new IllegalStateException("Unexpected value: " + clientType);
        };
        newClient= AbstractCustomerUI.createCustomerUI(type).generateCustomer(type);
        return newClient;
    }

    public ClientDto getClientInfoFromUserForEdit(ClientDto oldClient) throws ValidationException {
        String name= scannerWrapper.getUserInput("Enter new Name: " , Function.identity());
        String email= scannerWrapper.getUserInput("Enter new Email: ", Function.identity());
        String address= scannerWrapper.getUserInput("Enter new Address: ", Function.identity());

        oldClient.setName(name);
        oldClient.setEmail(email);
        oldClient.setAddress(address);
        AbstractCustomerUI
                    .createCustomerUI(oldClient.getType())
                    .editClient(oldClient);
        clientFacade.updateClient(oldClient.getId(), oldClient);
        return oldClient;
    }

    public int getIdFromUser (){
        int id= scannerWrapper.getUserInput("enter Id: " , Integer::valueOf);
        return id;
    }
    public Object getClientDetailForSelection() throws InvalidParameterException{
        char choice=
            scannerWrapper.getUserInput("How do you want to search for the client? \n" +
                                        "N.Name\n"+
                                        "I.Id\n", x -> x.charAt(0));
        scannerWrapper.clearExcessiveInput();
        if(choice == 'N'){
            String name= scannerWrapper.getUserInput("enter name:", Function.identity());
            return name;
        }else if(choice == 'I'){
            int id= scannerWrapper.getUserInput("enter id: ", Integer::valueOf);
            return id;
        }else{
            throw new InvalidParameterException();
        }

    }
    public String getNewNumberToUpdate(){
       return scannerWrapper.getUserInput("enter new number: \n", Function.identity());
    }
    public ContactNumberDto getNumberDetailsFromUser(){
        String number= scannerWrapper.getUserInput("enter number: ", Function.identity());
        return new ContactNumberDto(null , number);  //TODO: IDs dont get generated here. we will generate later on.
    }


}


