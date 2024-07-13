package com.wow.libre.domain.ports.in.guild_member;

import com.wow.libre.domain.model.GuildMemberModel;

public interface GuildMemberPort {

    long accountMemberGuildId(Long guildId);

    void saveGuildMember(GuildMemberModel guildMemberModel, String transactionId);
}
