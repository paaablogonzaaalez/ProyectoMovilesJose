package com.empresamoviles.mobiles.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

/**
 * DTO que encapsula los criterios de búsqueda de móviles.
 * priceMin y priceMax son obligatorios.
 * El resto de campos son filtros opcionales.
 */
@Data
public class MobileSearchCriteriaDTO {

    private String brand;

    @NotNull(message = "El precio mínimo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio mínimo debe ser mayor o igual a 0")
    private BigDecimal priceMin;
    
    @NotNull(message = "El precio máximo es obligatorio")
    @Positive(message = "El precio máximo debe ser positivo")
    private BigDecimal priceMax;

    @Positive(message = "La RAM mínima debe ser un valor positivo")
    private Integer ramMin;

    @Positive(message = "La RAM máxima debe ser un valor positivo")
    private Integer ramMax;

    private Boolean nfc;

    private String screenTechnology;
}