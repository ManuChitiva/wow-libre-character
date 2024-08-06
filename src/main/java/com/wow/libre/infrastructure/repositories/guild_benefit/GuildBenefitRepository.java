package com.wow.libre.infrastructure.repositories.guild_benefit;

import com.wow.libre.infrastructure.entities.GuildBenefitEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GuildBenefitRepository extends CrudRepository<GuildBenefitEntity, Long> {
    List<GuildBenefitEntity> findByGuildId(Long guildId);
}
