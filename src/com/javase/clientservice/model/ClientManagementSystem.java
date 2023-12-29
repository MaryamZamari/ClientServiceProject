package com.javase.clientservice.model;

import java.util.ArrayList;
import java.util.List;

public class ClientManagementSystem {
    private List<Client> clientList= new ArrayList<>();

    public ClientManagementSystem(List<Client> clientList) {
        this.clientList = clientList;
    }

    public ClientManagementSystem() {

    }

    public List<Client> getClientList() {
        return clientList;
    }



    @Override
    public String toString() {
        return "ClientManagementSystem{" +
                "clientList=" + clientList +
                '}';
    }
}
