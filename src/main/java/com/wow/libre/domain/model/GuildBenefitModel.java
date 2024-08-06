package com.wow.libre.domain.model;

import java.util.Date;

public record GuildBenefitModel(Long id, Long guildId, Date acquisitionDate, Date expirationDate, String title,
                                String subTitle, String description, String logo, String itemId, int quantity,
                                boolean status, String benefitCode) {
}
