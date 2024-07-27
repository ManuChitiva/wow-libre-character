package com.wow.libre.infrastructure.repositories.character_benefit;

import com.wow.libre.infrastructure.entities.CharacterBenefitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterBenefitRepository extends CrudRepository<CharacterBenefitEntity, Long> {
    Optional<CharacterBenefitEntity> findByCharacterIdAndBenefitCode(Long characterId, String benefitCode);
}
