package com.wow.libre.infrastructure.repositories.guild;

import com.wow.libre.infrastructure.entities.GuildEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GuildRepository extends CrudRepository<GuildEntity, Long> {

    List<GuildEntity> findAll(Pageable pageable);

    Optional<GuildEntity> findById(@NotNull Long id);
}
