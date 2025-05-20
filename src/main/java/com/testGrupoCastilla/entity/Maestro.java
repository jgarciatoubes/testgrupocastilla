package com.testGrupoCastilla.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Maestro extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
}
