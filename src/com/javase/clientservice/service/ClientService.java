package com.javase.clientservice.service;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.LegalClient;
import com.javase.clientservice.model.PersonalClient;
import com.javase.clientservice.service.exception.DuplicateClientException;
import com.javase.clientservice.service.exception.ValidationException;
import com.javase.clientservice.service.validation.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ClientService implements IClientService {
    private static final ClientService INSTANCE;
    private static List<Client> clientList = new ArrayList<>();
    private ValidationContext<Client> validationContext= new ClientValidationContext();
    static {
        INSTANCE = new ClientService();
    }
    public static ClientService getInstance() {
        return INSTANCE;
    }

    /*
     Singleton Lazy initialization
     public static ClientService getInstance(List<Client> clientList) {

         if(instance == null){
             synchronized(ClientService.class){
                 if(instance == null){
                     instance= new ClientService();
                 }
             }
         }
         return instance;
     }
 */
    //Eager initialisation ==> God Object


    public static List<Client> getClientList() {
        return clientList;
    }
    public void addClient(Client client) throws DuplicateClientException, ValidationException {
        Optional<Client> duplicateClient = clientList.stream().filter(isDuplicate(client)).findFirst();
        if (duplicateClient.isPresent()) {
            throw new DuplicateClientException("it is not possible to add duplicate client! " +
                    "                           check again the name and surname or name and contact person's name!");
        }
        ClientValidation clientValidation= new ClientValidation();
        clientValidation.validate(client);

        validationContext.validate(client);
        clientList.add(client);
        System.out.println("Client was added successfully. Client details: " + client.toString());
    }



    private static Predicate<Client> isDuplicate(Client client) {
        return x -> x.getName().equals(client.getName()) &&
                (isPersonalDuplicate(client, (PersonalClient) x) ||
                        isLegalDuplicate(client, (LegalClient) x));
    }

    private static boolean isLegalDuplicate(Client client, LegalClient x) {
        return client instanceof LegalClient && x.getContactPerson().equals(((LegalClient) client).getContactPerson());
    }

    private static boolean isPersonalDuplicate(Client client, PersonalClient x) {
        return client instanceof PersonalClient && x.getSurname().equals(((PersonalClient) client).getSurname());
    }

    public <T> Client getClient(T clientDetail) {
        Client selectedClient = null;
        if (clientDetail instanceof Integer) {
            selectedClient = getClientById((Integer) clientDetail);
        } else if (clientDetail instanceof String) {
            selectedClient = getClientByName((String) clientDetail);
        }
        System.out.println("The searched client is: " + selectedClient.toString());
        return selectedClient;
    }

    public Client getClientById(int clientId) {
        Stream<Client> client = getClientList().stream().filter((x) -> x.getDeleted() == false).filter(x -> x.getId().equals(clientId));
        return client.findFirst().orElseThrow(() -> new NoSuchElementException("there is no client with the Id " + clientId));
    }

    public Client getClientByName(String clientName) {
        Optional<Client> optionalClient = getClientList().stream().filter((x) -> x.getDeleted() == false).filter(x -> x.getName().equalsIgnoreCase(clientName)).findFirst();
        return optionalClient.orElseThrow(() -> new NoSuchElementException("there is no client with the Name " + clientName));
    }

    public void updateClient(int clientId, Client newClient) {
        Client c = getClientList().stream().filter((x) -> x.getDeleted() == false).filter(x -> x.getId() == clientId).findFirst().orElseThrow(() -> new NoSuchElementException("The client with the Id" + clientId + "does not exist!Try again!"));
        int index = getClientList().indexOf(c);
        getClientList().set(index, newClient);
        System.out.println("Modification was done! The updated client is: " + c.toString());
    }

    public void deleteClientById(int cliendId) {
        Client clientToDelete = getClientById(cliendId);
        clientToDelete.setDeleted(true);
        System.out.println("Client removed successfully! The updated list is: " + getClientList().toString());
    }

    public void printAllNumbersOfClient(int cliendId) {
        Client client = getClientById(cliendId);
        client.getNumberList().stream().filter(x -> !x.getDeleted()).forEach(System.out::println);
    }

    public void printAllClients() {
        getClientList().stream().filter(x -> !x.getDeleted()).forEach(System.out::println);
    }

}
