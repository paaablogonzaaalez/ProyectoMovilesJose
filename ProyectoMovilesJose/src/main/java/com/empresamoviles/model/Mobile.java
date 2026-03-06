package com.empresamoviles.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "mobiles", indexes = {
    @Index(name = "idx_brand",              columnList = "brand"),
    @Index(name = "idx_current_price",      columnList = "currentPrice"),
    @Index(name = "idx_consultation_count", columnList = "consultationCount")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder.Default
    private Integer consultationCount = 0;
}
