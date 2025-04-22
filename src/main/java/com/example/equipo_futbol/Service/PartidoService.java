package com.example.equipo_futbol.Service;

import com.example.equipo_futbol.Model.Partido;
import com.example.equipo_futbol.Repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    //buscar todos
    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }

    //buscar por id
    public Optional<Partido> findById(Integer id) {
        return partidoRepository.findById(id);
    }

    //guardar
    public Partido save(Partido partido) {
        return partidoRepository.save(partido);
    }

    //borrar
    public void deleteById(Integer id) {
        partidoRepository.deleteById(id);
    }
}