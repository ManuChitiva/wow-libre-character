package com.wow.libre.domain.ports.in.guild;


import com.wow.libre.domain.dto.GuildDto;
import com.wow.libre.domain.dto.GuildsDto;

public interface GuildPort {
    GuildsDto findAll(Integer size, Integer page, String transactionId);

    GuildDto detail(Long guildId, String transactionId);

    void attach(Long guildId, Long accountId, Long accountWebId, Long characterId, String transactionId);
}
