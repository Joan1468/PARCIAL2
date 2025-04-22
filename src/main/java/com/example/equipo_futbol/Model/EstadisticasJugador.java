package com.example.equipo_futbol.Model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "estadisticas_jugador")
public class EstadisticasJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idEstadistica;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_partido")
    private Partido partido;

    private Integer minutosJugados;

    private Integer goles;

    private Integer asistencias;

    private Integer tarjetasAmarillas;

    private Integer tarjetasRojas;


}


