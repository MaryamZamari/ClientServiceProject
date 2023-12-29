package com.javase.clientservice.view;

import com.javase.clientservice.model.*;
import com.javase.clientservice.service.IdGeneratorService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Console {
    //menu + get details from user for various operations needed in the service classes.
    public void printMenu() {
        System.out.println();
        System.out.println("Welcome to Client Management Portal! \n" +
                "--- select a menu item: --- \n" +
                "0.Exit\n" +
                "1.Add a new client.\n" +
                "2.Search a client \n" +
                "3.Edit a client\n" +
                "4.Removing a client \n" +
                "5.Printing all the Clients.\n" +
                "6.Number Menu _ Functionalities related to numbers of a client \n"
        );
    }

    public void printNumberActionMenu() {  //nestedSwitch will be
        System.out.println("----Number Actions Menu------\n" +
                "X.Exit \n" +
                "A.Add a number \n" +
                "B.Get numbers of the contact by id \n" +
                "C.Delete a number \n" +
                "D.Update a number \n"
        );
    }

    public Client getClientDetailsFromUser(Scanner input) throws ParseException {
        Client newClient = null;
        System.out.println("Enter Name: ");
        String name = input.nextLine();
        System.out.println("Enter FiscalCode: ");
        String fiscalCode = input.nextLine();
        System.out.println("Enter new Email: ");
        String email = input.nextLine();
        System.out.println("Enter new Address: ");
        String address = input.nextLine();

        System.out.println("how many numbers do you want to add?");
        int n = parseInt(input.next());
        input.nextLine();
        List<ContactNumber> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ContactNumber number = this.getNumberDetailsFromUser(input);
            numbers.add(number);
        }
        System.out.println("Enter P for Personal and L for Legal client: ");
        char typeInput = input.next().toUpperCase().charAt(0);
        input.nextLine();
        if (typeInput == 'P') {
              newClient= createPersonalClientFromInput(name, fiscalCode, address, email,numbers, input);
        } else if (typeInput == 'L') {
               newClient= createLegalClientFromInput(name, fiscalCode, address, email, numbers, input);
        }
        input.nextLine();
        return newClient;
    }

    public Client getClientInfoFromUserForEdit(Scanner input, Client oldClient) throws ParseException {
        System.out.println("Enter new Name: ");
        String name = input.nextLine();
        System.out.println("Enter new Email: ");
        String email = input.nextLine();
        System.out.println("Enter new Address: ");
        String address = input.nextLine();
        input.nextLine();

        oldClient.setName(name);
        oldClient.setEmail(email);
        oldClient.setAddress(address);

        if (oldClient instanceof PersonalClient) {
            System.out.println("Enter new surname: ");
            String surname = input.nextLine();
            System.out.println("Enter new nationality: ");
            String nationality = input.next();
            input.nextLine();
            ((PersonalClient) oldClient).setSurname(surname);
            ((PersonalClient) oldClient).setNationality(nationality);

        } else if (oldClient instanceof LegalClient) {
            System.out.println("enter new Contact Person: ");
            String person = input.next();
            System.out.println("enter new Industry: ");
            String industry = input.next();
            System.out.println("enter new website: ");
            String website = input.next();
            System.out.println("enter new Employee Count: ");
            int count = Integer.parseInt(input.next());
            input.nextLine();

            ((LegalClient) oldClient).setContactPerson(person);
            ((LegalClient) oldClient).setIndustry(industry);
            ((LegalClient) oldClient).setWebsite(website);
            ((LegalClient) oldClient).setEmployeeCount(count);
        }
        return oldClient;
    }

    public ContactNumber getNumberDetailsFromUser (Scanner input){
        System.out.println("enter number: \n");
        String number= input.nextLine();
        System.out.println("Enter number type: \n" +
                            "W_Work,\n" +
                            "H_Home,\n" +
                            "B_Business,\n" +
                            "P_Personal\n");
        char choice= input.nextLine().toUpperCase().charAt(0);
        NumberType type;
        switch(choice){
            case 'W' -> type= NumberType.WORK;
            case 'H' -> type= NumberType.HOME;
            case 'B' -> type= NumberType.BUSINESS;
            case 'P' -> type= NumberType.PERSONAL;
            default  -> throw new NoSuchElementException("Invalid Type.Try again!");
        }
        return new ContactNumber(IdGeneratorService.generateUniqueNumberId(), number, type);
    }
    public int getIdFromUser (Scanner input){
        System.out.println("enter Id: \n");
        int id = Integer.parseInt(input.nextLine());
        return id;
    }
    public Object getClientDetailForSelection(Scanner input){
        System.out.println("How do you want to search for the client? \n" +
                            "N.Name\n"+
                            "I.Id\n");
        char choice= input.nextLine().toUpperCase().charAt(0);
        if(choice == 'N'){
            System.out.println("enter name:");
            String name= input.nextLine();
            return name;
        }else if(choice == 'I'){
            System.out.println("enter id: ");
            int id= Integer.parseInt(input.nextLine());
            return id;
        }
        return null;
    }
    public String getNewNumberToUpdate(Scanner input){
        System.out.println("enter new number: \n");
        return input.nextLine();
    }
    private PersonalClient createPersonalClientFromInput (String name, String fiscalCode, String address, String email, List<ContactNumber> numbers, Scanner input) throws ParseException {
        PersonalClient personalClient;
        System.out.println("Enter surname: ");
        String surname = input.nextLine();
        System.out.println("Enter birthdate (dd-MM-yyyy): ");
        String date = input.nextLine();
        Date birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        System.out.println("Enter nationality: ");
        String nationality = input.next();
        personalClient = new PersonalClient(IdGeneratorService.generateUniqueClientId(), name, surname,
                birthdate, nationality, fiscalCode, email, address, numbers);
        System.out.println("Personal client: " + personalClient.toString());
        System.out.println();
        return personalClient;
    }

    private LegalClient createLegalClientFromInput(String name, String fiscalCode, String address, String email,  List<ContactNumber> numbers, Scanner input) throws ParseException {
        LegalClient newClient= null;
        System.out.println("enter Contact Person: ");
        String person = input.next();
        System.out.println("enter Industry: ");
        String industry = input.next();
        System.out.println("enter Registration Number: ");
        String registrationNumber = input.next();
        System.out.println("enter Establishment Date (dd-MM-yyyy): ");
        String date = input.next();
        Date estDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        System.out.println("enter Website: ");
        String website = input.next();
        System.out.println("enter Employee Count: ");
        int count = Integer.parseInt(input.next());
        newClient = new LegalClient(IdGeneratorService.generateUniqueClientId(), name, person, industry, fiscalCode,
                registrationNumber, estDate, email, website,
                address, count, numbers);
        System.out.println();
        return newClient;
    }



}//class level


