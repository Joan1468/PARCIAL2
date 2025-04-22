package com.example.equipo_futbol.Controller;

import com.example.equipo_futbol.Model.Equipo;
import com.example.equipo_futbol.Service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Equipos", description = "API para la gestión de equipos de fútbol")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista de todos los equipos registrados.")
    public ResponseEntity<List<Equipo>> getAll() {
        return ResponseEntity.ok(equipoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por ID", description = "Devuelve la información de un equipo específico según su ID.")
    public ResponseEntity<Equipo> getById(@PathVariable Integer id) {
        Optional<Equipo> equipo = equipoService.findById(id);
        return equipo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo equipo", description = "Crea un nuevo equipo en la base de datos.")
    public ResponseEntity<Equipo> create(@RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.save(equipo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo por ID", description = "Actualiza la información del equipo con el ID proporcionado.")
    public ResponseEntity<Equipo> update(@PathVariable Integer id, @RequestBody Equipo equipoActualizado) {
        Optional<Equipo> equipoExistente = equipoService.findById(id);
        if (equipoExistente.isPresent()) {
            Equipo equipo = equipoExistente.get();
            equipo.setNombre(equipoActualizado.getNombre());
            equipo.setCiudad(equipoActualizado.getCiudad());
            equipo.setFundacion(equipoActualizado.getFundacion());

            return ResponseEntity.ok(equipoService.save(equipo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo por ID", description = "Elimina el equipo correspondiente al ID proporcionado.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
