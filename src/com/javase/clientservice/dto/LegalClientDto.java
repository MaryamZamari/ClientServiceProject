package com.javase.clientservice.dto;

import com.javase.clientservice.model.ClientType;
import java.util.Date;
import java.util.List;

public class LegalClientDto extends ClientDto {
    private String contactPerson;
    private String industry;
    private final String registrationNumber;
    private final Date establishmentDate;
    private String website;
    private int employeeCount;

    public LegalClientDto(Integer id, ClientType type, String name, String contactPerson, String industry,
                          String fiscalCode, String registrationNumber, Date establishmentDate, String email,
                          String website, String address, int employeeCount, List<ContactNumberDto> numbers, String password ) {
        super(id, name, fiscalCode, email, address, type, numbers, password);
        this.contactPerson = contactPerson;
        this.industry = industry;
        this.registrationNumber = registrationNumber;
        this.establishmentDate = establishmentDate;
        this.website = website;
        this.employeeCount = employeeCount;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Override
    public String toString() {
        return "LegalClientDto{" + super.toString() +
                "contactPerson='" + contactPerson + '\'' +
                ", industry='" + industry + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", website='" + website + '\'' +
                ", employeeCount=" + employeeCount +
                '}';
    }
}
