package com.wow.libre.application.service.guild_member;

import com.wow.libre.domain.ports.in.guild_member.GuildMemberPort;
import com.wow.libre.domain.ports.out.guild_member.ObtainGuildMember;
import org.springframework.stereotype.Service;

@Service
public class GuildMemberService implements GuildMemberPort {

    private final ObtainGuildMember obtainGuildMember;

    public GuildMemberService(ObtainGuildMember obtainGuildMember) {
        this.obtainGuildMember = obtainGuildMember;
    }

    @Override
    public long accountMemberGuildId(Long guildId) {
        return obtainGuildMember.numberMembers(guildId);
    }
}
