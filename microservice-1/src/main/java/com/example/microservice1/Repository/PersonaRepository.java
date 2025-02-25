package com.example.microservice1.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.microservice1.Model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
  
}