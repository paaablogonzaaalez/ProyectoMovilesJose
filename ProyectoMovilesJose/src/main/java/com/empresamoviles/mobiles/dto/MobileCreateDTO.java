package com.empresamoviles.mobiles.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO para la creación de un nuevo móvil.
 * Todos los campos son obligatorios y validados.
 */
@Data
public class MobileCreateDTO {

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede superar 50 caracteres")
    private String brand;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 100, message = "El modelo no puede superar 100 caracteres")
    private String model;

    @NotBlank(message = "El tipo de procesador es obligatorio")
    @Size(max = 100, message = "El tipo de procesador no puede superar 100 caracteres")
    private String processorType;

    @NotNull(message = "El número de núcleos es obligatorio")
    @Min(value = 1, message = "El mínimo de núcleos es 1")
    @Max(value = 32, message = "El máximo de núcleos es 32")
    private Integer processorCores;

    @NotNull(message = "La velocidad máxima del procesador es obligatoria")
    @DecimalMin(value = "0.1", message = "La velocidad mínima del procesador es 0.1 GHz")
    private Double processorMaxSpeedGhz;

    @NotNull(message = "El almacenamiento es obligatorio")
    @Min(value = 1, message = "El almacenamiento mínimo es 1 GB")
    private Integer storageGb;

    @NotNull(message = "El tamaño de pantalla es obligatorio")
    @DecimalMin(value = "1.0", message = "El tamaño mínimo de pantalla es 1.0 pulgadas")
    private Double screenSizeInches;

    @NotBlank(message = "La tecnología de pantalla es obligatoria")
    private String screenTechnology;

    @NotNull(message = "La RAM es obligatoria")
    @Min(value = 1, message = "La RAM mínima es 1 GB")
    private Integer ramGb;

    @NotNull(message = "El alto es obligatorio")
    @DecimalMin(value = "0.1", message = "El alto mínimo es 0.1 cm")
    private Double heightCm;

    @NotNull(message = "El ancho es obligatorio")
    @DecimalMin(value = "0.1", message = "El ancho mínimo es 0.1 cm")
    private Double widthCm;

    @NotNull(message = "El grosor es obligatorio")
    @DecimalMin(value = "0.1", message = "El grosor mínimo es 0.1 cm")
    private Double thicknessCm;

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 1, message = "El peso mínimo es 1 gramo")
    private Integer weightGrams;

    @NotNull(message = "Los megapíxeles de cámara son obligatorios")
    @DecimalMin(value = "0.1", message = "Los MP mínimos son 0.1")
    private Double cameraMp;

    @NotNull(message = "La batería es obligatoria")
    @Min(value = 100, message = "La batería mínima es 100 mAh")
    private Integer batteryMah;

    @NotNull(message = "El campo NFC es obligatorio")
    private Boolean nfc;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio mínimo es 0.01")
    private BigDecimal currentPrice;

    @NotNull(message = "La fecha de lanzamiento es obligatoria")
    @PastOrPresent(message = "La fecha de lanzamiento no puede ser futura")
    private LocalDate releaseDate;
}