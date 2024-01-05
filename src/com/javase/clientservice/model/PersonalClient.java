package com.javase.clientservice.model;

import java.util.Date;
import java.util.List;

public class PersonalClient extends Client{
    private String surname;
    private Date birthday;
    private String nationality;

    public PersonalClient(int id, String name, String surname, Date birthday,
                          String nationality, String fiscalCode, String email,
                          String address, List<ContactNumber> numbers,
                          boolean deleted, String password) {
        super(id, name, fiscalCode, email, address, numbers, deleted, password);
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
                super.toString() +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
