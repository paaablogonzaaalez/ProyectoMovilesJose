package com.empresamoviles.mobile.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.empresamoviles.mobiles.repository.MobileRepository;

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
	
	//búsqueda dinámica con filtros
	/**
	 * @param filterRequest DTO con los filtros de búsqueda
	 * @param pageable 		Paginación y ordenación
	 * @return Página de MobileSumaryDTO con los resultados
	 */
	@Transactional(readOnly = true)
	public Page<MobileSummaryDTO> searchWithFilters(MobileFilterRequestDTO filterRequest, Pageable pageable) {
		Specification<Mobile> spec = MoibleSpecification.withFilter(
				filterRequest.getBrand(),
				filterRequest.getPriceMin(),
				filterRequest.getPriceMax(),
				filterRequest.getRamMax(),
				filterRequest.getRamMin(),
				filterRequest.getNfc(),
				filterRequest.getScreenTechnology()
		);
		return mobileRepository.findAll(spec, pageable)
				.map(mobileMapper::toSumaryDTO);
	}
	
	//Detalle de móvil, obtiene por ID e incrementa el contador de consultas
	//en cada llamada
	
	/**
	 * @param id 
	 * @return MobileDetailDTO
	 * @throws MobileNotFoundException
	 */
	@Transactional
	public MobileDetailDTO getDetailById(Long id) {
		Mobile mobile = MobileRepository.findById(id)
				.orElseThrow(() -> new MobileNotFoundException("Móvil no encontrado por ID" + id));
		
		//incrementar contador de consultas feeds trending
		mobile.setConsultationCount(mobile.getConsultationCount() + 1);
		mobileRepository.save(mobile);
		
	//top 5 más consultados
	/**
	* @return ordenados por popularidad
	*
	*/
	@Transactional(readOnly = true)
	public List<MobileSummaryDTO> getTrending() {
		Pageable top5 = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "consultationCount"));
		
		return mobileRepository.findAll(top5)
				.stream()
				.map(mobileMapper::toSummaryDTO)
				.toList();
		
	}
	
	// comprar dos móviles
	/**
	 * @param idA id del primer móvil
	 * @param idB otro id
	 * @return MobileCompareDTO
	 * @throws MobileNotFoundException
	 */
	@Transactional(readOnly = true)
		public MobileCompareDTO compareMobiles(Long idA, Long idB) {
			Mobile mobileA = mobileRepository.findById(idA)
					.orElseTrhow(() -> new MobileNotFoundException("Móvil A no encontrado con id: ", idA));
			Mobile mobileB = mobileRepository.findById(idB)
					.orElseThrow(() -> new MobileNotFoundException("Móvil B no encontrado por id: ", idB));
			
			return MobileCompareDTO.builder()
					.mobileA(mobileMapper.toDetailDTO(mobileA))
					.mobileB(mobileMapper.toDetailDTO(mobileB))
					.build();
		
	
	//CRUD para admin
		
	/**
	 @param detailDTO nuevo móvil
	 @return MobilDetailDTO
	 */
	@Transactional MobileDetailDTO create(MobileDetailDTO detailDTO) {
			Mobile mobile = mobilMapper.toEntity(detailDTO);
			mobile.setConsultationCount(0);
			return mobileMapper.toDetailDTO(mobileRepository.save(mobile));
	
		}
	
		/**
		 * @param id
		 * @param detailDTO nuevos datos
		 * @return móvil actualizado
		 * @throws MobileNotFoundException
		 */
	@Transactional
	public MobileDetailDTO update(Long id, MobileDetailDTO detailDTO) {
		Mobile existing = mobileREpository.findByid(id)
				.orElseThrow(() -> new MobileNotFoundException("Móvil no encontrado"));
		
		mobileMapper.updateEntityFromDTO(detailDTO, existing);
		return mobileMapper.toDetailDTO(mobileRepository.save(existing));
		
		}
	
	//elimina móvil por id
	/**
	 * @param id
	 * @throws MobileNotFoundException
	 */
	@Transactional
	public void delete(Long id) {
		if (!mobileRepository.existsById(id)) {
			throw new MobileNotFoundException("Móvil no encontrado, id: ", id);
		}
		mobileRepository.deleteById(id);
	  }
	
	/**
	 * @param pageable
	 * @return Página de MobileSummaryDTO
	 */
	@Transactional(readOnly = true)
	public Page<MobileSummaryDTO> findAll(Pageable pageable) {
		return mobileRepository.findAll(pageable)
				.map(mobileMapper::toSumaryDTO);
	}
	
	}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
