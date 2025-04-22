package com.example.equipo_futbol.Controller;

import com.example.equipo_futbol.Model.Jugador;
import com.example.equipo_futbol.Service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugadores", description = "API para la gestión de jugadores de fútbol")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    @Operation(summary = "Obtener todos los jugadores", description = "Devuelve una lista de todos los jugadores registrados.")
    public ResponseEntity<List<Jugador>> getAll() {
        return ResponseEntity.ok(jugadorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un jugador por ID", description = "Devuelve la información de un jugador específico según su ID.")
    public ResponseEntity<Jugador> getById(@PathVariable Integer id) {
        Optional<Jugador> jugador = jugadorService.findById(id);
        return jugador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo jugador", description = "Registra un nuevo jugador en la base de datos.")
    public ResponseEntity<Jugador> create(@RequestBody Jugador jugador) {
        return ResponseEntity.ok(jugadorService.save(jugador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un jugador por ID", description = "Actualiza la información de un jugador existente.")
    public ResponseEntity<Jugador> update(@PathVariable Integer id, @RequestBody Jugador jugadorActualizado) {
        Optional<Jugador> jugadorExistente = jugadorService.findById(id);
        if (jugadorExistente.isPresent()) {
            Jugador jugador = jugadorExistente.get();
            jugador.setNombre(jugadorActualizado.getNombre());
            jugador.setPosicion(jugadorActualizado.getPosicion());
            jugador.setDorsal(jugadorActualizado.getDorsal());
            jugador.setFechaNac(jugadorActualizado.getFechaNac());
            jugador.setNacionalidad(jugadorActualizado.getNacionalidad());
            jugador.setEquipo(jugadorActualizado.getEquipo());

            return ResponseEntity.ok(jugadorService.save(jugador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un jugador por ID", description = "Elimina el jugador correspondiente al ID proporcionado.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        jugadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
