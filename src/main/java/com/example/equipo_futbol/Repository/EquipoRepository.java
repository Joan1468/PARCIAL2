package com.example.equipo_futbol.Repository;

import com.example.equipo_futbol.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    // Consulta nativa: Total de goles por equipo
    @Query(value = """
           SELECT SUM(ej.goles) FROM estadisticas_jugador ej 
           JOIN jugadores j ON ej.id_jugador = j.id_jugador 
           WHERE j.id_equipo = :equipoId
           """, nativeQuery = true)
    Integer sumGolesByEquipoId(@Param("equipoId") Integer equipoId);
}