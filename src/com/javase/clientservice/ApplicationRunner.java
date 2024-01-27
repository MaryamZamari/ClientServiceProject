package com.javase.clientservice;

import com.javase.clientservice.view.ClientController;

public class ApplicationRunner {
    private static final ClientController controller= new ClientController();
    public static void main(String[] args){
            controller.run();
        }

}
