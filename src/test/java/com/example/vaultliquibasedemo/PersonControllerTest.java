package com.example.vaultliquibasedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.vaultliquibasedemo.model.Person;
import com.example.vaultliquibasedemo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class PersonControllerTest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("appdb").withUsername("postgres").withPassword("postgres");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveAndFind() {
        Person p = new Person(); p.setName("Bob");
        Person saved = personRepository.save(p);
        assertThat(personRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldListPeople() throws Exception {
        personRepository.save(new Person("Test"));
        mockMvc.perform(get("/api/persons").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
