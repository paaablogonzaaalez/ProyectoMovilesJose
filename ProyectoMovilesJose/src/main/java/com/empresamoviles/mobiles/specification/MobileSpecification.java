package com.empresamoviles.mobiles.specification;

import com.empresamoviles.model.Mobile;
import com.empresamoviles.mobiles.dto.MobileSearchCriteriaDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de especificaciones JPA para búsquedas dinámicas sobre la entidad Mobile.
 * Implementa el patrón Specification para construcción de filtros composables.
 */
public class MobileSpecification {

    /**
     * Genera una Specification combinada a partir del DTO de criterios.
     * priceMin y priceMax son obligatorios. El resto son opcionales.
     *
     * @param criteria DTO con los filtros de búsqueda
     * @return Specification<Mobile> con todos los filtros aplicados
     */
    public static Specification<Mobile> withFilters(MobileSearchCriteriaDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtros obligatorios: rango de precio
            predicates.add(cb.greaterThanOrEqualTo(
                    root.get("currentPrice"), criteria.getPriceMin().doubleValue()));
            predicates.add(cb.lessThanOrEqualTo(
                    root.get("currentPrice"), criteria.getPriceMax().doubleValue()));

            // Filtros opcionales
            if (criteria.getBrand() != null && !criteria.getBrand().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("brand")),
                        "%" + criteria.getBrand().toLowerCase() + "%"));
            }

            if (criteria.getRamMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("ramGb"), criteria.getRamMin()));
            }

            if (criteria.getRamMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("ramGb"), criteria.getRamMax()));
            }

            if (criteria.getNfc() != null) {
                predicates.add(cb.equal(root.get("nfc"), criteria.getNfc()));
            }

            if (criteria.getScreenTechnology() != null && !criteria.getScreenTechnology().isBlank()) {
                predicates.add(cb.equal(
                        cb.lower(root.get("screenTechnology")),
                        criteria.getScreenTechnology().toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}