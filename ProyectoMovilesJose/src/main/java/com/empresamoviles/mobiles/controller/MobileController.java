package com.empresamoviles.mobiles.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresamoviles.mobile.service.MobileService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mobiles")
@RequiredArgsConstructor
public class MobileController {

    private final MobileService mobileService;

    /** GET /api/v1/mobiles/search — Búsqueda dinámica */
    @GetMapping("/search")
    public ResponseEntity<List<MobileSummaryDTO>> searchMobiles(
            @Valid MobileSearchCriteriaDTO criteria) {
        return ResponseEntity.ok(mobileService.searchMobiles(criteria));
    }

    /** GET /api/v1/mobiles/{id} — Detalle de un móvil */
    @GetMapping("/{id}")
    public ResponseEntity<MobileDetailDTO> getMobileById(@PathVariable Long id) {
        return ResponseEntity.ok(mobileService.getMobileById(id));
    }

    /** GET /api/v1/mobiles/trending — Top 5 más consultados */
    @GetMapping("/trending")
    public ResponseEntity<List<MobileSummaryDTO>> getTrendingMobiles() {
        return ResponseEntity.ok(mobileService.getTrendingMobiles());
    }

    /** GET /api/v1/mobiles/compare?id1=&id2= — Comparar dos móviles */
    @GetMapping("/compare")
    public ResponseEntity<MobileComparisonDTO> compareMobiles(
            @RequestParam Long id1,
            @RequestParam Long id2) {
        return ResponseEntity.ok(mobileService.compareMobiles(id1, id2));
    }

    /** POST /api/v1/mobiles — Crear móvil (ADMIN) */
    @PostMapping
    public ResponseEntity<MobileDetailDTO> createMobile(
            @Valid @RequestBody MobileCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mobileService.createMobile(createDTO));
    }

    /** PUT /api/v1/mobiles/{id} — Actualizar móvil (ADMIN) */
    @PutMapping("/{id}")
    public ResponseEntity<MobileDetailDTO> updateMobile(
            @PathVariable Long id,
            @Valid @RequestBody MobileUpdateDTO updateDTO) {
        return ResponseEntity.ok(mobileService.updateMobile(id, updateDTO));
    }

    /** DELETE /api/v1/mobiles/{id} — Eliminar móvil (ADMIN) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        mobileService.deleteMobile(id);
        return ResponseEntity.noContent().build();
    }
}