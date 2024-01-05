package com.javase.clientservice.view;

import com.javase.clientservice.model.*;
import com.javase.clientservice.utility.IdGeneratorUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Console implements AutoCloseable{
    //menu + get details from user for various operations needed in the service classes.
    private static final Scanner scanner= new Scanner(System.in);
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

    public Client getClientDetailsFromUser() throws ParseException {
        Client newClient = null;
        String name = (String) getUserInput("Enter Name: ", "str");
        String fiscalCode = (String) getUserInput("Enter FiscalCode: ", "str");
        String email = (String) getUserInput("Enter new Email: ", "str");
        String address = (String) getUserInput("Enter new Address: ", "str");
        boolean deleted= false;
        String passwordInput= (String) getUserInput("Enter password: ", "str");

        int n= (int) getUserInput("how many numbers do you want to add?", "int");
        List<ContactNumber> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ContactNumber number = this.getNumberDetailsFromUser();
            numbers.add(number);
        }
        char typeInput= (char)getUserInput("Enter P for Personal and L for Legal client: ", "char");
        if (typeInput == 'P') {
              newClient=
                      createPersonalClientFromInput(
                              name, fiscalCode, address, email,numbers, deleted, passwordInput);
        } else if (typeInput == 'L') {
               newClient=
                       createLegalClientFromInput(
                               name, fiscalCode, address, email, numbers, deleted, passwordInput);
        }
        scanner.nextLine();
        return newClient;
    }

    public Client getClientInfoFromUserForEdit(Client oldClient){
        String name= (String) getUserInput("Enter new Name: " , "str");
        String email= (String) getUserInput("Enter new Email: ", "str");
        String address= (String) getUserInput("Enter new Address: ", "str");

        oldClient.setName(name);
        oldClient.setEmail(email);
        oldClient.setAddress(address);

        if (oldClient instanceof PersonalClient) {
            String surname= (String) getUserInput("Enter new surname: " , "str");
            String nationality= (String) getUserInput("Enter new nationality: " , "str");

            ((PersonalClient) oldClient).setSurname(surname);
            ((PersonalClient) oldClient).setNationality(nationality);

        } else if (oldClient instanceof LegalClient) {
            String person= (String) getUserInput("enter new Contact Person: " , "String");
            String industry= (String) getUserInput("enter new Industry: ", "String");
            String website= (String) getUserInput("enter new website: ", "String");
            int count= (int) getUserInput("enter new Employee Count: " , "int");

            ((LegalClient) oldClient).setContactPerson(person);
            ((LegalClient) oldClient).setIndustry(industry);
            ((LegalClient) oldClient).setWebsite(website);
            ((LegalClient) oldClient).setEmployeeCount(count);
        }
        return oldClient;
    }
    public ContactNumber getNumberDetailsFromUser(){
        String number= (String) getUserInput("enter number: ", "str");
        char choice= (char) getUserInput("Enter number type: \n" +
                                                    "W_Work,\n" +
                                                    "H_Home,\n" +
                                                    "B_Business,\n" +
                                                    "P_Personal\n", "char");
        NumberType type;
        switch(choice){
            case 'W' -> type= NumberType.WORK;
            case 'H' -> type= NumberType.HOME;
            case 'B' -> type= NumberType.BUSINESS;
            case 'P' -> type= NumberType.PERSONAL;
            default  -> throw new NoSuchElementException("Invalid Type.Try again!");
        }
        return new ContactNumber(IdGeneratorUtil.generateUniqueNumberId(), number, type);
    }
    public int getIdFromUser (){
        int id= (int)getUserInput("enter Id: " , "int");
        return id;
    }
    public Object getClientDetailForSelection(){
        char choice=
            (char)getUserInput("How do you want to search for the client? \n" +
                                        "N.Name\n"+
                                        "I.Id\n", "char");
        if(choice == 'N'){
            String name= (String)getUserInput("enter name:", "str");
            return name;
        }else if(choice == 'I'){
            int id= (int)getUserInput("enter id: ", "int");
            return id;
        }
        return null;
    }
    public String getNewNumberToUpdate(){
       return (String) getUserInput("enter new number: \n", "str");
    }
    private PersonalClient createPersonalClientFromInput(
                    String name, String fiscalCode, String address,
                    String email, List<ContactNumber> numbers,
                    boolean deleted, String password) throws ParseException {
        scanner.nextLine();
        PersonalClient personalClient;
        String surname = (String)getUserInput("Enter surname: ", "str");
        String date = (String)getUserInput("Enter birthdate (dd-MM-yyyy): ", "str");
        Date birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        String nationality = (String)getUserInput("Enter nationality: ","str");
        scanner.nextLine();
        personalClient = new PersonalClient(IdGeneratorUtil.generateUniqueClientId(), name, surname,
                birthdate, nationality, fiscalCode, email, address, numbers, deleted, password);
        System.out.println();
        return personalClient;
    }
    private LegalClient createLegalClientFromInput(
                    String name, String fiscalCode, String address,
                    String email,  List<ContactNumber> numbers,
                    boolean deleted, String password) throws ParseException {
        LegalClient newClient= null;
        String person = (String) getUserInput("enter Contact Person: ", "str");
        String industry = (String) getUserInput("enter Industry: ", "str");
        String registrationNumber = (String) getUserInput("enter Registration Number: ", "str");
        String date = (String) getUserInput("enter Establishment Date (dd-MM-yyyy): ", "str");
        Date estDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        String website = (String) getUserInput("enter Website: " , "str");
        int count = (int) getUserInput("enter Employee Count: ","int");
        newClient = new LegalClient(IdGeneratorUtil.generateUniqueClientId(), name, person, industry, fiscalCode,
                                    registrationNumber, estDate, email, website,
                                    address, count, numbers, deleted, password);
        System.out.println();
        return newClient;
    }

    private Object getUserInput(String message, String type) {
        System.out.println(message);
        Object userInput = null;
        if (type.equalsIgnoreCase("str")) {
            userInput= scanner.nextLine();
        } else if (type.equalsIgnoreCase("int")) {
            userInput= Integer.parseInt(scanner.nextLine());
        } else if(type.equalsIgnoreCase("char")){
            userInput= scanner.next().toUpperCase().charAt(0);
        }
        return userInput;
    }

    @Override
    public void close() {
            scanner.close();
    }
}


