package com.javase.clientservice.service;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.model.Client;
import com.javase.clientservice.service.exception.DuplicateClientException;
import com.javase.clientservice.service.exception.FileException;
import com.javase.clientservice.service.exception.ValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IClientService {
    void addClient(Client client) throws DuplicateClientException, ValidationException;
    <T> Client getClient(T clientDetail);
    Client getClientById(int clientId);
    Client getClientByName(String clientName);
    void updateClientList(int clientId, Client newClient);
    void deleteClientById(int cliendId);
    void printAllNumbersOfClient(int cliendId);
    void printAllClients();
    void saveData() throws IOException, FileException;
    void loadData() throws FileException, FileNotFoundException;
}
