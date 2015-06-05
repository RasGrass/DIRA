package com.rasgrass.dira.repository;

import com.rasgrass.dira.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author RasGrass
 */
@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "employee", path = "employees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {}
