package com.javase.clientservice.service;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGeneratorService {
    /*
    Used AtomicInteger in order to handle concurrency for Id, and at the same tiem decoupling it from the model,
    to keep the business logic out of the model class.
     */
    private static final AtomicInteger clientIdCounter= new AtomicInteger(1);
    private static final AtomicInteger numberIdCounter= new AtomicInteger(1);
    public static int generateUniqueClientId() {
        return clientIdCounter.getAndIncrement();
    }

    public static int generateUniqueNumberId() {
        return numberIdCounter.getAndIncrement();
    }
}
