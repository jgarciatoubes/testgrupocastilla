package com.testGrupoCastilla.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "DetalleMaestro",
        description = "DTO que representa el detalle junto con la descripción del maestro"
)
public class DetalleMaestroDTO {

    @NotEmpty(message = "La descripción del maestro no puede estar vacía")
    @Schema(
            description = "Descripción del maestro asociado",
            example = "Maestro A"
    )
    private String descripcion;

    @NotEmpty(message = "El nombre del detalle no puede estar vacío")
    @Schema(
            description = "Nombre del detalle",
            example = "Detalle 1"
    )
    private String nombre;

    @NotNull(message = "La fecha no puede ser nula")
    @Schema(
            description = "Fecha del detalle",
            example = "2024-01-10T14:30:00"
    )
    private LocalDateTime fecha;

    @NotNull(message = "El importe no puede ser nulo")
    @Schema(
            description = "Importe asociado al detalle",
            example = "250.75"
    )
    private Double importe;

    public DetalleMaestroDTO(String descripcion, String nombre, LocalDateTime fecha, Double importe) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.importe = importe;
    }

    public DetalleMaestroDTO() {
    }
}
