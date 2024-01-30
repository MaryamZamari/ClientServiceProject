package com.javase.clientservice.facade;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.service.exception.DuplicateClientException;
import com.javase.clientservice.service.exception.ValidationException;

public interface IClientFacade {
    void addClient(ClientDto client) throws DuplicateClientException, ValidationException;
    <T> ClientDto getClient(T clientDetail);
    ClientDto getClientById(int clientId);
    ClientDto getClientByName(String clientName);
    void updateClient(int id, ClientDto newClient) throws ValidationException;
    void deleteClientById(int cliendId);
    void printAllNumbersOfClient(int cliendId);
    void printAllClients(); //TODO : Refactor it to get a list of clients instead of void and add a method to get the deleted customers.
}
