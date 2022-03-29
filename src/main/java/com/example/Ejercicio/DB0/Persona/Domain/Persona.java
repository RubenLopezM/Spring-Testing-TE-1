package com.example.Ejercicio.DB0.Persona.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_persona;
    @Column(nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String surname;
    @Column(nullable = false)
    private String company_email;
    @Column(nullable = false)
    private String personal_email;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
}
