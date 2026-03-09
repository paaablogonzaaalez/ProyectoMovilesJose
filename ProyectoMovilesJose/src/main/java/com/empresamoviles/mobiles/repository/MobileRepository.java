package com.empresamoviles.mobiles.repository;

import com.empresamoviles.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para la entidad Mobile.
 * Extiende JpaRepository para operaciones CRUD estándar.
 * Extiende JpaSpecificationExecutor para búsquedas dinámicas con Specification.
 */
@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long>,
        JpaSpecificationExecutor<Mobile> {

    /**
     * Obtiene los 5 móviles más consultados, ordenados de mayor a menor
     * según el campo consultationCount.
     */
    List<Mobile> findTop5ByOrderByConsultationCountDesc();
}