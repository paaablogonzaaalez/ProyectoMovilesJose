package com.empresamoviles.mobiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MobileRepository extends JpaRepository<Mobile, Long>, JpaSpecificationExecutor<Mobile> {

    /**
     * Obtiene los 5 móviles más consultados, ordenados de mayor a menor.
     *
     */
    List<Mobile> findTop5ByOrderByConsultationCountDesc();
}