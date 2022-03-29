package com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PersonaInputDTO {
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
}
