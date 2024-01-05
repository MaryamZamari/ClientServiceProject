package com.javase.clientservice.model;

import com.javase.clientservice.utility.IdGeneratorUtil;

public class ContactNumber {
    private int id;
    private String number;
    private NumberType type;
    private boolean deleted;

    public ContactNumber(String number, NumberType type) {
        this.id= IdGeneratorUtil.generateUniqueNumberId();
        this.number = number;
        this.type = type;
        this.deleted= false;
    }

    public ContactNumber(int id, String number, NumberType type) {
        this.id= id;
        this.number = number;
        this.type = type;
        this.deleted= false;
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

    public boolean getDeleted(){
        return this.deleted;
    }

    public void setDeleted(boolean bool){
        this.deleted= bool;
    }
    @Override
    public String toString() {
        return "ContactNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", deleted= " + deleted +
                '}';
    }
}
