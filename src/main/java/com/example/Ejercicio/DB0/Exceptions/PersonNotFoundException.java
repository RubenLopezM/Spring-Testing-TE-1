package com.example.Ejercicio.DB0.Exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String mensaje){
        super(mensaje);
    }
}
