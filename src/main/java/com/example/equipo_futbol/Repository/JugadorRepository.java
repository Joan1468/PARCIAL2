package com.example.equipo_futbol.Repository;

import com.example.equipo_futbol.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

    // Consulta nativa: Obtener todos los jugadores de un equipo específico
    @Query(value = "SELECT * FROM jugadores WHERE id_equipo = :equipoId", nativeQuery = true)
    List<Jugador> findJugadoresByEquipoId(@Param("equipoId") Integer equipoId);

    // Consulta nativa: Jugadores con más de X goles
    @Query(value = """
           SELECT j.* FROM jugadores j 
           JOIN estadisticas_jugador ej ON j.id_jugador = ej.id_jugador 
           GROUP BY j.id_jugador 
           HAVING SUM(ej.goles) > :minGoles
           """, nativeQuery = true)
    List<Jugador> findJugadoresConMasDeXGoles(@Param("minGoles") Integer minGoles);
}