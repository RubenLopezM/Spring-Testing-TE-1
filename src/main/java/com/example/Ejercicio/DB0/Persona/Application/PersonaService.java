package com.example.Ejercicio.DB0.Persona.Application;

import com.example.Ejercicio.DB0.Exceptions.PersonNotFoundException;
import com.example.Ejercicio.DB0.Exceptions.UnprocesableException;
import com.example.Ejercicio.DB0.Persona.Domain.Persona;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaInputDTO;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaOutputDTO;
import com.example.Ejercicio.DB0.Persona.infrastructure.repository.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersona {

    @Autowired
    PersonaRepo personaRepo;

    @Override
    public PersonaOutputDTO addPerson(PersonaInputDTO personaInputDTO) {
        this.validar(personaInputDTO);

        PersonaOutputDTO personaoutputDTO= new PersonaOutputDTO(personaRepo.save(convertToEntity(personaInputDTO)));

        return personaoutputDTO;
    }

    @Override
    public List<PersonaOutputDTO> getPersons() {
        List<Persona> personas= personaRepo.findAll();
        List<PersonaOutputDTO> listapersonas= personas.stream().map(persona -> convertToDTO(persona))
                                                               .collect(Collectors.toList());
        return listapersonas;
    }

    @Override
    public PersonaOutputDTO setPerson(PersonaInputDTO personainputDTO, String id) throws UnprocesableException, PersonNotFoundException {
        validar(personainputDTO);
        Persona persona= personaRepo.findById(id).orElseThrow(()-> new PersonNotFoundException("No se ha encontrado la persona con id: "+ id));
        persona.setUsuario(personainputDTO.getUsuario());
        persona.setPassword(personainputDTO.getPassword());
        persona.setCity(personainputDTO.getCity());
        persona.setPersonal_email(personainputDTO.getPersonal_email());
        persona.setCompany_email(personainputDTO.getCompany_email());
        persona.setName(personainputDTO.getName());
        persona.setSurname(personainputDTO.getSurname());
        persona.setImagen_url(personainputDTO.getImagen_url());
        persona.setTermination_date(personainputDTO.getTermination_date());
        persona.setCreated_date(personainputDTO.getCreated_date());
        personaRepo.save(persona);
        return this.convertToDTO(persona);
    }

    @Override
    public void deletePerson(String id) throws PersonNotFoundException, UnprocesableException {
        Persona persona= personaRepo.findById(id).orElseThrow(()-> new PersonNotFoundException("No se ha encontrado la persona con id: "+ id));
        personaRepo.delete(persona);

    }

    private Persona convertToEntity(PersonaInputDTO personainputDTO){
        Persona persona= new Persona();
        persona.setUsuario(personainputDTO.getUsuario());
        persona.setPassword(personainputDTO.getPassword());
        persona.setCity(personainputDTO.getCity());
        persona.setPersonal_email(personainputDTO.getPersonal_email());
        persona.setActive(personainputDTO.isActive());
        persona.setCreated_date(personainputDTO.getCreated_date());
        persona.setSurname(personainputDTO.getSurname());
        persona.setName(personainputDTO.getName());
        persona.setCompany_email(personainputDTO.getCompany_email());
        persona.setImagen_url(personainputDTO.getImagen_url());
        persona.setTermination_date(personainputDTO.getTermination_date());
        return  persona;
    }

    private void validar(PersonaInputDTO personainputDTO) throws UnprocesableException {
        String usuario= personainputDTO.getUsuario();

        if (usuario==null) throw new UnprocesableException("Usuario no puede ser nulo");
        if (usuario.length()>10 || usuario.length()<6) throw new UnprocesableException("El usuario debe tener entre 6 y 10 caracteres");;
        if (personainputDTO.getPassword()==null) throw new UnprocesableException("Se debe introducir una contraseña");
        if (personainputDTO.getCreated_date()==null) throw new UnprocesableException("Se debe introducir una fecha");
    }

    private  PersonaOutputDTO convertToDTO(Persona persona){
        PersonaOutputDTO personaoutputDTO= new PersonaOutputDTO();
        personaoutputDTO.setId_persona(persona.getId_persona());
        personaoutputDTO.setUsuario(persona.getUsuario());
        personaoutputDTO.setCity(persona.getCity());
        personaoutputDTO.setPersonal_email(persona.getPersonal_email());
        personaoutputDTO.setActive(persona.isActive());
        personaoutputDTO.setCreated_date(persona.getCreated_date());
        personaoutputDTO.setSurname(persona.getSurname());
        personaoutputDTO.setName(persona.getName());
        personaoutputDTO.setCompany_email(persona.getCompany_email());
        personaoutputDTO.setImagen_url(persona.getImagen_url());
        personaoutputDTO.setTermination_date(persona.getTermination_date());
        return personaoutputDTO;
    }
}
