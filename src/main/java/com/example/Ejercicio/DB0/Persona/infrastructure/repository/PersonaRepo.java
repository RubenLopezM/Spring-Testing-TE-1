package com.example.Ejercicio.DB0.Persona.infrastructure.repository;

import com.example.Ejercicio.DB0.Persona.Domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepo extends JpaRepository<Persona,String> {
}
