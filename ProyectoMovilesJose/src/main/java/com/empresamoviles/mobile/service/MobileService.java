package com.empresamoviles.mobile.service;

import com.empresamoviles.mobiles.dto.*;
import com.empresamoviles.mobiles.exception.BadRequestException;
import com.empresamoviles.mobiles.exception.ResourceNotFoundException;
import com.empresamoviles.mobiles.mapper.MobileMapper;
import com.empresamoviles.mobiles.repository.MobileRepository;
import com.empresamoviles.mobiles.specification.MobileSpecification;
import com.empresamoviles.model.Mobile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Servicio principal de lógica de negocio para la entidad Mobile.
* Gestiona búsquedas dinámicas, detalle, trending y comparación.
*/
@Service
@RequiredArgsConstructor
public class MobileService {

	  private final MobileRepository mobileRepository;
	    private final MobileMapper mobileMapper;

	    /**
	     * Búsqueda dinámica con filtros opcionales.
	     */
	    @Transactional
	    public List<MobileSummaryDTO> searchMobiles(MobileSearchCriteriaDTO criteria) {
	        if (criteria.getPriceMin().compareTo(criteria.getPriceMax()) > 0) {
	            throw new BadRequestException("priceMin no puede ser mayor que priceMax");
	        }
	        return mobileRepository
	                .findAll(MobileSpecification.withFilters(criteria))
	                .stream()
	                .map(mobileMapper::toSummaryDTO)
	                .toList();
	    }

	    /**
	     * Detalle de un móvil por ID. Incrementa consultationCount.
	     */
	    @Transactional
	    public MobileDetailDTO getMobileById(Long id) {
	        Mobile mobile = mobileRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Móvil no encontrado con id: " + id));
	        mobile.setConsultationCount(mobile.getConsultationCount() + 1);
	        mobileRepository.save(mobile);
	        return mobileMapper.toDetailDTO(mobile);
	    }

	    /**
	     * Los 5 móviles más consultados.
	     */
	    @Transactional
	    public List<MobileSummaryDTO> getTrendingMobiles() {
	        return mobileRepository.findTop5ByOrderByConsultationCountDesc()
	                .stream()
	                .map(mobileMapper::toSummaryDTO)
	                .toList();
	    }

	    /**
	     * Comparación entre dos móviles.
	     */
	    @Transactional
	    public MobileComparisonDTO compareMobiles(Long id1, Long id2) {
	        Mobile mobile1 = mobileRepository.findById(id1)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Móvil no encontrado con id: " + id1));
	        Mobile mobile2 = mobileRepository.findById(id2)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Móvil no encontrado con id: " + id2));

	        return MobileComparisonDTO.builder()
	                .mobile1(mobileMapper.toDetailDTO(mobile1))
	                .mobile2(mobileMapper.toDetailDTO(mobile2))
	                .build();
	    }

	    /**
	     * Crear un nuevo móvil (ADMIN).
	     */
	    @Transactional
	    public MobileDetailDTO createMobile(MobileCreateDTO createDTO) {
	        Mobile mobile = Mobile.builder()
	                .brand(createDTO.getBrand())
	                .model(createDTO.getModel())
	                .processorType(createDTO.getProcessorType())
	                .processorCores(createDTO.getProcessorCores())
	                .processorMaxSpeedGhz(createDTO.getProcessorMaxSpeedGhz())
	                .storageGb(createDTO.getStorageGb())
	                .screenSizeInches(createDTO.getScreenSizeInches())
	                .screenTechnology(createDTO.getScreenTechnology())
	                .ramGb(createDTO.getRamGb())
	                .heightCm(createDTO.getHeightCm())
	                .widthCm(createDTO.getWidthCm())
	                .thicknessCm(createDTO.getThicknessCm())
	                .weightGrams(createDTO.getWeightGrams())
	                .cameraMp(createDTO.getCameraMp())
	                .batteryMah(createDTO.getBatteryMah())
	                .nfc(createDTO.getNfc())
	                .currentPrice(createDTO.getCurrentPrice().doubleValue())
	                .releaseDate(createDTO.getReleaseDate())
	                .consultationCount(0)
	                .build();
	        return mobileMapper.toDetailDTO(mobileRepository.save(mobile));
	    }

	    /**
	     * Actualizar un móvil existente (ADMIN).
	     */
	    @Transactional
	    public MobileDetailDTO updateMobile(Long id, MobileUpdateDTO updateDTO) {
	        Mobile mobile = mobileRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Móvil no encontrado con id: " + id));
	        mobileMapper.updateEntityFromUpdateDTO(updateDTO, mobile);
	        return mobileMapper.toDetailDTO(mobileRepository.save(mobile));
	    }

	    /**
	     * Eliminar un móvil (ADMIN).
	     */
	    @Transactional
	    public void deleteMobile(Long id) {
	        if (!mobileRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Móvil no encontrado con id: " + id);
	        }
	        mobileRepository.deleteById(id);
	    }
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
