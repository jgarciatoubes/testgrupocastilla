package com.testGrupoCastilla.repository;

import com.testGrupoCastilla.entity.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {

    List<Detalle> findByMaestroId(Long maestroId);

    List<Detalle> findByNombreAndMaestroIdAndIdNot(String nombre, Long maestroId, Long id);

    List<Detalle> findByNombreAndMaestroId(String nombre, Long maestroId);
}
