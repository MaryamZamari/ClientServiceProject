package com.javase.clientservice.model;

import com.javase.clientservice.service.IdGeneratorService;

public class ContactNumber {
    private int id;
    private String number;
    private NumberType type;

    public ContactNumber(String number, NumberType type) {
        this.id= IdGeneratorService.generateUniqueNumberId();
        this.number = number;
        this.type = type;
    }

    public ContactNumber(int id, String number, NumberType type) {
        this.id= id;
        this.number = number;
        this.type = type;
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

    public NumberType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ContactNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
