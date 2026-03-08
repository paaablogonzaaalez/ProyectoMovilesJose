package com.empresamoviles.mobiles.mapper;

import com.empresamoviles.mobiles.dto.MobileDetailDTO;
import com.empresamoviles.mobiles.dto.MobileSummaryDTO;
import com.empresamoviles.mobiles.dto.MobileUpdateDTO;
import com.empresamoviles.model.Mobile;

import org.springframework.stereotype.Component;

/**
 * Mapper que centraliza todas las conversiones entre
 * la entidad Mobile y sus distintos DTOs.
 */
@Component
public class MobileMapper {

    /**
     * Convierte Mobile a MobileSummaryDTO.
     * Solo incluye los campos esenciales para listados.
     */
    public MobileSummaryDTO toSummaryDTO(Mobile mobile) {
        return MobileSummaryDTO.builder()
                .id(mobile.getId())
                .brand(mobile.getBrand())
                .model(mobile.getModel())
                .currentPrice(mobile.getCurrentPrice())
                .ramGb(mobile.getRamGb())
                .storageGb(mobile.getStorageGb())
                .screenSizeInches(mobile.getScreenSizeInches())
                .screenTechnology(mobile.getScreenTechnology())
                .nfc(mobile.isNfc())
                .cameraMp(mobile.getCameraMp())
                .batteryMah(mobile.getBatteryMah())
                .build();
    }

    /**
     * Convierte Mobile a MobileDetailDTO.
     * Incluye todos los campos excepto consultationCount.
     */
    public MobileDetailDTO toDetailDTO(Mobile mobile) {
        return MobileDetailDTO.builder()
                .id(mobile.getId())
                .brand(mobile.getBrand())
                .model(mobile.getModel())
                .processorType(mobile.getProcessorType())
                .processorCores(mobile.getProcessorCores())
                .processorMaxSpeedGhz(mobile.getProcessorMaxSpeedGhz())
                .storageGb(mobile.getStorageGb())
                .screenSizeInches(mobile.getScreenSizeInches())
                .screenTechnology(mobile.getScreenTechnology())
                .ramGb(mobile.getRamGb())
                .heightCm(mobile.getHeightCm())
                .widthCm(mobile.getWidthCm())
                .thicknessCm(mobile.getThicknessCm())
                .weightGrams(mobile.getWeightGrams())
                .cameraMp(mobile.getCameraMp())
                .batteryMah(mobile.getBatteryMah())
                .nfc(mobile.isNfc())
                .currentPrice(mobile.getCurrentPrice())
                .releaseDate(mobile.getReleaseDate())
                .build();
    }
    /**
     * Actualiza los campos de una entidad Mobile a partir de MobileUpdateDTO.
     * No modifica id ni consultationCount.
     *
     * @param dto    DTO con los nuevos valores
     * @param mobile entidad existente a actualizar
     */
    public void updateEntityFromUpdateDTO(MobileUpdateDTO dto, Mobile mobile) {
        mobile.setBrand(dto.getBrand());
        mobile.setModel(dto.getModel());
        mobile.setProcessorType(dto.getProcessorType());
        mobile.setProcessorCores(dto.getProcessorCores());
        mobile.setProcessorMaxSpeedGhz(dto.getProcessorMaxSpeedGhz());
        mobile.setStorageGb(dto.getStorageGb());
        mobile.setScreenSizeInches(dto.getScreenSizeInches());
        mobile.setScreenTechnology(dto.getScreenTechnology());
        mobile.setRamGb(dto.getRamGb());
        mobile.setHeightCm(dto.getHeightCm());
        mobile.setWidthCm(dto.getWidthCm());
        mobile.setThicknessCm(dto.getThicknessCm());
        mobile.setWeightGrams(dto.getWeightGrams());
        mobile.setCameraMp(dto.getCameraMp());
        mobile.setBatteryMah(dto.getBatteryMah());
        mobile.setNfc(dto.getNfc());
        mobile.setCurrentPrice(dto.getCurrentPrice());
        mobile.setReleaseDate(dto.getReleaseDate());
    }
    
    /**
     * Convierte una entidad Mobile a MobileDetailDTO.
     * Reutilizado internamente para la comparación entre dos móviles.
     * (El método toDetailDTO ya sirve — no hay que duplicar nada)
     *
     * Uso desde el service de comparación:
     *   mobileMapper.toDetailDTO(mobile1)
     *   mobileMapper.toDetailDTO(mobile2)
     */
}