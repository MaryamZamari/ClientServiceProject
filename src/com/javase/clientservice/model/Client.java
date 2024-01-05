package com.javase.clientservice.model;

import com.javase.clientservice.utility.IdGeneratorUtil;
import com.javase.clientservice.utility.PasswordEncoderUtil;

import java.util.List;

public abstract class Client {
    private Integer id;
    private String name;
    private String fiscalCode;
    private String email;
    private String address;
    private List<ContactNumber> numbers;
    private boolean deleted;
    private String password;

    public Client(String name, String fiscalCode, String email,
                  String address, List<ContactNumber> numbers,
                  boolean deleted, String password){
        this.id= IdGeneratorUtil.generateUniqueClientId();
        this.name= capitaliseFirstLetter(name);
        this.fiscalCode= fiscalCode;
        this.email= email;
        this.address= address;
        this.numbers= numbers;
        this.deleted= false;
        this.password= PasswordEncoderUtil.encodePassword(password, this.id);
    }
    public Client(Integer id, String name, String fiscalCode,
                  String email, String address, List<ContactNumber> numbers,
                  boolean deleted, String password){
        this.id= id;
        this.name= capitaliseFirstLetter(name);
        this.fiscalCode= fiscalCode;
        this.email= email;
        this.address= address;
        this.numbers= numbers;
        this.deleted= false;
        this.password= PasswordEncoderUtil.encodePassword(password, this.id);
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
    public boolean getDeleted(){
        return this.deleted;
    }
    public boolean setDeleted(boolean bool){
      return this.deleted= bool;
    }
    public List<ContactNumber> getNumberList() {
        return numbers;
    }
    public String getPassword(String passwordInput) {
        return password;
    }
    public void setPassword(String password) {
        this.password = PasswordEncoderUtil.encodePassword(password, this.getId());
    }

    private static String capitaliseFirstLetter(String str){
        char firstChar= str.toUpperCase().charAt(0);
        return firstChar+ str.substring(1);
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", numberList=" + numbers +
                ", deleted=" + deleted +
                ", password=" + password +
                '}';
    }
}
