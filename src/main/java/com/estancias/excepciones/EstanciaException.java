package com.estancias.excepciones;

public class EstanciaException extends RuntimeException {
    public EstanciaException() {
    }

    public EstanciaException(String message) {
        super(message);
    }
}
