package com.example.equipo_futbol.Service;

import com.example.equipo_futbol.Model.EstadisticasJugador;
import com.example.equipo_futbol.Repository.EstadisticasJugadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadisticasJugadorService {

    @Autowired
    private EstadisticasJugadorRepository estadisticasJugadorRepository;

    //buscar todos
    public List<EstadisticasJugador> findAll() {
        return estadisticasJugadorRepository.findAll();
    }

    //buscar por id
    public Optional<EstadisticasJugador> findById(Integer id) {
        return estadisticasJugadorRepository.findById(id);
    }

    //guardar
    public EstadisticasJugador save(EstadisticasJugador estadistica) {
        return estadisticasJugadorRepository.save(estadistica);
    }

    //borrar
    public void deleteById(Integer id) {
        estadisticasJugadorRepository.deleteById(id);
    }
    @Transactional
    public EstadisticasJugador updateEstadisticasJugador(Integer id, EstadisticasJugador datosActualizados) {
        return estadisticasJugadorRepository.findById(id)
                .map(estadisticaExistente -> {
                    // Actualizar campos numéricos (solo si no son null)
                    if (datosActualizados.getMinutosJugados() != null) {
                        estadisticaExistente.setMinutosJugados(datosActualizados.getMinutosJugados());
                    }
                    if (datosActualizados.getGoles() != null) {
                        estadisticaExistente.setGoles(datosActualizados.getGoles());
                    }
                    if (datosActualizados.getAsistencias() != null) {
                        estadisticaExistente.setAsistencias(datosActualizados.getAsistencias());
                    }
                    if (datosActualizados.getTarjetasAmarillas() != null) {
                        estadisticaExistente.setTarjetasAmarillas(datosActualizados.getTarjetasAmarillas());
                    }
                    if (datosActualizados.getTarjetasRojas() != null) {
                        estadisticaExistente.setTarjetasRojas(datosActualizados.getTarjetasRojas());
                    }
                    return estadisticasJugadorRepository.save(estadisticaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Estadísticas de jugador con ID " + id + " no encontradas"));
    }
}