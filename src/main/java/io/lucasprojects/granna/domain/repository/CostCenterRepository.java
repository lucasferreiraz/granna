package io.lucasprojects.granna.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.lucasprojects.granna.domain.model.CostCenter;

public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    
}
