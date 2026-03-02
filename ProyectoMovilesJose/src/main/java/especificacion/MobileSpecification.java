package especificacion;

import especificacion.model.Mobile;
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
     * Genera una Specification combinada a partir de los filtros recibidos.
     * priceMin y priceMax son obligatorios. El resto son opcionales.
     *
     * @param brand           Marca del móvil 
     * @param priceMin        Precio mínimo (obligatorio)
     * @param priceMax        Precio máximo (obligatorio)
     * @param ramMin          RAM mínima en GB 
     * @param ramMax          RAM máxima en GB 
     * @param nfc             Soporte NFC 
     * @param screenTechnology Tecnología de pantalla 
     * @return Specification<Mobile> con todos los filtros aplicados
     */
    public static Specification<Mobile> withFilters(
            String brand,
            Double priceMin,
            Double priceMax,
            Integer ramMin,
            Integer ramMax,
            Boolean nfc,
            String screenTechnology
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro obligatorio: rango de precio
            predicates.add(cb.greaterThanOrEqualTo(root.get("currentPrice"), priceMin));
            predicates.add(cb.lessThanOrEqualTo(root.get("currentPrice"), priceMax));

            // Filtros opcionales
            if (brand != null && !brand.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            }

            if (ramMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("ramGb"), ramMin));
            }

            if (ramMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("ramGb"), ramMax));
            }

            if (nfc != null) {
                predicates.add(cb.equal(root.get("nfc"), nfc));
            }

            if (screenTechnology != null && !screenTechnology.isBlank()) {
                predicates.add(cb.equal(
                        cb.lower(root.get("screenTechnology")),
                        screenTechnology.toLowerCase()
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}