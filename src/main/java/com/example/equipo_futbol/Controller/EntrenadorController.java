package com.example.equipo_futbol.Controller;

import com.example.equipo_futbol.Model.Entrenador;
import com.example.equipo_futbol.Service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(name = "Entrenadores", description = "API para la gestión de entrenadores de fútbol")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    @Operation(summary = "Obtener todos los entrenadores", description = "Devuelve una lista de todos los entrenadores registrados.")
    public ResponseEntity<List<Entrenador>> getAll() {
        return ResponseEntity.ok(entrenadorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un entrenador por ID", description = "Devuelve la información de un entrenador específico según su ID.")
    public ResponseEntity<Entrenador> getById(@PathVariable Integer id) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        return entrenador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo entrenador", description = "Crea un nuevo entrenador en la base de datos.")
    public ResponseEntity<Entrenador> create(@RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(entrenadorService.save(entrenador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un entrenador por ID", description = "Actualiza la información de un entrenador existente.")
    public ResponseEntity<Entrenador> update(@PathVariable Integer id, @RequestBody Entrenador entrenadorActualizado) {
        Optional<Entrenador> entrenadorExistente = entrenadorService.findById(id);
        if (entrenadorExistente.isPresent()) {
            Entrenador entrenador = entrenadorExistente.get();
            entrenador.setNombre(entrenadorActualizado.getNombre());
            entrenador.setEspecialidad(entrenadorActualizado.getEspecialidad());
            entrenador.setEquipo(entrenadorActualizado.getEquipo());
            return ResponseEntity.ok(entrenadorService.save(entrenador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un entrenador por ID", description = "Elimina el entrenador correspondiente al ID proporcionado.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        entrenadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
