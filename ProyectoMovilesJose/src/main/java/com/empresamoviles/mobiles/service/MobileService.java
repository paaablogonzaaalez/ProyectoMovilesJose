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
}
