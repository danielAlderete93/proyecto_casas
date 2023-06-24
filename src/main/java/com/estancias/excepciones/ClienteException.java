package com.estancias.excepciones;

public class ClienteException extends RuntimeException{
    public ClienteException() {
    }

    public ClienteException(String message) {
        super(message);
    }
}
