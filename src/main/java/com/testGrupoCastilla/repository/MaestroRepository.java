package com.testGrupoCastilla.repository;

import com.testGrupoCastilla.entity.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepository extends JpaRepository<Maestro, Long> {
    // Puedes agregar métodos personalizados si necesitas, por ahora nada adicional.
}
