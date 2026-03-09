package com.empresamoviles.mobiles.dto;

import lombok.*;

/**
 * DTO de resumen para listados de móviles.
 * Contiene solo los campos esenciales para mostrar en búsquedas y trending.
 * No expone la entidad Mobile directamente.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileSummaryDTO {

    private Long id;
    private String brand;
    private String model;
    private Double currentPrice;
    private Integer ramGb;
    private Integer storageGb;
    private Double screenSizeInches;
    private String screenTechnology;
    private Boolean nfc;
    private Integer cameraMp;
    private Integer batteryMah;
}