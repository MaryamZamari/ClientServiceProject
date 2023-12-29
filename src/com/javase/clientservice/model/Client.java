package com.javase.clientservice.model;

import com.javase.clientservice.service.IdGeneratorService;
import java.util.List;

public abstract class Client {
    private int id;
    private String name;
    private String fiscalCode;
    private String email;
    private String address;
    private List<ContactNumber> numbers;

    public Client(String name, String fiscalCode, String email, String address, List<ContactNumber> numbers){
        this.id= IdGeneratorService.generateUniqueClientId();
        this.name= capitaliseFirstLetter(name);
        this.fiscalCode= fiscalCode;
        this.email= email;
        this.address= address;
        this.numbers= numbers;
    }
    public Client(int id, String name, String fiscalCode, String email, String address, List<ContactNumber> numbers){
        this.id= id;
        this.name= capitaliseFirstLetter(name);
        this.fiscalCode= fiscalCode;
        this.email= email;
        this.address= address;
        this.numbers= numbers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ContactNumber> getNumberList() {
        return numbers;
    }

    private static String capitaliseFirstLetter(String str){
        char firstChar= str.toUpperCase().charAt(0);
        return firstChar+ str.substring(1);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", numberList=" + numbers +
                '}';
    }
}
