package com.example.equipo_futbol.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "partido")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPartido;

    private LocalDate fecha;
    private String estadio;
    private Integer golesLocal;
    private Integer golesVisita;

    @ManyToOne
    @JoinColumn(name = "equipo_local")
    @JsonBackReference
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visita")
    @JsonBackReference
    private Equipo equipoVisita;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    private List<EstadisticasJugador> estadisticas;
}
