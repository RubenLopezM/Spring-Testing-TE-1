package com.example.Ejercicio.DB0;

import com.example.Ejercicio.DB0.Persona.Domain.Persona;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaInputDTO;
import com.example.Ejercicio.DB0.Persona.infrastructure.repository.PersonaRepo;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.PersonaController;
import com.example.Ejercicio.DB0.Persona.infrastructure.controller.DTO.PersonaOutputDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  //Usamos esto para que la función de inicio no sea estática.
public class MockMVCTestApplication {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonaRepo personaRepository;

    private PersonaInputDTO personaInputDTO;
    private Persona persona;
    private PersonaInputDTO persona2InputDTO;


    @BeforeAll
    public  void starting()
    {
        personaInputDTO=new PersonaInputDTO();
        personaInputDTO.setUsuario("Javier");
        personaInputDTO.setName("Test Name");
        personaInputDTO.setSurname("rublop");
        personaInputDTO.setPassword("rub1811");
        personaInputDTO.setPersonal_email("ruben@hotmail.com");
        personaInputDTO.setActive(true);
        personaInputDTO.setCompany_email("ruben@bosonit.com");
        personaInputDTO.setImagen_url("");
        personaInputDTO.setCity("Barcelona");
        personaInputDTO.setCreated_date(new Date());
        personaInputDTO.setTermination_date(new Date());



        persona2InputDTO=new PersonaInputDTO();
        persona2InputDTO.setUsuario("Crislop44");
        persona2InputDTO.setName("Cristian");
        persona2InputDTO.setSurname("rublop");
        persona2InputDTO.setPassword("crislop811");
        persona2InputDTO.setPersonal_email("cristian@hotmail.com");
        persona2InputDTO.setActive(true);
        persona2InputDTO.setCompany_email("cristian@bosonit.com");
        persona2InputDTO.setImagen_url("");
        persona2InputDTO.setCity("Barcelona");
        persona2InputDTO.setCreated_date(new Date());
        persona2InputDTO.setTermination_date(new Date());

    }



    @Test
    @DisplayName("Calling the post method")
    public void test1_create() throws Exception {

        MvcResult resultado = this.mockMvc.perform(post("/crear").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(personaInputDTO))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated()).andReturn();

        String contenido = resultado.getResponse().getContentAsString();

        PersonaOutputDTO personaOutputDto = new ObjectMapper()
                .readValue(contenido, new TypeReference<PersonaOutputDTO>() {
                });

        checkPersona(personaOutputDto);

    }

    @Test
    @DisplayName("Calling getAll method")
    void test2_mockController() throws Exception {
        MvcResult resultado  = this.mockMvc.perform(get("/personas")).andExpect(status().isOk())
                .andReturn();
        String contenido= resultado.getResponse().getContentAsString();

        List<PersonaOutputDTO> personas= new ObjectMapper().readValue(contenido, new TypeReference<List<PersonaOutputDTO>>() {   }); // Use TypeReference to map the List.

        Assertions.assertEquals(personas.size(), personaRepository.findAll().size());

    }

    @Test
    @DisplayName("Testing the put method")
    void test3_updatePersona() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/personas/{id}",1)
                        .content(new ObjectMapper().writeValueAsString(persona2InputDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario").value("Crislop44"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Cristian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personal_email").value("cristian@hotmail.com"));

    }

    @Test
    @DisplayName("Testing the delete method")
    void test4_delete() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/personas/{id}",1))
                .andExpect(status().isAccepted());

    }

    private void checkPersona(PersonaOutputDTO personaOutputDTO){
        Assertions.assertEquals(personaOutputDTO.getUsuario(),personaInputDTO.getUsuario());
        Assertions.assertEquals(personaOutputDTO.getName(),personaInputDTO.getName());
        Assertions.assertEquals(personaOutputDTO.getPersonal_email(),personaInputDTO.getPersonal_email());
        Assertions.assertEquals(personaOutputDTO.getSurname(),personaInputDTO.getSurname());
        Assertions.assertEquals(personaOutputDTO.getCompany_email(),personaInputDTO.getCompany_email());
        Assertions.assertEquals(personaOutputDTO.getCity(),personaInputDTO.getCity());
        Assertions.assertEquals(personaOutputDTO.isActive(),personaInputDTO.isActive());
        Assertions.assertEquals(personaOutputDTO.getImagen_url(),personaInputDTO.getImagen_url());
        Assertions.assertEquals(personaOutputDTO.getCreated_date(),personaInputDTO.getCreated_date());
        Assertions.assertEquals(personaOutputDTO.getTermination_date(),personaInputDTO.getTermination_date());

    }
}
