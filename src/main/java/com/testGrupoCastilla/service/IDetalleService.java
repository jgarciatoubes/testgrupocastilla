package com.testGrupoCastilla.service;

import com.testGrupoCastilla.dto.DetalleMaestroDTO;
import com.testGrupoCastilla.entity.Detalle;

import java.time.LocalDateTime;
import java.util.List;

public interface IDetalleService {

    /**
     * Crea un nuevo detalle tras realizar las validaciones necesarias.
     *
     * @param detalle - Detalle a crear
     */
    void crearDetalle(Detalle detalle);

    /**
     * Actualiza un detalle existente tras validaciones.
     *
     * @param detalle - Detalle a actualizar
     */
    void actualizarDetalle(Detalle detalle);

    /**
     * Obtiene una lista de DetalleMaestroDTO por id de maestro.
     *
     * @param maestroId - ID del maestro
     * @return lista de DetalleMaestroDTO
     */
    List<DetalleMaestroDTO> buscarPorIdMaestro(Long maestroId);

    /**
     * Obtiene los detalles que están entre dos fechas dadas.
     *
     * @param fechaInicio - fecha mínima (inclusive)
     * @param fechaFin - fecha máxima (inclusive)
     * @return lista de detalles en ese rango de fechas
     */
    List<Detalle> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
