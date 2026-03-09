package com.empresamoviles.mobiles.controller;

import com.empresamoviles.mobile.service.MobileService;
import com.empresamoviles.mobiles.dto.MobileComparisonDTO;
import com.empresamoviles.mobiles.dto.MobileCreateDTO;
import com.empresamoviles.mobiles.dto.MobileDetailDTO;
import com.empresamoviles.mobiles.dto.MobileSearchCriteriaDTO;
import com.empresamoviles.mobiles.dto.MobileSummaryDTO;
import com.empresamoviles.mobiles.dto.MobileUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de móviles.
 * No contiene lógica de negocio, delega todo al servicio.
 */
@RestController
@RequestMapping("/api/v1/mobiles")
@RequiredArgsConstructor
public class MobileController {

    private final MobileService mobileService;

    @GetMapping("/search")
    public ResponseEntity<List<MobileSummaryDTO>> searchMobiles(
            @Valid MobileSearchCriteriaDTO criteria) {
        return ResponseEntity.ok(mobileService.searchMobiles(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobileDetailDTO> getMobileById(@PathVariable Long id) {
        return ResponseEntity.ok(mobileService.getMobileById(id));
    }

    @GetMapping("/trending")
    public ResponseEntity<List<MobileSummaryDTO>> getTrendingMobiles() {
        return ResponseEntity.ok(mobileService.getTrendingMobiles());
    }

    @GetMapping("/compare")
    public ResponseEntity<MobileComparisonDTO> compareMobiles(
            @RequestParam Long id1,
            @RequestParam Long id2) {
        return ResponseEntity.ok(mobileService.compareMobiles(id1, id2));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MobileDetailDTO> createMobile(
            @Valid @RequestBody MobileCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mobileService.createMobile(createDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MobileDetailDTO> updateMobile(
            @PathVariable Long id,
            @Valid @RequestBody MobileUpdateDTO updateDTO) {
        return ResponseEntity.ok(mobileService.updateMobile(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        mobileService.deleteMobile(id);
        return ResponseEntity.noContent().build();
    }
}