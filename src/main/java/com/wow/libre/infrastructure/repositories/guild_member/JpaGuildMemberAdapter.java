package com.wow.libre.infrastructure.repositories.guild_member;

import com.wow.libre.domain.ports.out.guild_member.ObtainGuildMember;
import com.wow.libre.domain.ports.out.guild_member.SaveGuildMember;
import com.wow.libre.infrastructure.entities.GuildMemberEntity;
import org.springframework.stereotype.Repository;

@Repository
public class JpaGuildMemberAdapter implements ObtainGuildMember, SaveGuildMember {

    private final GuildMemberRepository guildMemberRepository;

    public JpaGuildMemberAdapter(GuildMemberRepository guildMemberRepository) {
        this.guildMemberRepository = guildMemberRepository;
    }

    @Override
    public long numberMembers(Long guildId) {
        return guildMemberRepository.countById(guildId);
    }

    @Override
    public void save(GuildMemberEntity guildMember) {
        guildMemberRepository.save(guildMember);
    }
}
