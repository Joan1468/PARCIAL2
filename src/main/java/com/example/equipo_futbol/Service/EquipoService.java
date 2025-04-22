package com.example.equipo_futbol.Service;

import com.example.equipo_futbol.Model.Equipo;
import com.example.equipo_futbol.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    //buscar todos
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    //buscar por id
    public Optional<Equipo> findById(Integer id) {
        return equipoRepository.findById(id);
    }

    //guardar
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    //borrar
    public void deleteById(Integer id) {
        equipoRepository.deleteById(id);
    }
    public Equipo updateEquipo( Integer id, Equipo datos) {
        return equipoRepository.findById(id)
                .map(e -> {
                    e.setNombre(datos.getNombre());
                    e.setCiudad(datos.getCiudad());
                    e.setFundacion(datos.getFundacion());
                    return equipoRepository.save(e);
                }).orElseThrow(()-> new RuntimeException("Equipo no encontrado")); }

}