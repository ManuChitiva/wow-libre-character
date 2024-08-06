package com.wow.libre.infrastructure.repositories.guild_benefit;

import com.wow.libre.infrastructure.entities.GuildBenefitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface GuildBenefitRepository extends CrudRepository<GuildBenefitEntity, Long> {
    @Query("SELECT gbe FROM GuildBenefitEntity gbe WHERE gbe.guildId = :guildId AND gbe.expirationDate > :currentDate")
    List<GuildBenefitEntity> findByGuildIdAndExpirationDateAfter(@Param("guildId") Long guildId, @Param("currentDate") Date currentDate);}
