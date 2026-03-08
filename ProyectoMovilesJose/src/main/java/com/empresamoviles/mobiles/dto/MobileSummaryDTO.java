package com.empresamoviles.mobiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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