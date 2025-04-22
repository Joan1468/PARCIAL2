package com.example.equipo_futbol.Controller;

import com.example.equipo_futbol.Model.Partido;
import com.example.equipo_futbol.Service.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partidos")
@Tag(name = "Partidos", description = "API para la gestión de partidos de fútbol")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping
    @Operation(summary = "Obtener todos los partidos", description = "Devuelve una lista de todos los partidos registrados.")
    public ResponseEntity<List<Partido>> getAll() {
        return ResponseEntity.ok(partidoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un partido por ID", description = "Devuelve la información de un partido específico según su ID.")
    public ResponseEntity<Partido> getById(@PathVariable Integer id) {
        Optional<Partido> partido = partidoService.findById(id);
        return partido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo partido", description = "Registra un nuevo partido en la base de datos.")
    public ResponseEntity<Partido> create(@RequestBody Partido partido) {
        return ResponseEntity.ok(partidoService.save(partido));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un partido por ID", description = "Actualiza la información de un partido existente.")
    public ResponseEntity<Partido> update(@PathVariable Integer id, @RequestBody Partido partidoActualizado) {
        Optional<Partido> partidoExistente = partidoService.findById(id);
        if (partidoExistente.isPresent()) {
            Partido partido = partidoExistente.get();
            partido.setFecha(partidoActualizado.getFecha());
            partido.setEstadio(partidoActualizado.getEstadio());
            partido.setGolesLocal(partidoActualizado.getGolesLocal());
            partido.setGolesVisita(partidoActualizado.getGolesVisita());
            partido.setEquipoLocal(partidoActualizado.getEquipoLocal());
            partido.setEquipoVisita(partidoActualizado.getEquipoVisita());

            return ResponseEntity.ok(partidoService.save(partido));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un partido por ID", description = "Elimina el partido correspondiente al ID proporcionado.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        partidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
