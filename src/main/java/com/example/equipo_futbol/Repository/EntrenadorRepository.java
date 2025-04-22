package com.example.equipo_futbol.Repository;

import com.example.equipo_futbol.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
    // Consultas básicas heredadas de JpaRepository
}