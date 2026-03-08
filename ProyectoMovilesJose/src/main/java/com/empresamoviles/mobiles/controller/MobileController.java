package com.empresamoviles.mobiles.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresamoviles.mobile.service.MobileService;
import com.empresamoviles.mobiles.dto.MobileCompareDTO;
import com.empresamoviles.mobiles.dto.MobileCreateDTO;
import com.empresamoviles.mobiles.dto.MobileDetailDTO;
import com.empresamoviles.mobiles.dto.MobileSearchCriteriaDTO;
import com.empresamoviles.mobiles.dto.MobileSummaryDTO;
import com.empresamoviles.mobiles.dto.MobileUpdateDTO;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobiles")
@RequiredArgsConstructor
public class MobileController {

    private final MobileService mobileService = new MobileService();

    @GetMapping("/search")
    public ResponseEntity<List<MobileSummaryDTO>> searchMobiles(
            @Valid MobileSearchCriteriaDTO criteria) {
        return ResponseEntity.ok(mobileService.searchMobiles(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobileDetailDTO> getMobileById(@PathVariable Long id) {
    	MobileDetailDTO detail = mobileService.getMobileById(id);
        return ResponseEntity.ok(detail);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<MobileSummaryDTO>> getTrendingMobiles() {
        return ResponseEntity.ok(mobileService.getTrendingMobiles());
    }

    @GetMapping("/compare")
    public ResponseEntity<MobileCompareDTO> compareMobiles(
            @RequestParam Long id1,
            @RequestParam Long id2) {
        return ResponseEntity.ok(mobileService.compareMobiles(id1, id2));
    }

    @PostMapping
    public ResponseEntity<MobileDetailDTO> createMobile(
            @Valid @RequestBody MobileCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mobileService.createMobile(createDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MobileDetailDTO> updateMobile(
            @PathVariable Long id,
            @Valid @RequestBody MobileUpdateDTO updateDTO) {
        return ResponseEntity.ok(mobileService.updateMobile(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        mobileService.deleteMobile(id);
        return ResponseEntity.noContent().build();
    }
}