package com.wow.libre.application.service.guild_member;

import com.wow.libre.domain.model.GuildMemberModel;
import com.wow.libre.domain.ports.in.guild_member.GuildMemberPort;
import com.wow.libre.domain.ports.out.guild_member.ObtainGuildMember;
import com.wow.libre.domain.ports.out.guild_member.SaveGuildMember;
import com.wow.libre.infrastructure.entities.GuildMemberEntity;
import org.springframework.stereotype.Service;

@Service
public class GuildMemberService implements GuildMemberPort {

    private final ObtainGuildMember obtainGuildMember;
    private final SaveGuildMember saveGuildMember;

    public GuildMemberService(ObtainGuildMember obtainGuildMember, SaveGuildMember saveGuildMember) {
        this.obtainGuildMember = obtainGuildMember;
        this.saveGuildMember = saveGuildMember;
    }

    @Override
    public long accountMemberGuildId(Long guildId) {
        return obtainGuildMember.numberMembers(guildId);
    }

    @Override
    public void saveGuildMember(GuildMemberModel guildMemberModel, String transactionId) {
        saveGuildMember.save(toFromModel(guildMemberModel));
    }


    private GuildMemberEntity toFromModel(GuildMemberModel guildMemberModel) {
        GuildMemberEntity memberEntity = new GuildMemberEntity();

        memberEntity.setId(guildMemberModel.guildId);
        memberEntity.setGuid(guildMemberModel.guild);
        memberEntity.setRank(guildMemberModel.rank);
        memberEntity.setPnote("");
        memberEntity.setOffnote("");
        return memberEntity;
    }
}
