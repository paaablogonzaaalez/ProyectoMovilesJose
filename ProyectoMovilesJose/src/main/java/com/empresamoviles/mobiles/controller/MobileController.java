package com.empresamoviles.mobiles.controller;

import com.empresamoviles.mobiles.dto.MobileSummaryDTO;
import com.empresamoviles.mobiles.dto.MobileSearchCriteriaDTO;
import com.empresamoviles.mobiles.service.MobileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobiles")
@RequiredArgsConstructor
public class MobileController {

    private final MobileService mobileService;

    @GetMapping("/search")
    public ResponseEntity<List<MobileSummaryDTO>> searchMobiles(
            @Valid MobileSearchCriteriaDTO criteria) {
        List<MobileSummaryDTO> results = mobileService.searchMobiles(criteria);
        return ResponseEntity.ok(results);
    }
}