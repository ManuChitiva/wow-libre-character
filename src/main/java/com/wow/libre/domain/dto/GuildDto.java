package com.wow.libre.domain.dto;

import com.wow.libre.domain.model.GuildBenefitModel;
import com.wow.libre.domain.model.GuildModel;

import java.util.Date;
import java.util.List;

public class GuildDto extends GuildModel {
    public Cta cta;
    public List<GuildBenefitModel> benefits;

    public GuildDto(Long id, String name, String leaderName, Long emblemStyle, Long emblemColor, Long borderStyle,
                    Long borderColor, String info, String motd, Date createDate, Long bankMoney, Long members,
                    String logo, String bannerPrimary, String bannerSecondary,
                    List<GuildBenefitModel> benefits, Cta cta, boolean publicAccess) {
        super(id, name, leaderName, emblemStyle, emblemColor, borderStyle, borderColor, info, motd, createDate,
                bankMoney, members, logo, bannerPrimary, bannerSecondary, publicAccess);
        this.benefits = benefits;
        this.cta = cta;
    }

    public static class Cta {
        public String label;
        public boolean linked;

        public Cta(String label, boolean linked) {
            this.label = label;
            this.linked = linked;
        }
    }
}
