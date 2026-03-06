package com.empresamoviles.mapper;

import org.springframework.stereotype.Component;

/**
 * Mapper manual para conversión entre la entidad Mobile y sus DTOs.
 * No se expone la entidad fuera de esta capa.
 */
@Component
public class MobileMapper {

    /**
     * Convierte Mobile a MobileSummaryDTO (para listas y búsquedas).
     */
    public MobileSummaryDTO toSummaryDTO(Mobile mobile) {
        return MobileSummaryDTO.builder()
                .id(mobile.getId())
                .brand(mobile.getBrand())
                .model(mobile.getModel())
                .ramGb(mobile.getRamGb())
                .storageGb(mobile.getStorageGb())
                .currentPrice(mobile.getCurrentPrice())
                .screenTechnology(mobile.getScreenTechnology())
                .nfc(mobile.getNfc())
                .build();
    }

    /**
     * Convierte Mobile a MobileDetailDTO (para detalle completo).
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
                .nfc(mobile.getNfc())
                .currentPrice(mobile.getCurrentPrice())
                .releaseDate(mobile.getReleaseDate())
                .build();
    }

    /**
     * Actualiza los campos de una entidad existente desde un DTO de detalle.
     * No toca id ni consultationCount.
     */
    public void updateEntityFromDTO(MobileDetailDTO dto, Mobile entity) {
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setProcessorType(dto.getProcessorType());
        entity.setProcessorCores(dto.getProcessorCores());
        entity.setProcessorMaxSpeedGhz(dto.getProcessorMaxSpeedGhz());
        entity.setStorageGb(dto.getStorageGb());
        entity.setScreenSizeInches(dto.getScreenSizeInches());
        entity.setScreenTechnology(dto.getScreenTechnology());
        entity.setRamGb(dto.getRamGb());
        entity.setHeightCm(dto.getHeightCm());
        entity.setWidthCm(dto.getWidthCm());
        entity.setThicknessCm(dto.getThicknessCm());
        entity.setWeightGrams(dto.getWeightGrams());
        entity.setCameraMp(dto.getCameraMp());
        entity.setBatteryMah(dto.getBatteryMah());
        entity.setNfc(dto.getNfc());
        entity.setCurrentPrice(dto.getCurrentPrice());
        entity.setReleaseDate(dto.getReleaseDate());
    }
}
