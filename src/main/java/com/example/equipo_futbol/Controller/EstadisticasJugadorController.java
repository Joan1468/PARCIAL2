package com.example.equipo_futbol.Controller;

import com.example.equipo_futbol.Model.EstadisticasJugador;
import com.example.equipo_futbol.Service.EstadisticasJugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estadisticas")
@Tag(name = "Estadísticas de Jugadores", description = "API para gestionar las estadísticas de jugadores en los partidos")
public class EstadisticasJugadorController {

    @Autowired
    private EstadisticasJugadorService estadisticasService;

    @GetMapping
    @Operation(summary = "Obtener todas las estadísticas", description = "Devuelve una lista con todas las estadísticas de los jugadores.")
    public ResponseEntity<List<EstadisticasJugador>> getAll() {
        return ResponseEntity.ok(estadisticasService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estadísticas por ID", description = "Devuelve una estadística específica según su ID.")
    public ResponseEntity<EstadisticasJugador> getById(@PathVariable Integer id) {
        Optional<EstadisticasJugador> estadistica = estadisticasService.findById(id);
        return estadistica.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Registrar nueva estadística", description = "Registra una nueva estadística de un jugador en un partido.")
    public ResponseEntity<EstadisticasJugador> create(@RequestBody EstadisticasJugador estadistica) {
        return ResponseEntity.ok(estadisticasService.save(estadistica));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estadística por ID", description = "Actualiza una estadística existente de un jugador.")
    public ResponseEntity<EstadisticasJugador> update(@PathVariable Integer id, @RequestBody EstadisticasJugador estadisticaActualizada) {
        Optional<EstadisticasJugador> estadisticaExistente = estadisticasService.findById(id);
        if (estadisticaExistente.isPresent()) {
            EstadisticasJugador estadistica = estadisticaExistente.get();

            estadistica.setJugador(estadisticaActualizada.getJugador());
            estadistica.setPartido(estadisticaActualizada.getPartido());
            estadistica.setMinutosJugados(estadisticaActualizada.getMinutosJugados());
            estadistica.setGoles(estadisticaActualizada.getGoles());
            estadistica.setAsistencias(estadisticaActualizada.getAsistencias());
            estadistica.setTarjetasAmarillas(estadisticaActualizada.getTarjetasAmarillas());
            estadistica.setTarjetasRojas(estadisticaActualizada.getTarjetasRojas());

            return ResponseEntity.ok(estadisticasService.save(estadistica));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estadística por ID", description = "Elimina la estadística correspondiente al ID proporcionado.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estadisticasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
