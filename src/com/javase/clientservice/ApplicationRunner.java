package com.javase.clientservice;

import com.javase.clientservice.controller.ClientController;

public class ApplicationRunner {
    private static ClientController controller= new ClientController();

    public static void main(String[] args) {
        controller.run();
    }
}
