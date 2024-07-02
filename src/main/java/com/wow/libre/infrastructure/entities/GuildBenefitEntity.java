package com.wow.libre.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "guild_benefits")
public class GuildBenefitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guild_id")
    private Long guildId;
    @Column(name = "acquisition_date")
    private Date acquisitionDate;
    @Column(name = "expiration_date")
    private Date expirationDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "benefit_id",
            referencedColumnName = "id")
    private BenefitEntity benefitId;

}
