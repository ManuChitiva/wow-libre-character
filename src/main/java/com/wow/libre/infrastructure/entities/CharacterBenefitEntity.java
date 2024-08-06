package com.wow.libre.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "character_benefit")
public class CharacterBenefitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "character_id")
    private Long characterId;
    @Column(name = "benefit_code")
    private String benefitCode;
    @Column(name = "transaction_id")
    private String transactionId;
}
