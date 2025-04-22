package com.example.equipo_futbol.Service;

import com.example.equipo_futbol.Model.Entrenador;
import com.example.equipo_futbol.Repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    //buscar todos
    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    //buscar por id
    public Optional<Entrenador> findById(Integer id) {
        return entrenadorRepository.findById(id);
    }

    //guardar
    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    //borrar
    public void deleteById(Integer id) {
        entrenadorRepository.deleteById(id);
    }
    public Entrenador updateEntrenador(Integer id, Entrenador datos) {
        return entrenadorRepository.findById(id)
                .map(entrenadorExistente -> {
                    if (datos.getNombre() != null) {
                        entrenadorExistente.setNombre(datos.getNombre());
                    }
                    if (datos.getEspecialidad() != null) {
                        entrenadorExistente.setEspecialidad(datos.getEspecialidad());
                    }
                    // No es viable que actualizaemos equipo directamente aquí por relaciones Muchos a uno
                    return entrenadorRepository.save(entrenadorExistente);
                }).orElseThrow(() -> new RuntimeException("Entrenador con ID " + id + " no encontrado"));
    }
}