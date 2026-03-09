package com.empresamoviles.mobiles.controller;

import com.empresamoviles.mobile.service.MobileService;
import com.empresamoviles.mobiles.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de móviles.
 * Expone los endpoints de la API v1.
 * No contiene lógica de negocio — delega todo al MobileService.
 * Los endpoints de escritura están protegidos por rol ADMIN.
 */
@RestController
@RequestMapping("/api/v1/mobiles")
@RequiredArgsConstructor
public class MobileController {

    private final MobileService mobileService;

    /**
     * Búsqueda dinámica de móviles con filtros opcionales.
     * GET /api/v1/mobiles/search?priceMin=&priceMax=&brand=&ramMin=&ramMax=&nfc=&screenTechnology=
     *
     */
    @GetMapping("/search")
    public ResponseEntity<List<MobileSummaryDTO>> searchMobiles(
            @Valid MobileSearchCriteriaDTO criteria) {
        return ResponseEntity.ok(mobileService.searchMobiles(criteria));
    }

    /**
     * Obtiene el detalle completo de un móvil por su ID.
     * Incrementa el contador de consultas en cada llamada.
     * GET /api/v1/mobiles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<MobileDetailDTO> getMobileById(@PathVariable Long id) {
        return ResponseEntity.ok(mobileService.getMobileById(id));
    }

    /**
     * Obtiene los 5 móviles más consultados.
     * GET /api/v1/mobiles/trending
     */
    @GetMapping("/trending")
    public ResponseEntity<List<MobileSummaryDTO>> getTrendingMobiles() {
        return ResponseEntity.ok(mobileService.getTrendingMobiles());
    }

    /**
     * Compara dos móviles por sus IDs.
     * GET /api/v1/mobiles/compare?id1=&id2=
     *
     */
    @GetMapping("/compare")
    public ResponseEntity<MobileComparisonDTO> compareMobiles(
            @RequestParam Long id1,
            @RequestParam Long id2) {
        return ResponseEntity.ok(mobileService.compareMobiles(id1, id2));
    }

    /**
     * Crea un nuevo móvil. Solo accesible por ADMIN.
     * POST /api/v1/mobiles
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MobileDetailDTO> createMobile(
            @Valid @RequestBody MobileCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mobileService.createMobile(createDTO));
    }

    /**
     * Actualiza un móvil existente. Solo accesible por ADMIN.
     * PUT /api/v1/mobiles/{id}
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MobileDetailDTO> updateMobile(
            @PathVariable Long id,
            @Valid @RequestBody MobileUpdateDTO updateDTO) {
        return ResponseEntity.ok(mobileService.updateMobile(id, updateDTO));
    }

    /**
     * Elimina un móvil por su ID. Solo accesible por ADMIN.
     * DELETE /api/v1/mobiles/{id}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        mobileService.deleteMobile(id);
        return ResponseEntity.noContent().build();
    }
}