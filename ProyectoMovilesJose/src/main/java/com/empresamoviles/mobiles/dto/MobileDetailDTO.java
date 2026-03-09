package com.empresamoviles.mobiles.dto;

import lombok.*;
import java.time.LocalDate;

/**
 * DTO con el detalle completo de un móvil.
 * Incluye todos los campos de la entidad excepto consultationCount,
 * que es un dato interno y no debe exponerse al cliente.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileDetailDTO {

    private Long id;
    private String brand;
    private String model;
    private String processorType;
    private Integer processorCores;
    private Double processorMaxSpeedGhz;
    private Integer storageGb;
    private Double screenSizeInches;
    private String screenTechnology;
    private Integer ramGb;
    private Double heightCm;
    private Double widthCm;
    private Double thicknessCm;
    private Integer weightGrams;
    private Integer cameraMp;
    private Integer batteryMah;
    private Boolean nfc;
    private Double currentPrice;
    private LocalDate releaseDate;
}