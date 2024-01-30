package com.javase.clientservice.dto;

import com.javase.clientservice.model.ClientType;
import java.util.Date;
import java.util.List;

public class PersonalClientDto extends ClientDto{
    private String surname;
    private Date birthday;
    private String nationality;
    public PersonalClientDto(Integer id, String name, String surname, Date birthday,
                             String nationality, String fiscalCode, String email,
                             String address, ClientType type, List<ContactNumberDto> numbers, String password) {
        super(id, name, fiscalCode, email, address, type,  numbers, password);
        this.surname= surname;
        this.birthday= birthday;
        this.nationality= nationality;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Date getBirthday() {
        return birthday;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality= nationality;
    }


    @Override
    public String toString() {
        return "Client_PersonalClientDto{" +
                super.toString() +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
