package com.example.equipo_futbol.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "entrenador")

public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idEntrenador;
    private String nombre;
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    @JsonBackReference
    private Equipo equipo;

}
