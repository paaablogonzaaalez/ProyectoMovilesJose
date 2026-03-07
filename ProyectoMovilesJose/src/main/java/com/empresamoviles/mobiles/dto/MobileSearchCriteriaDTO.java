package com.empresamoviles.mobiles.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
