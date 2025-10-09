package com.example.vaultliquibasedemo.repository;

import com.example.vaultliquibasedemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
