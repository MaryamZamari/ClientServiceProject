package com.javase.clientservice.model;

import com.javase.clientservice.utility.IdGeneratorUtil;

import java.io.Serializable;

public class ContactNumber implements Serializable {
    private int id;
    private String number;
    private boolean deleted;

    public ContactNumber(String number) {
        this.id= IdGeneratorUtil.generateUniqueNumberId();
        this.number = number;
        this.deleted= false;
    }

    public ContactNumber(int id, String number) {
        this.id= id;
        this.number = number;
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
                ", deleted= " + deleted +
                '}';
    }
}
