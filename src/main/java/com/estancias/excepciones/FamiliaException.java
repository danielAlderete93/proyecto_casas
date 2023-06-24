package com.estancias.excepciones;

public class FamiliaException extends RuntimeException {
    public FamiliaException() {
    }

    public FamiliaException(String message) {
        super(message);
    }
}
