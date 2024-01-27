package com.javase.clientservice.view.component;

import com.javase.clientservice.model.Client;
import com.javase.clientservice.model.ClientType;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.utility.IdGeneratorUtil;
import com.javase.clientservice.utility.ScannerWrapperUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class AbstractCustomerUI {
    static ScannerWrapperUtil scannerWrapper;
    private Console console = new Console();
    public AbstractCustomerUI(ScannerWrapperUtil scannerWrapper){
        this.scannerWrapper= ScannerWrapperUtil.getInstance();
    }
    public abstract void editClient(Client oldClient);
    public static AbstractCustomerUI createCustomerUI(ClientType type){
       return switch(type){
            case B ->  new LegalClientUI(scannerWrapper);
            case P ->  new PersonalClientUI(scannerWrapper);
        };
    }

    public Client generateCustomer(ClientType type){
        int id=IdGeneratorUtil.generateUniqueClientId();
        String name = scannerWrapper.getUserInput("Enter Name: ", Function.identity());
        String fiscalCode = scannerWrapper.getUserInput("Enter FiscalCode: ", Function.identity());
        String email = scannerWrapper.getUserInput("Enter new Email: ", Function.identity());
        String address = scannerWrapper.getUserInput("Enter new Address: ", Function.identity());
        boolean deleted= false;
        String passwordInput= scannerWrapper.getUserInput("Enter password: ", Function.identity());
        char choice= scannerWrapper.getUserInput("Enter number type: \n" +
                "B_Business,\n" +
                "P_Personal\n", x -> x.toUpperCase().charAt(0));
        int n= scannerWrapper.getUserInput("how many numbers do you want to add?", Integer::valueOf);
        List<ContactNumber> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ContactNumber number = console.getNumberDetailsFromUser();
            numbers.add(number);
        }
        System.out.println("client data obtained. moving on to the specific client. id is : " + id );
        return additionalGenerateClient(id, name, fiscalCode, email, address, deleted, passwordInput, type, numbers);
    }


    protected abstract Client additionalGenerateClient(int id, String name, String fiscalCode, String email, String address,
                                                       boolean deleted, String passwordInput, ClientType type, List<ContactNumber> numbers);
}
