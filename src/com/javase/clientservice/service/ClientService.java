package com.javase.clientservice.service;

import com.javase.clientservice.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class ClientService {
    private static List<Client> clientList= new ArrayList<>();
    private static final ClientService INSTANCE;
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
    static {
        INSTANCE = new ClientService();
   }
    public static List<Client> getClientList() {
        return clientList;
    }

    public static void setClientList(List<Client> clientList) {
        ClientService.clientList = clientList;
    }

    public ClientService() {
    }
    public void addClient(Client client){
        clientList.add(client);
        System.out.println("Client was added successfully. Client details: "
                            + client.toString());
    }
    public <T> Client getClient(T clientDetail) {
        Client selectedClient= null;
        if(clientDetail instanceof Integer){
            selectedClient = getClientById((Integer) clientDetail);
        }else if(clientDetail instanceof String){
            selectedClient =  getClientByName((String) clientDetail);
        }
        System.out.println("The searched client is: " + selectedClient.toString());
        return selectedClient;
    }
    public Client getClientById(int clientId){
        Stream<Client> client=
                getClientList().stream()
                        .filter((x) -> x.getDeleted() == false)
                        .filter(x -> x.getId().equals(clientId));
        return client.findFirst().orElseThrow(()->
                new NoSuchElementException("there is no client with the Id " + clientId));
            }



    public Client getClientByName(String clientName){
        Optional<Client> optionalClient=
                getClientList().stream()
                        .filter((x) -> x.getDeleted() == false)
                        .filter(x -> x.getName().equalsIgnoreCase(clientName)).findFirst();
        return optionalClient.orElseThrow(() ->
                new NoSuchElementException("there is no client with the Name " + clientName));
    }
    public void updateClient(int clientId, Client newClient){
        Client c= getClientList()
                .stream()
                .filter((x) -> x.getDeleted() == false)
                .filter(x -> x.getId()== clientId)
                .findFirst()
                .orElseThrow(()->
                         new NoSuchElementException(
                                 "The client with the Id" + clientId + "does not exist!Try again!"));
        int index= getClientList().indexOf(c);
        getClientList().set(index, newClient);
        System.out.println("Modification was done! The updated client is: " + c.toString());
    }
    public void deleteClientById(int cliendId){
        Client clientToDelete= getClientById(cliendId);
        clientToDelete.setDeleted(true); //TODO : take all the sysouts to the console part. here only business logic
        System.out.println("Client removed successfully! The updated list is: "
                + getClientList().toString());
    }
    public void printAllNumbersOfClient(int cliendId) {
        Client client= getClientById(cliendId);
        client.getNumberList().stream()
                .filter(x -> !x.getDeleted())
                .forEach(System.out::println);
    }
    public void printAllClients(){
        getClientList()
                          .stream()
                          .filter(x -> !x.getDeleted())
                          .forEach(System.out::println);
    }

}
