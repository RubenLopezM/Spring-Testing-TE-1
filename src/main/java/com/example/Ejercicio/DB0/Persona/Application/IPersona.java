package com.example.Ejercicio.DB0.Persona.Application;

import com.example.Ejercicio.DB0.Exceptions.PersonNotFoundException;
import com.example.Ejercicio.DB0.Exceptions.UnprocesableException;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaInputDTO;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaOutputDTO;

import java.util.List;

public interface IPersona {
    public PersonaOutputDTO addPerson(PersonaInputDTO persona);
    public List<PersonaOutputDTO> getPersons();
    public PersonaOutputDTO setPerson(PersonaInputDTO personainputDTO, String id) throws UnprocesableException, PersonNotFoundException;
    public void deletePerson(String id) throws PersonNotFoundException, UnprocesableException;
}
