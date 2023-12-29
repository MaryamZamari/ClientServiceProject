package com.javase.clientservice.model;

import com.javase.clientservice.service.IdGeneratorService;
import java.util.Date;
import java.util.List;

public class PersonalClient extends Client{
   // private int id;
    private String surname;
    private Date birthday;
    private String nationality;


    public PersonalClient(int id, String name, String surname, Date birthday,
                          String nationality, String fiscalCode, String email,
                          String address, List<ContactNumber> numbers) {
        super(id, name, fiscalCode, email, address, numbers);
        this.surname= capitaliseFirstLetter(surname);
        this.birthday= birthday;
        this.nationality= nationality;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = capitaliseFirstLetter(surname);
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

    private static String capitaliseFirstLetter(String str){
        char firstChar= str.toUpperCase().charAt(0);
        return firstChar+ str.substring(1);
    }

    @Override
    public String toString() {
                return "Client_PersonalClient{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", fiscalCode='" + super.getFiscalCode() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", address='" + super.getAddress() + '\'' +
                ", numberList=" + super.getNumberList() +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
