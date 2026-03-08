package com.empresamoviles.mobiles.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	    private BigDecimal currentPrice;
	    private LocalDate releaseDate;
    

}
