package com.wow.libre.infrastructure.repositories.benefit;

import com.wow.libre.infrastructure.entities.BenefitEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BenefitRepository extends CrudRepository<BenefitEntity, Long> {
    List<BenefitEntity> findByStatusIsTrue();
}
