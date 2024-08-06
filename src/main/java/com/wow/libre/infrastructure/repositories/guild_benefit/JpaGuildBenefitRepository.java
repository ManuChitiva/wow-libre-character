package com.wow.libre.infrastructure.repositories.guild_benefit;

import com.wow.libre.domain.ports.out.guild_benefit.ObtainGuildBenefit;
import com.wow.libre.infrastructure.entities.GuildBenefitEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class JpaGuildBenefitRepository implements ObtainGuildBenefit {

    private final GuildBenefitRepository guildBenefitRepository;

    public JpaGuildBenefitRepository(GuildBenefitRepository guildBenefitRepository) {
        this.guildBenefitRepository = guildBenefitRepository;
    }

    @Override
    public List<GuildBenefitEntity> getBenefitsAndGuildId(Long guildId, String transactionId) {
        return guildBenefitRepository.findByGuildIdAndExpirationDateAfter(guildId, new Date());
    }
}
