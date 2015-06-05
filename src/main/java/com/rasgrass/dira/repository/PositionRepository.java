package com.rasgrass.dira.repository;

import com.rasgrass.dira.domain.Position;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author RasGrass
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RepositoryRestResource(collectionResourceRel = "position", path = "positions")
public interface PositionRepository extends PagingAndSortingRepository<Position, Long> {
}
