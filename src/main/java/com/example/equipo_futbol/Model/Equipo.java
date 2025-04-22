package com.example.equipo_futbol.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idequipo;
    private String nombre;
    private String ciudad;
    private LocalDate fundacion;

    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores;

    @OneToMany(mappedBy = "equipo")
    private List<Entrenador> entrenadores;
}
