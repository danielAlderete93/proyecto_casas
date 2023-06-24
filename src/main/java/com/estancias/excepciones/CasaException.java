package com.estancias.excepciones;

public class CasaException extends RuntimeException{
    public CasaException() {
    }

    public CasaException(String message) {
        super(message);
    }
}
