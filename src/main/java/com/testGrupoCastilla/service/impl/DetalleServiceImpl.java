package com.testGrupoCastilla.service.impl;

import com.testGrupoCastilla.dto.DetalleMaestroDTO;
import com.testGrupoCastilla.entity.Detalle;
import com.testGrupoCastilla.entity.Maestro;
import com.testGrupoCastilla.exception.ResourceNotFoundException;
import com.testGrupoCastilla.repository.DetalleRepository;
import com.testGrupoCastilla.repository.MaestroRepository;
import com.testGrupoCastilla.service.IDetalleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DetalleServiceImpl implements IDetalleService {

    private final DetalleRepository detalleRepository;
    private final MaestroRepository maestroRepository;

    @Override
    public void crearDetalle(Detalle detalle) {
        validarDetalle(detalle, null);
        detalleRepository.save(detalle);
    }

    @Override
    public void actualizarDetalle(Detalle detalle) {
        if (detalle.getId() == null) {
            throw new IllegalArgumentException("El ID del detalle es obligatorio para actualizar.");
        }
        validarDetalle(detalle, detalle.getId());
        detalleRepository.save(detalle);
    }

    @Override
    public List<DetalleMaestroDTO> buscarPorIdMaestro(Long maestroId) {
        List<Detalle> detalles = detalleRepository.findByMaestroId(maestroId);
        Maestro maestro = maestroRepository.findById(maestroId).orElseThrow(
                () -> new ResourceNotFoundException("Maestro", "id", maestroId.toString()));

        return detalles.stream()
                .map(d -> new DetalleMaestroDTO(maestro.getDescripcion(), d.getNombre(), d.getFecha(), d.getImporte()))
                .toList();
    }

    @Override
    public List<Detalle> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return detalleRepository.findAll().stream()
                .filter(d -> !d.getFecha().isBefore(fechaInicio) && !d.getFecha().isAfter(fechaFin))
                .toList();
    }

    private void validarDetalle(Detalle detalle, Long idActual) {
        Long maestroId = detalle.getMaestro().getId();

        boolean nombreDuplicado = (idActual == null)
                ? !detalleRepository.findByNombreAndMaestroId(detalle.getNombre(), maestroId).isEmpty()
                : !detalleRepository.findByNombreAndMaestroIdAndIdNot(detalle.getNombre(), maestroId, idActual).isEmpty();

        if (nombreDuplicado) {
            throw new IllegalArgumentException("Ya existe un detalle con ese nombre para el mismo maestro.");
        }

        Maestro maestro = maestroRepository.findById(maestroId).orElseThrow(
                () -> new ResourceNotFoundException("Maestro", "id", maestroId.toString()));

        if (detalle.getFecha().isBefore(maestro.getFechaAlta())) {
            throw new IllegalArgumentException("La fecha del detalle no puede ser anterior a la fecha de alta del maestro.");
        }
    }
}
