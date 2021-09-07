package com.example.holamundo.service;

import com.example.holamundo.domain.Persona;

import java.util.List;

public interface PersonaService {

    public List<Persona> listarPersonas();

    public void guardar(Persona persona);

    public void eliminar (Persona persona);

    public Persona encontrarPersona(Persona persona);
}
