package com.example.equipo_futbol.Service;

import com.example.equipo_futbol.Model.Jugador;
import com.example.equipo_futbol.Repository.JugadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    //buscar todos
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    //buscarpor id
    public Optional<Jugador> findById(Integer id) {
        return jugadorRepository.findById(id);
    }

    //guardar
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    //borrar
    public void deleteById(Integer id) {
        jugadorRepository.deleteById(id);
    }
    //Actualizar
    @Transactional
    public Jugador updateJugador(Integer id, Jugador datosActualizados) {
        return jugadorRepository.findById(id)
                .map(jugadorExistente -> {
                    // Actualizar campos básicos (excepto si son nulos)
                    if (datosActualizados.getNombre() != null) {
                        jugadorExistente.setNombre(datosActualizados.getNombre());
                    }
                    if (datosActualizados.getPosicion() != null) {
                        jugadorExistente.setPosicion(datosActualizados.getPosicion());
                    }
                    if (datosActualizados.getDorsal() != null) {
                        jugadorExistente.setDorsal(datosActualizados.getDorsal());
                    }
                    if (datosActualizados.getFechaNac() != null) {
                        jugadorExistente.setFechaNac(datosActualizados.getFechaNac());
                    }
                    if (datosActualizados.getNacionalidad() != null) {
                        jugadorExistente.setNacionalidad(datosActualizados.getNacionalidad());
                    }

                    return jugadorRepository.save(jugadorExistente);
                })
                .orElseThrow(() -> new RuntimeException("Jugador con ID " + id + " no encontrado"));
    }
}