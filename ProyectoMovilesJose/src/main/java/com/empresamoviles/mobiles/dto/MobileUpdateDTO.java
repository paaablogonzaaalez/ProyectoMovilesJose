package com.empresamoviles.mobiles.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para la actualización completa de un móvil existente (PUT).
 * Todos los campos son obligatorios.
 * Solo accesible por usuarios con rol ADMIN.
 */
@Data
public class MobileUpdateDTO {

    @NotBlank(message = "La marca es obligatoria")
    private String brand;

    @NotBlank(message = "El modelo es obligatorio")
    private String model;

    @NotBlank(message = "El tipo de procesador es obligatorio")
    private String processorType;

    @NotNull
    @Min(value = 1, message = "Los núcleos deben ser al menos 1")
    private Integer processorCores;

    @NotNull
    @DecimalMin(value = "0.1", message = "La velocidad debe ser positiva")
    private Double processorMaxSpeedGhz;

    @NotNull
    @Min(value = 1, message = "El almacenamiento debe ser positivo")
    private Integer storageGb;

    @NotNull
    @DecimalMin(value = "1.0", message = "El tamaño de pantalla debe ser positivo")
    private Double screenSizeInches;

    @NotBlank(message = "La tecnología de pantalla es obligatoria")
    private String screenTechnology;

    @NotNull
    @Min(value = 1, message = "La RAM debe ser positiva")
    private Integer ramGb;

    @NotNull
    @DecimalMin(value = "0.1", message = "El alto debe ser positivo")
    private Double heightCm;

    @NotNull
    @DecimalMin(value = "0.1", message = "El ancho debe ser positivo")
    private Double widthCm;

    @NotNull
    @DecimalMin(value = "0.1", message = "El grosor debe ser positivo")
    private Double thicknessCm;

    @NotNull
    @Min(value = 1, message = "El peso debe ser positivo")
    private Integer weightGrams;

    @NotNull
    @Min(value = 1, message = "Los MP de cámara deben ser positivos")
    private Integer cameraMp;

    @NotNull
    @Min(value = 100, message = "La batería debe ser mayor de 100 mAh")
    private Integer batteryMah;

    @NotNull(message = "El campo NFC es obligatorio")
    private Boolean nfc;

    @NotNull
    @DecimalMin(value = "0.01", message = "El precio debe ser positivo")
    private BigDecimal currentPrice;

    @NotNull(message = "La fecha de lanzamiento es obligatoria")
    private LocalDate releaseDate;
}