package com.javase.clientservice.view.component;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ClientType;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.model.LegalClient;
import com.javase.clientservice.utility.ScannerWrapperUtil;
import com.javase.clientservice.view.component.AbstractCustomerUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class LegalClientUI extends AbstractCustomerUI {
    public LegalClientUI(ScannerWrapperUtil scannerWrapper) {
        super(scannerWrapper);
    }

    @Override
    public void editClient(Client oldClient) {
        String person= scannerWrapper.getUserInput("enter new Contact Person: " , Function.identity());
        String industry= scannerWrapper.getUserInput("enter new Industry: ", Function.identity());
        String website= scannerWrapper.getUserInput("enter new website: ", Function.identity());
        int count= scannerWrapper.getUserInput("enter new Employee Count: " , Integer::valueOf);

        ((LegalClient) oldClient).setContactPerson(person);
        ((LegalClient) oldClient).setIndustry(industry);
        ((LegalClient) oldClient).setWebsite(website);
        ((LegalClient) oldClient).setEmployeeCount(count);
    }

    @Override
    protected Client additionalGenerateClient(int id, String name, String fiscalCode, String email, String address,
                                              boolean deleted, String passwordInput, ClientType type, List<ContactNumber> numbers) {
        String person = null;
        String industry = null;
        String registrationNumber = null;
        String date = null;
        Date estDate = null;
        String website = null;
        int count= 0;
        try{
            person = scannerWrapper.getUserInput("enter Contact Person: ", Function.identity());
            industry = scannerWrapper.getUserInput("enter Industry: ", Function.identity());
            registrationNumber = scannerWrapper.getUserInput("enter Registration Number: ", Function.identity());
            date = scannerWrapper.getUserInput("enter Establishment Date (dd-MM-yyyy): ", Function.identity());
            estDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            website = scannerWrapper.getUserInput("enter Website: " , Function.identity());
            count = scannerWrapper.getUserInput("enter Employee Count: ", Integer::valueOf);
        }catch(ParseException exception){
            exception.printStackTrace();
        }
        Client legalClient= new LegalClient(id, type, name, person, industry, fiscalCode, registrationNumber,
                                            estDate, email, website, address, count, numbers, deleted, passwordInput);
        return legalClient;
    }


}
