package com.example.holamundo.dao;

import com.example.holamundo.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDao extends CrudRepository<Persona, Long> {

}
