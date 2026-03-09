package com.empresamoviles.mobiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que encapsula la comparación entre dos móviles.
 * Contiene el detalle completo de cada uno.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileComparisonDTO {

    private MobileDetailDTO mobile1;
    private MobileDetailDTO mobile2;
}