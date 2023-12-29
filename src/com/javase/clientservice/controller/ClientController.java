package com.javase.clientservice.controller;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.service.ClientService;
import com.javase.clientservice.service.NumberService;
import com.javase.clientservice.view.Console;
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
public class ClientController {
    private static NumberService numberService = new NumberService();
    private static ClientService clientService= new ClientService();
    private static Console view = new Console();
    public void initialize(NumberService numberService, ClientService clientService, Console view){
        this.numberService= numberService;
        this.clientService= clientService;
        this.view= view;
    }
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
                        Client newClient= view.getClientDetailsFromUser(input);
                        addClient(newClient);
                        break;
                    case 2:
                        Object clientDetailToSearch= view.getClientDetailForSelection(input);
                        Client client= clientService.getClient(clientDetailToSearch);
                        searchClient(client);
                        break;
                    case 3:
                        int clientId= view.getIdFromUser(input);
                        Client oldClient= clientService.getClientById(clientId);
                        Client updatedClient = view.getClientInfoFromUserForEdit(input, oldClient);
                        updateClient(clientId, updatedClient);
                        System.out.println("If you need to update the numbers too, proceed with the Number Menu (6)!");
                        break;
                    case 4:
                        clientId = view.getIdFromUser(input);
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
                                    ContactNumber newNumber= view.getNumberDetailsFromUser(input);
                                    addNumber(newNumber);
                                    break;
                                case 'B':
                                    int id= view.getIdFromUser(input);
                                    printAllNumbersOfClient(id);
                                    break;
                                case 'C':
                                    id= view.getIdFromUser(input);
                                    deleteNumber(id);
                                    break;
                                case 'D':
                                    id= view.getIdFromUser(input);
                                    String updatedNumber= view.getNewNumberToUpdate(input);
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
            }while(choice != 00);
        }catch(ParseException ex){
            ex.printStackTrace();
            System.out.println("could not parse the String to produce a Date. check your input or the code!");
        }catch(NumberFormatException ex){
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        }catch(InputMismatchException ex){
            System.out.println("invalid input.please enter a valid output");
        }
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

    public void addClient(Client client){
        clientService.addClient(client);
    }

    public  <T>  void searchClient(T clientDetail){
        Client client= clientService.getClient(clientDetail);
        System.out.println("The retrieved client is: "+ client.toString());
    }

    public void deleteClient(int id){
        clientService.deleteClientById(id);
    }

    public void updateClient(int id, Client newClient){
        clientService.updateClient(id, newClient);
    }

    public void printAllNumbersOfClient(int clientId){
        clientService.printAllNumbersOfClient(clientId);
    }
    public void printAllClients(){
        clientService.printAllClients();
    }


}
