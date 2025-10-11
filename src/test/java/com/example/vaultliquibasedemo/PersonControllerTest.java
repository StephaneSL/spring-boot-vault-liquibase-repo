package com.example.vaultliquibasedemo;

import com.example.vaultliquibasedemo.model.Person;
import com.example.vaultliquibasedemo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldListPeople() throws Exception {
        personRepository.save(new Person("Test"));
        mockMvc.perform(get("/api/people").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
