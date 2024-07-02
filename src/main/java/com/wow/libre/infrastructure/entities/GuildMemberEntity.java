package com.wow.libre.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "guild_member")
public class GuildMemberEntity {
    @Id
    @Column(name = "guildid")
    private Long id;
    private String guid;
    private Integer rank;
    private String pnote;
    private String offnote;

}
