package com.javase.clientservice.model;

import com.javase.clientservice.service.IdGeneratorService;
import java.util.Date;
import java.util.List;

public class LegalClient extends Client{
    private int id;
    private String contactPerson;
    private String industry;
    private String registrationNumber;
    private Date establishmentDate;
    private String website;
    private int employeeCount;

    public LegalClient(String name, String contactPerson, String industry, String fiscalCode,
                       String registrationNumber, Date establishmentDate,String email,String website,
                       String address, int employeeCount, List<ContactNumber> numbers){
        super(name, fiscalCode, email, address, numbers);
        this.id= IdGeneratorService.generateUniqueClientId();
        this.contactPerson= contactPerson;
        this.industry= industry;
        this.registrationNumber= registrationNumber;
        this.establishmentDate= establishmentDate;
        this.website= website;
        this.employeeCount= employeeCount;
    }

    public LegalClient(int id, String name, String contactPerson, String industry, String fiscalCode,
                       String registrationNumber, Date establishmentDate,String email,String website,
                       String address, int employeeCount, List<ContactNumber> numbers){
        super(name, fiscalCode, email, address, numbers);
        this.id= id;
        this.contactPerson= contactPerson;
        this.industry= industry;
        this.registrationNumber= registrationNumber;
        this.establishmentDate= establishmentDate;
        this.website= website;
        this.employeeCount= employeeCount;
    }

    @Override
    public int getId() {
        return id;
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
        return "LegalClient{" +
                "id=" + id +
                ", contactPerson='" + contactPerson + '\'' +
                ", industry='" + industry + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", establishmentDate='" + establishmentDate + '\'' +
                ", website='" + website + '\'' +
                ", employeeCount=" + employeeCount +
                '}';
    }
}
