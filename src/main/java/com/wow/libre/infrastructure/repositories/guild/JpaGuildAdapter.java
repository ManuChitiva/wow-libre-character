package com.wow.libre.infrastructure.repositories.guild;

import com.wow.libre.domain.ports.out.guild.ObtainGuild;
import com.wow.libre.infrastructure.entities.GuildEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaGuildAdapter implements ObtainGuild {

    private final GuildRepository guildRepository;

    public JpaGuildAdapter(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }

    @Override
    public List<GuildEntity> getGuilds(Integer size, Integer page, String transactionId) {
        return guildRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<GuildEntity> getGuild(Long guildId) {
        return guildRepository.findById(guildId);
    }

}
