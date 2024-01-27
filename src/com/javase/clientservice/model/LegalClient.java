package com.javase.clientservice.model;

import java.util.Date;
import java.util.List;

public class LegalClient extends Client{
    private String contactPerson;
    private String industry;
    private String registrationNumber;
    private Date establishmentDate;
    private String website;
    private int employeeCount;

    public LegalClient(int id, ClientType type, String name, String contactPerson, String industry,
                       String fiscalCode, String registrationNumber, Date establishmentDate,
                       String email, String website, String address, int employeeCount,
                       List<ContactNumber> numbers, boolean deleted, String password){
        super(id, type, name, fiscalCode, email, address, numbers, deleted, password);
        this.contactPerson= contactPerson;
        this.industry= industry;
        this.registrationNumber= registrationNumber;
        this.establishmentDate= establishmentDate;
        this.website= website;
        this.employeeCount= employeeCount;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Override
    public String toString() {
        return "Client_LegalClient{" +
                super.toString() +
                ", contactPerson='" + contactPerson + '\'' +
                ", industry='" + industry + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", establishmentDate='" + establishmentDate + '\'' +
                ", website='" + website + '\'' +
                ", employeeCount=" + employeeCount +
                '}';
    }
}
