package com.example.vaultliquibasedemo.controller;

import com.example.vaultliquibasedemo.model.Person;
import com.example.vaultliquibasedemo.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> all() {
        return personRepository.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
