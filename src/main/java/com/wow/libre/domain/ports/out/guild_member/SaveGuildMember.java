package com.wow.libre.domain.ports.out.guild_member;

import com.wow.libre.infrastructure.entities.GuildMemberEntity;

public interface SaveGuildMember {
    void save(GuildMemberEntity guildMember);
}
