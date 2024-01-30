package com.javase.clientservice.dto;

public class ContactNumberDto {
    private Integer id;
    private String number;


    public ContactNumberDto(String number) {
        this.id= (id != null) ? id : -1;
        this.number = number;
    }

    public ContactNumberDto(Integer id, String number) {
        this.id= (id != null) ? id : -1;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "ContactNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
               '}';
    }


}
