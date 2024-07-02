package com.wow.libre.infrastructure.repositories.guild_member;

import com.wow.libre.domain.ports.out.guild_member.ObtainGuildMember;
import org.springframework.stereotype.Repository;

@Repository
public class JpaGuildMemberAdapter implements ObtainGuildMember {

    private final GuildMemberRepository guildMemberRepository;

    public JpaGuildMemberAdapter(GuildMemberRepository guildMemberRepository) {
        this.guildMemberRepository = guildMemberRepository;
    }

    @Override
    public long numberMembers(Long guildId) {
        return guildMemberRepository.countById(guildId);
    }
}
