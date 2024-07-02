package com.wow.libre.domain.model;

import java.util.Date;

public class GuildBenefitModel {
    public final Long id;
    public final Long guildId;
    public final Date acquisitionDate;
    public final Date expirationDate;
    public final String title;
    public final String subTitle;
    public final String description;
    public final String logo;

    public GuildBenefitModel(Long id, Long guildId, Date acquisitionDate, Date expirationDate, String title,
                             String subTitle, String description, String logo) {
        this.id = id;
        this.guildId = guildId;
        this.acquisitionDate = acquisitionDate;
        this.expirationDate = expirationDate;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.logo = logo;
    }
}
