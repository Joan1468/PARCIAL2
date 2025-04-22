package com.example.equipo_futbol.Repository;

import com.example.equipo_futbol.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    // Consulta nativa: Resultados con nombres de equipos
    @Query(value = """
           SELECT p.id_partido, p.fecha, p.estadio, 
                  p.goles_local, p.goles_visita,
                  el.nombre as nombre_local, 
                  ev.nombre as nombre_visita
           FROM partidos p
           JOIN equipos el ON p.equipo_local = el.id_equipo
           JOIN equipos ev ON p.equipo_visita = ev.id_equipo
           """, nativeQuery = true)
    List<Object[]> findAllPartidosConNombresEquipos();
}