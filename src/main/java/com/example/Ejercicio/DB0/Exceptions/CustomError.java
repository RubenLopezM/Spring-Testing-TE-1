package com.example.Ejercicio.DB0.Exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class CustomError {
    private Date timestamp;
    private int httpCode;
    private String mensaje;

    public CustomError(Date timestamp,int httpCode, String mensaje){
        super();
        this.timestamp=timestamp;
        this.httpCode=httpCode;
        this.mensaje=mensaje;
    }
}
