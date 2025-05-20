package com.testGrupoCastilla.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Detalle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maestro")
    private Maestro maestro;

    private String nombre;

    private LocalDateTime fecha;

    private Double importe;
}
