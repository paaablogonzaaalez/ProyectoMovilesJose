package com.empresamoviles.mobiles.service;

import com.empresamoviles.mobiles.repository.MobileRepository;

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
}
