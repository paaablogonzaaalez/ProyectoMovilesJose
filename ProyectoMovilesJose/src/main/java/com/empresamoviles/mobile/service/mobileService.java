package com.empresamoviles.mobile.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.empresamoviles.mapper.MobileMapper;
import com.empresamoviles.mobiles.repository.MobileRepository;
import com.empresamoviles.model.Mobile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
* Servicio principal de lógica de negocio para la entidad Mobile.
* Gestiona búsquedas dinámicas, detalle, trending y comparación.
*/
@Service
@RequiredArgsConstructor
public class mobileService {

	private final MobileRepository mobileRepository;
	private final MobileMapper mobileMapper;
	
	@Transactional(readOnly = true)
	public List<MobileSummaryDTO> getTrendingMobiles() {
	    return mobileRepository.findTop5ByOrderByConsultationCountDesc()
	            .stream()
	            .map(mobileMapper::toSummaryDTO)
	            .toList();
	}
	
	@Transactional(readOnly = true)
	public MobileComparisonDTO compareMobiles(Long id1, Long id2) {
	    Mobile mobileA = mobileRepository.findById(id1)
	            .orElseThrow(() -> new ResourceNotFoundException("Móvil no encontrado con id: " + id1));
	    Mobile mobileB = mobileRepository.findById(id2)
	            .orElseThrow(() -> new ResourceNotFoundException("Móvil no encontrado con id: " + id2));

	    return MobileComparisonDTO.builder()
	            .mobileA(mobileMapper.toDetailDTO(mobileA))
	            .mobileB(mobileMapper.toDetailDTO(mobileB))
	            .build();
	}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
