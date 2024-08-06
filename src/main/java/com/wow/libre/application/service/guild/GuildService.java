package com.wow.libre.application.service.guild;

import com.wow.libre.domain.dto.GuildDto;
import com.wow.libre.domain.dto.GuildsDto;
import com.wow.libre.domain.enums.CommandsTrinityCore;
import com.wow.libre.domain.exception.InternalException;
import com.wow.libre.domain.exception.NotFoundException;
import com.wow.libre.domain.model.CharacterDetail;
import com.wow.libre.domain.model.GuildBenefitModel;
import com.wow.libre.domain.model.GuildModel;
import com.wow.libre.domain.model.ItemQuantityModel;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.ports.in.guild.GuildPort;
import com.wow.libre.domain.ports.in.guild_benefits.GuildBenefitsPort;
import com.wow.libre.domain.ports.in.guild_member.GuildMemberPort;
import com.wow.libre.domain.ports.out.guild.ObtainGuild;
import com.wow.libre.infrastructure.client.TrinityCoreClient;
import com.wow.libre.infrastructure.entities.GuildEntity;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GuildService implements GuildPort {
    private final ObtainGuild obtainGuild;
    private final GuildMemberPort guildMemberPort;
    private final CharactersPort charactersPort;
    private final GuildBenefitsPort guildBenefitsPort;
    private final TrinityCoreClient trinityCoreClient;

    public GuildService(ObtainGuild obtainGuild, GuildMemberPort guildMemberPort, CharactersPort charactersPort,
                        GuildBenefitsPort guildBenefitsPort, TrinityCoreClient trinityCoreClient) {
        this.obtainGuild = obtainGuild;
        this.guildMemberPort = guildMemberPort;
        this.charactersPort = charactersPort;
        this.guildBenefitsPort = guildBenefitsPort;
        this.trinityCoreClient = trinityCoreClient;
    }

    @Override
    public GuildsDto findAll(Integer size, Integer page, String transactionId) {

        GuildsDto guilds = new GuildsDto();
        List<GuildEntity> listGuilds = obtainGuild.getGuilds(size, page, transactionId);

        guilds.setGuilds(listGuilds.stream().map(this::mapToModel).toList());
        guilds.setSize(listGuilds.size());
        return guilds;
    }

    @Override
    public GuildDto detail(Long guildId, String transactionId) {

        Optional<GuildModel> getGuild = obtainGuild.getGuild(guildId).map(this::mapToModel);

        if (getGuild.isEmpty()) {
            throw new NotFoundException("The requested guild does not exist", transactionId);
        }

        GuildModel guild = getGuild.get();

        List<GuildBenefitModel> benefits = getGuildBenefit(guildId, transactionId);

        return new GuildDto(guild.id, guild.name, guild.leaderName, guild.emblemStyle, guild.emblemColor,
                guild.borderStyle, guild.borderColor, guild.info, guild.motd, guild.createDate, guild.bankMoney,
                guild.members, guild.logo, guild.bannerPrimary, guild.bannerSecondary, benefits, null,
                guild.publicAccess);
    }

    private List<GuildBenefitModel> getGuildBenefit(Long guildId, String transactionId) {
        return guildBenefitsPort.getBenefits(guildId, transactionId).stream()
                .filter(benefit -> new Date().before(benefit.expirationDate)).toList();
    }

    @Override
    public void attach(Long guildId, Long accountId, Long accountWebId, Long characterId, String transactionId) {

        Optional<GuildModel> getGuild = obtainGuild.getGuild(guildId).map(this::mapToModel);

        if (getGuild.isEmpty()) {
            throw new NotFoundException("The requested guild does not exist", transactionId);
        }
        GuildModel guild = getGuild.get();

        if (!guild.publicAccess) {
            throw new InternalException("The brotherhood is currently not public", transactionId);
        }

        CharacterDetail character = charactersPort.getCharacter(characterId, accountId, accountWebId, "");

        if (character == null) {
            throw new NotFoundException("The requested characters does not exist", transactionId);
        }

        List<ItemQuantityModel> items =
                getGuildBenefit(guildId, transactionId).stream().map(benefit -> new ItemQuantityModel(benefit.itemId,
                        benefit.quantity)).toList();

        final String message = String.format("%s Te da la bienvenida.", guild.name);
        final String body = guild.motd;

        try {
            trinityCoreClient.executeCommand(CommandsTrinityCore.invite(character.getName(), getGuild.get().name));
            if (!items.isEmpty()) {
                trinityCoreClient.executeCommand(CommandsTrinityCore.sendItems(character.getName(), message,
                        body, items));
            } else {
                trinityCoreClient.executeCommand(CommandsTrinityCore.sendMail(character.getName(), message,
                        body));
            }
        } catch (SoapFaultClientException | JAXBException e) {
            throw new InternalException("The request to join the brotherhood could not be made, please check if " +
                    "you" +
                    " already belong to it", transactionId);
        }
    }


    private GuildModel mapToModel(GuildEntity guildEntity) {
        Date dateCreate = Date.from(Instant.ofEpochMilli(guildEntity.getCreateDate() * 1000));
        long members = guildMemberPort.accountMemberGuildId(guildEntity.getId());
        CharacterDetail character = charactersPort.getCharacter(Long.valueOf(guildEntity.getLeaderGuid()), "");

        return new GuildModel(guildEntity.getId(), guildEntity.getName(), character.getName(),
                guildEntity.getEmblemStyle(), guildEntity.getEmblemColor(), guildEntity.getBorderStyle(),
                guildEntity.getBorderColor(), guildEntity.getInfo(), guildEntity.getMotd(),
                dateCreate, guildEntity.getBankMoney(), members, guildEntity.getLogo(),
                guildEntity.getBannerPrimary(), guildEntity.getBannerSecondary(), guildEntity.isPublicAccess());
    }
}
