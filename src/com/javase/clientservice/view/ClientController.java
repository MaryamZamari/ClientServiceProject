package com.javase.clientservice.view;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.service.ClientService;
import com.javase.clientservice.service.ContactService;
import com.javase.clientservice.service.exception.CustomerBaseException;
import com.javase.clientservice.service.exception.DuplicateClientException;
import com.javase.clientservice.view.component.AbstractCustomerUI;
import com.javase.clientservice.view.component.Console;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/***
 *
 *     interacting with the services and the View. View is used to interact with the
 *     User and Get the inputs from the user, prepare the Outputs needed for
 *     the Service classes.
 *
 */
public class ClientController{
    private static final ContactService numberService = new ContactService();
    private static final ClientService clientService= new ClientService();
    private static final Console view = new Console();
    private static AbstractCustomerUI view1;

    public ClientController(){

    }
    public void run(){
        try(Scanner input= new Scanner(System.in)){
            int choice = 0;
            do{
                view.printMenu();
                System.out.println();
                choice= parseInt(input.nextLine());
                switch(choice){
                    case 1:
                        Client newClient= view.getClientDetailsFromUser();
                        addClient(newClient);
                        break;
                    case 2:
                        Object clientDetailToSearch= null;
                        try{
                            clientDetailToSearch= view.getClientDetailForSelection();}
                        catch(InvalidParameterException exception){
                            System.out.println("you typed the wrong characters. revise your choice to select the client!");
                             }
                        searchClient(clientDetailToSearch);
                        break;
                    case 3:
                        int clientId= view.getIdFromUser();
                        Client oldClient= clientService.getClientById(clientId);
                        Client updatedClient = view.getClientInfoFromUserForEdit(oldClient);
                        updateClient(clientId, updatedClient);
                        System.out.println(
                                "If you need to update the numbers too, " +
                                        "proceed with the Number Menu (6)!");
                        break;
                    case 4:
                        clientId = view.getIdFromUser();
                        deleteClient(clientId);
                        break;
                    case 5:
                        printAllClients();
                        break;
                        
                    //Presenting the Number actions menu
                    case 6:
                        view.printNumberActionMenu();
                        char numberChoice= input.nextLine().toUpperCase().charAt(0);
                        do{
                            switch(numberChoice){
                                case 'A':
                                    ContactNumber newNumber= view.getNumberDetailsFromUser();
                                    addNumber(newNumber);
                                    break;
                                case 'B':
                                    int id= view.getIdFromUser();
                                    printAllNumbersOfClient(id);
                                    break;
                                case 'C':
                                    id= view.getIdFromUser();
                                    deleteNumber(id);
                                    break;
                                case 'D':
                                    id= view.getIdFromUser();
                                    String updatedNumber= view.getNewNumberToUpdate();
                                    updateNumber(id, updatedNumber);
                                    break;
                                default: if(numberChoice != 'X'){
                                    System.out.println("Invalid input. Try again!");
                                }
                            }
                        }while(numberChoice != 'X');
                        break;
                    default:
                        if(choice != 0){
                            System.out.println("the selected number is invalid. try again!");
                        }
                }
            }while(choice != 0);
        }catch(ParseException ex){
            ex.printStackTrace();
            System.out.println("could not parse the String to produce a Date. check your input or the code!");
        }catch(NumberFormatException ex){
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        }catch(InputMismatchException ex){
            System.out.println("invalid input.please enter a valid output");
        } catch (DuplicateClientException e) {
            throw new RuntimeException(e);
        }
    }


    public void addClient(Client client) throws DuplicateClientException {
        try {
            clientService.addClient(client);
        }catch(DuplicateClientException exception){
            System.out.println("it is not possible to add a duplicate customer! check surname or business person name!");
            clientService.addClient(client);
        }
    }
    public static Client searchClient(Object clientDetailToSearch) {
        return clientService.getClient(clientDetailToSearch);
    }

    public void deleteClient(int id){
        clientService.deleteClientById(id);
    }

    public void updateClient(int id, Client newClient){
        clientService.updateClient(id, newClient);
    }
    public void printAllClients(){
        clientService.printAllClients();
    }
    public void addNumber(ContactNumber newNumber) {
        numberService.addNumber(newNumber);
    }

    public void updateNumber(int id, String updatedNumber) {
        numberService.updateNumber(id, updatedNumber);
    }

    public void deleteNumber(int id) {
        numberService.deleteNumberById(id);
    }

    public void printAllNumbersOfClient(int clientId){
        clientService.printAllNumbersOfClient(clientId);
    }



}
