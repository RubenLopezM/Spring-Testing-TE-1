package com.example.Ejercicio.DB0;

import com.example.Ejercicio.DB0.Persona.Application.IPersona;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaOutputDTO;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.PersonaController;
import com.example.Ejercicio.DB0.Persona.infrastructure.repository.PersonaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoTestApplication {

    @Mock
    IPersona Ipersona; // Tendremos que especificar



    @InjectMocks
    PersonaController controlador;



    /**
     * Inicializo las condiciones para mockito
     */
    @BeforeTestClass
    public void putWhen()
    {
        Mockito.when(Ipersona.getPersons()).thenReturn(new ArrayList<PersonaOutputDTO>() {
        });
    }

    //con este test no estamos utilizando la base de datos.

    @Test
    @DisplayName("Controller using Mockito")
    void mockController()
    {
        List<PersonaOutputDTO>  personas=controlador.getAllpersons().getBody();
        Assertions.assertEquals(0,personas.size());
    }

}
