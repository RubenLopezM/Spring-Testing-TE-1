package com.example.Ejercicio.DB0.Persona.infrastructure.controller;

import com.example.Ejercicio.DB0.Persona.Application.IPersona;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaInputDTO;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    IPersona iPersona;

    @GetMapping("personas")
    public ResponseEntity <List<PersonaOutputDTO>> getAllpersons(){
        return new ResponseEntity<>(iPersona.getPersons(),HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<PersonaOutputDTO> addPerson(@RequestBody PersonaInputDTO personainputDTO) throws Exception{
        return new ResponseEntity<PersonaOutputDTO>(iPersona.addPerson(personainputDTO), HttpStatus.CREATED) ;

    }

    @PutMapping("personas/{id}")
    public ResponseEntity<PersonaOutputDTO> updatePerson(@PathVariable String id, @RequestBody PersonaInputDTO personaInputDTO){
        return new ResponseEntity<>(iPersona.setPerson(personaInputDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("personas/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable String id){
            iPersona.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
