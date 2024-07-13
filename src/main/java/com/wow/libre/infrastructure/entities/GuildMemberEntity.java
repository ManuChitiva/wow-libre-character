package com.wow.libre.infrastructure.entities;

import com.wow.libre.infrastructure.entities.dto.GuildMemberId;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@IdClass(GuildMemberId.class)
@Data
@Entity
@Table(name = "guild_member")
public class GuildMemberEntity implements Serializable {
    @Id
    @Column(name = "guildid")
    private Long id;
    @Id
    private Long guid;
    @Column(name = "`rank`") // Escaping reserved keyword
    private Integer rank;
    private String pnote;
    private String offnote;

}
