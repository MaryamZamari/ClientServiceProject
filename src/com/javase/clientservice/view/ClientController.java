package com.javase.clientservice.view;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.ContactNumberDto;
import com.javase.clientservice.facade.ClientFacade;
import com.javase.clientservice.facade.ContactFacade;
import com.javase.clientservice.facade.IClientFacade;
import com.javase.clientservice.facade.IContactFacade;
import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.service.ContactService;
import com.javase.clientservice.service.exception.DuplicateClientException;
import com.javase.clientservice.service.exception.FileException;
import com.javase.clientservice.service.exception.ValidationException;
import com.javase.clientservice.view.component.AbstractCustomerUI;
import com.javase.clientservice.view.component.Console;

import java.io.FileNotFoundException;
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
    private static final IContactFacade numberService = ContactFacade.getinstance();
    private static final IClientFacade clientFacade= ClientFacade.getInstance();
    private static final Console view = new Console();
    private static AbstractCustomerUI view1;

    public void run(){
        try(Scanner input= new Scanner(System.in)){
            int choice = 0;
            do{
                view.printMenu();
                System.out.println();
                choice= parseInt(input.nextLine());
                switch(choice){
                    case 1:
                        ClientDto newClient= view.getClientDetailsFromUser();
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
                    case 3:   //TODO: isn't it incorrect to get the client by Id from user? how would the client know the ID?
                        int clientId= view.getIdFromUser();
                        ClientDto oldClient= clientFacade.getClientById(clientId);
                        ClientDto updatedClient = view.getClientInfoFromUserForEdit(oldClient);
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
                                    ContactNumberDto newNumber= view.getNumberDetailsFromUser();
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
                    case 7:
                        saveData();
                        break;
                    case 8:
                        loadData();
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
           e.getMessage();
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        } catch (FileException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadData() throws FileException, FileNotFoundException {
        clientFacade.loadData();
    }

    private void saveData() throws FileException {
        clientFacade.saveData();
        System.out.println("data saved successfully!");
    }


    public void addClient(ClientDto client) throws DuplicateClientException, ValidationException {
        try {
            clientFacade.addClient(client);
        }catch(DuplicateClientException exception){
            System.out.println("it is not possible to add a duplicate customer! check surname or business person name!");
            clientFacade.addClient(client);
        }catch(ValidationException e){
            System.out.println(e.getMessage());
            clientFacade.addClient(client);
        }
    }
    public static ClientDto searchClient(Object clientDetailToSearch) {
        return clientFacade.getClient(clientDetailToSearch);
    }

    public void deleteClient(int id){
        clientFacade.deleteClientById(id);
    }

    public void updateClient(int id, ClientDto newClient) throws ValidationException {
        clientFacade.updateClient(id, newClient);
    }
    public void printAllClients(){
        clientFacade.printAllClients();
    }
    public void addNumber(ContactNumberDto newNumber) {
        numberService.addNumber(newNumber);
    }

    public void updateNumber(int id, String updatedNumber) {
        numberService.updateNumber(id, updatedNumber);
    }

    public void deleteNumber(int id) {
        numberService.deleteNumberById(id);
    }  //TODO: create facade for number too.

    public void printAllNumbersOfClient(int clientId){
        clientFacade.printAllNumbersOfClient(clientId);
    }



}
