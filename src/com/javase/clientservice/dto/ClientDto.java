package com.javase.clientservice.dto;

import com.javase.clientservice.model.ClientType;
import java.util.List;

public abstract class ClientDto {
    private final Integer id;
    private String name;
    private String fiscalCode;
    private String email;
    private String address;
    private final ClientType type;
    private List<ContactNumberDto> numbers;
    private String password;

    public ClientDto(Integer id, String name, String fiscalCode, String email, String address, ClientType type, List<ContactNumberDto> numbers, String password) {
        this.id = (id != null) ? id : -1;
        this.name = name;
        this.fiscalCode = fiscalCode;
        this.email = email;
        this.address = address;
        this.type = type;
        this.numbers = numbers;
        this.password = password;
    }

    public Integer getId() {
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

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
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

    public ClientType getType() {
        return type;
    }

    public List<ContactNumberDto> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<ContactNumberDto> numbers) {
        this.numbers = numbers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", numbers=" + numbers +
                ", password='" + password + '\'' +
                '}';
    }
}
