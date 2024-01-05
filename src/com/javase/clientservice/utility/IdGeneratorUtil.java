package com.javase.clientservice.utility;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGeneratorUtil {
    /***
     *
     *  A utility class for IdGeneration. final keyword is used to avoid it being "subclassed", with a private constructor
     *  for avoiding getting instantiated.
        Used AtomicInteger in order to handle concurrency for Id, and at the same tiem decoupling it from the model,
        to keep the business logic out of the model class.
     */

    private static AtomicInteger clientIdCounter= new AtomicInteger(1);
    private static AtomicInteger numberIdCounter= new AtomicInteger(1);

    private IdGeneratorUtil(){

    }
    public static int generateUniqueClientId() {
        return clientIdCounter.getAndIncrement();
    }

    public static int generateUniqueNumberId() {
        return numberIdCounter.getAndIncrement();
    }
}
