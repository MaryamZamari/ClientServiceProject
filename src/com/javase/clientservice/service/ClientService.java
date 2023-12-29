package com.javase.clientservice.service;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ClientManagementSystem;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClientService {
    private static ClientManagementSystem managementSystem= new ClientManagementSystem();
    public ClientService(ClientManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    public ClientService() {

    }


    public void addClient(Client client){
        managementSystem.getClientList().add(client);
        System.out.println("Client was added successfully. Client details: " + client.toString());
    }
    public <T> Client getClient(T clientDetail) {
        Client selectedClient= null;
        if(clientDetail instanceof Integer){
            selectedClient = getClientById((Integer) clientDetail);
        }else if(clientDetail instanceof String){
            selectedClient =  getClientByName((String) clientDetail);
        }
        return selectedClient;
    }
    public Client getClientById(int clientId){
        Optional<Client> optionalClient=
                managementSystem.getClientList().stream().filter(x -> x.getId() == clientId).findFirst();
        return optionalClient.orElseThrow(()-> new NoSuchElementException("there is no client with the Id " + clientId)) ;
    }
    public Client getClientByName(String contactName){
        Optional<Client> optionalClient=
                managementSystem.getClientList().stream().filter(x -> x.getName().equalsIgnoreCase(contactName)).findFirst();
        return optionalClient.orElseThrow(() -> new NoSuchElementException("there is no client with the Name " + contactName));
    }
    public void updateClient(int clientId, Client newClient){
        Client c= managementSystem.getClientList()
                .stream().filter(x -> x.getId()== clientId)
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("The client with the Id" + clientId + "does not exist!Try again!"));
        int index= managementSystem.getClientList().indexOf(c);
        managementSystem.getClientList().set(index, newClient);
    }
    public void deleteClientById(int cliendId){
        Client clientToDelete= getClientById(cliendId);
        managementSystem.getClientList().remove(clientToDelete);
    }
    public void printAllNumbersOfClient(int cliendId) {
        Client client= getClientById(cliendId);
        client.getNumberList().stream().forEach(System.out::println);
    }
    public void printAllClients(){
        managementSystem.getClientList().stream().forEach(System.out::println);
    }

}
