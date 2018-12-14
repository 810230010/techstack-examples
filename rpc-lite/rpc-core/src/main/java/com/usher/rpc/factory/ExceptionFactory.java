package com.usher.rpc.factory;

import javax.persistence.PersistenceException;

public class ExceptionFactory {
    public RuntimeException newException(String message){
        return new PersistenceException(message);
    }
}
