package com.wow.libre.domain.ports.out.guild;

import com.wow.libre.infrastructure.entities.GuildEntity;

import java.util.List;
import java.util.Optional;

public interface ObtainGuild {

    List<GuildEntity> getGuilds(Integer size, Integer page, String transactionId);

    Optional<GuildEntity> getGuild(Long guildId);
}
