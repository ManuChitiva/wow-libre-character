package com.wow.libre.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "guild")
public class GuildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guildid")
    private Long id;

    private String name;
    @Column(name = "leaderguid")
    private Integer leaderGuid;
    @Column(name = "emblemstyle")
    private Long emblemStyle;
    @Column(name = "emblemcolor")
    private Long emblemColor;
    @Column(name = "borderstyle")
    private Long borderStyle;
    @Column(name = "bordercolor")
    private Long borderColor;
    private String info;
    private String motd;
    @Column(name = "createdate")
    private Long createDate;
    @Column(name = "bankmoney")
    private Long bankMoney;
    private String logo;
    @Column(name = "banner_primary")
    private String bannerPrimary;
    @Column(name = "banner_secondary")
    private String bannerSecondary;
    @Column(name = "public_access")
    private boolean publicAccess;
}
