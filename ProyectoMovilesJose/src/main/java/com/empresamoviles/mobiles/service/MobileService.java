package com.empresamoviles.mobiles.service;

import java.util.List;
import java.util.stream.Collectors;

import com.empresamoviles.mobiles.repository.MobileRepository;

import jakarta.transaction.Transactional;

public class MobileService {
	private final MobileRepository mobileRepository;
    private final MobileMapper mobileMapper;


    /**
     * Busca móviles aplicando filtros dinámicos mediante Specification.
     *
     * @param criteria DTO con los criterios de búsqueda
     * @return lista de MobileSummaryDTO que cumplen los filtros
     */
    @Transactional(readOnly = true)
    public List<MobileSummaryDTO> searchMobiles(MobileSearchCriteriaDTO criteria) {
        return mobileRepository
                .findAll(MobileSpecification.withFilters(criteria))
                .stream()
                .map(mobileMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene el detalle completo de un móvil por su ID.
     * Incrementa el contador de consultas cada vez que se accede.
     *
     * @param id identificador del móvil
     * @return MobileDetailDTO con todos los campos del móvil
     * @throws ResourceNotFoundException si el móvil no existe
     */
    @Transactional
    public MobileDetailDTO getMobileById(Long id) {
        Mobile mobile = mobileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Mobile not found with id: " + id));

        // Incrementar el contador de consultas
        mobile.setConsultationCount(mobile.getConsultationCount() + 1);
        mobileRepository.save(mobile);

        return mobileMapper.toDetailDTO(mobile);
    }
    
    /**
     * Obtiene los 5 móviles más consultados.
     *
     * @return lista de MobileSummaryDTO ordenada por consultationCount desc
     */
    @Transactional(readOnly = true)
    public List<MobileSummaryDTO> getTrendingMobiles() {
        return mobileRepository.findTop5ByOrderByConsultationCountDesc()
                .stream()
                .map(mobileMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
