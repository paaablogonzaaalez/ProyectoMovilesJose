package com.empresamoviles.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entidad JPA que representa un móvil en la base de datos.
 * Tabla: mobiles
 * Incluye índices para optimizar búsquedas por marca, precio y popularidad.
 */
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

 
    @Column(nullable = false, length = 50)
    private String brand;


    @Column(nullable = false, length = 100)
    private String model;

    @Column(nullable = false, length = 100)
    private String processorType;

    @Column(nullable = false)
    private Integer processorCores;

    @Column(nullable = false)
    private Double processorMaxSpeedGhz;

    @Column(nullable = false)
    private Integer storageGb;

    @Column(nullable = false)
    private Double screenSizeInches;

    @Column(nullable = false, length = 50)
    private String screenTechnology;

    @Column(nullable = false)
    private Integer ramGb;

    @Column(nullable = false)
    private Double heightCm;

    @Column(nullable = false)
    private Double widthCm;

    @Column(nullable = false)
    private Double thicknessCm;

    @Column(nullable = false)
    private Integer weightGrams;

    @Column(nullable = false)
    private Integer cameraMp;

    @Column(nullable = false)
    private Integer batteryMah;

    @Column(nullable = false)
    private Boolean nfc;

    @Column(nullable = false)
    private Double currentPrice;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    @Builder.Default
    private Integer consultationCount = 0;
}