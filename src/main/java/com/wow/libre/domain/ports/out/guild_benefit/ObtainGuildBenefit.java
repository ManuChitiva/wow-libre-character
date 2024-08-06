package com.wow.libre.domain.ports.out.guild_benefit;

import com.wow.libre.infrastructure.entities.GuildBenefitEntity;

import java.util.List;

public interface ObtainGuildBenefit {
    List<GuildBenefitEntity> getBenefitsAndGuildId(Long guildId, String transactionId);
}
