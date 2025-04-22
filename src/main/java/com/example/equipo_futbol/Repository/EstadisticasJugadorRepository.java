package com.example.equipo_futbol.Repository;

import com.example.equipo_futbol.Model.EstadisticasJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticasJugadorRepository extends JpaRepository<EstadisticasJugador, Integer> {
    // Consultas básicas heredadas de JpaRepository
}