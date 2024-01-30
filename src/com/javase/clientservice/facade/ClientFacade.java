package com.javase.clientservice.facade;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.mapper.ClientMapper;
import com.javase.clientservice.model.Client;
import com.javase.clientservice.service.ClientService;
import com.javase.clientservice.service.exception.DuplicateClientException;
import com.javase.clientservice.service.exception.ValidationException;
import com.javase.clientservice.facade.validation.ClientValidationContext;
import com.javase.clientservice.facade.validation.ValidationContext;

public class ClientFacade implements IClientFacade {
    /*
        client will interact with facade instead of the service. and facade takes and returns DTOs.
     */

    private static final ClientFacade INSTANCE;
    static{
        INSTANCE= new ClientFacade();
    }
    private ClientFacade(){
        clientService = ClientService.getInstance();
    }
    public static ClientFacade getInstance(){
        return INSTANCE;
    }
    private final ClientService clientService;
    private ValidationContext<ClientDto> validationContext= new ClientValidationContext();


    @Override
    public void addClient(ClientDto client) throws DuplicateClientException, ValidationException {
        validationContext.validate(client);
        clientService
                .addClient(ClientMapper.mapToClient(client));

    }

    @Override
    public <T> ClientDto getClient(T clientDetail) {
        return
                ClientMapper
                        .mapToClientDto(clientService.getClient(clientDetail));
    }

    @Override
    public ClientDto getClientById(int clientId) {
        return ClientMapper
                 .mapToClientDto(clientService.getClientById(clientId));
    }

    @Override
    public ClientDto getClientByName(String clientName) {
        return ClientMapper
                .mapToClientDto(clientService.getClientByName(clientName));
    }

    @Override
    public void updateClient(int id, ClientDto newClientDto) throws ValidationException { //TODO: you can even delete this id. control again.
        validationContext.validate(newClientDto);
        Client client= clientService.getClientById(id);
        ClientMapper.mapToClient(newClientDto, client);
        clientService.updateClientList(id, client);
    }

    @Override
    public void deleteClientById(int cliendId) {
        clientService.deleteClientById(cliendId);
    }

    @Override
    public void printAllNumbersOfClient(int cliendId) {
            clientService.printAllNumbersOfClient(cliendId);
    }

    @Override
    public void printAllClients() {
        clientService.printAllClients();
    }


}
