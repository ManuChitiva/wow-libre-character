package com.wow.libre.domain.ports.in.guild;


import com.wow.libre.domain.dto.GuildDto;
import com.wow.libre.domain.dto.GuildsDto;

public interface GuildPort {
    GuildsDto findAll(Integer size, Integer page, String transactionId);

    GuildDto detail(Long guildId, Long accountId, String authorizationHeader, String transactionId);
}
