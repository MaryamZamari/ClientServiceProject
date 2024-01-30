package com.javase.clientservice.mapper;

import com.javase.clientservice.dto.ClientDto;
import com.javase.clientservice.dto.LegalClientDto;
import com.javase.clientservice.dto.PersonalClientDto;
import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.LegalClient;
import com.javase.clientservice.model.PersonalClient;
import com.javase.clientservice.utility.IdGeneratorUtil;
import java.util.ArrayList;
import java.util.List;

public class ClientMapper {
    //Client to Dto
    public static List<ClientDto> mapToClientDtoList(List<Client> clientList){
         List<ClientDto> clientDtoList = new ArrayList<>();
         for(Client client: clientList){
             clientDtoList.add(mapToClientDto(client));
         }
         return clientDtoList;
    }
    public static ClientDto mapToClientDto(Client client) {
        ClientDto clientDto= null;
        if(client instanceof LegalClient){
            clientDto= mapToClientDto((LegalClient) client);
        }else if(client instanceof PersonalClient){
            clientDto= mapToClientDto((PersonalClient) client);
        }
        return clientDto;
           }

    public static LegalClientDto mapToClientDto(LegalClient client) {
        LegalClientDto legal= new LegalClientDto(
                IdGeneratorUtil.generateUniqueClientId(),
                client.getType(),
                client.getName(),
                client.getContactPerson(),
                client.getIndustry(),
                client.getFiscalCode(),
                client.getRegistrationNumber(),
                client.getEstablishmentDate(),
                client.getEmail(),
                client.getWebsite(),
                client.getAddress(),
                client.getEmployeeCount(),
                NumberMapper.mapToDtoNumberList(client.getNumberList()),
                client.getPassword()
        );
        return legal;
    }

    public static PersonalClientDto mapToClientDto(PersonalClient client) {
        PersonalClientDto personalDto = new PersonalClientDto(
                IdGeneratorUtil.generateUniqueClientId(),
                client.getName(),
                client.getSurname(),
                client.getBirthday(),
                client.getNationality(),
                client.getFiscalCode(),
                client.getEmail(),
                client.getAddress(),
                client.getType(),
                NumberMapper.mapToDtoNumberList(client.getNumberList()),
                client.getPassword()
        );
        return personalDto;
    }

    //Dto to Client
    public static Client mapToClient(ClientDto clientDto) {
        Client client = null;
        if(clientDto instanceof LegalClientDto){
            client= mapToClient((LegalClientDto) clientDto);
        }else if(clientDto instanceof PersonalClientDto){
            client= mapToClient((PersonalClientDto) clientDto);
        }
        return client;
    }

    public static Client mapToClient(ClientDto clientDto, Client client){
        Client c = null;
        if(clientDto instanceof LegalClientDto){
            c =  mapToClient((LegalClientDto) clientDto , (LegalClient) client);
        }else if(clientDto instanceof PersonalClientDto){
            c = mapToClient((PersonalClientDto) clientDto , (PersonalClient) client);
        }
        return c;
    }

    public static LegalClient mapToClient(LegalClientDto client) {
        LegalClient legal = new LegalClient(
                IdGeneratorUtil.generateUniqueClientId(),
                client.getType(),
                client.getName(),
                client.getContactPerson(),
                client.getIndustry(),
                client.getFiscalCode(),
                client.getRegistrationNumber(),
                client.getEstablishmentDate(),
                client.getEmail(),
                client.getWebsite(),
                client.getAddress(),
                client.getEmployeeCount(),
                NumberMapper.mapToNumberList(client.getNumbers()),
                false,
                client.getPassword()
        );
        return legal;
    }

    public static LegalClient mapToClient(LegalClientDto clientDto, LegalClient client) {
                client.setName(clientDto.getName());
                client.setContactPerson(clientDto.getContactPerson());
                client.setIndustry(clientDto.getIndustry());
                client.setEmail(clientDto.getEmail());
                client.setWebsite(clientDto.getWebsite());
                client.setAddress(clientDto.getAddress());
                client.setEmployeeCount(clientDto.getEmployeeCount());
                clientDto.getNumbers();
                client.setPassword(clientDto.getPassword());
        return client;
    }

    public static PersonalClient mapToClient(PersonalClientDto client) {
        PersonalClient personal = new PersonalClient(
                IdGeneratorUtil.generateUniqueClientId(),
                client.getName(),
                client.getSurname(),
                client.getBirthday(),
                client.getNationality(),
                client.getFiscalCode(),
                client.getEmail(),
                client.getAddress(),
                client.getType(),
                NumberMapper.mapToNumberList(client.getNumbers()),
                false,
                client.getPassword()
        );
        return personal;
    }

    public static PersonalClient mapToClient(PersonalClientDto clientDto, PersonalClient client) {
                client.setName(clientDto.getName());
                client.setSurname(clientDto.getSurname());
                client.setNationality(clientDto.getNationality());
                client.setEmail(clientDto.getEmail());
                client.setAddress(clientDto.getAddress());
                client.setPassword(clientDto.getPassword());
            return client;
    }

    public static List<Client> mapToClientList(List<ClientDto> clientDtoList){
        List<Client> clientList = new ArrayList<>();
        for(ClientDto client: clientDtoList){
            clientList.add(mapToClient(client));
        }
        return clientList;
    }
}
