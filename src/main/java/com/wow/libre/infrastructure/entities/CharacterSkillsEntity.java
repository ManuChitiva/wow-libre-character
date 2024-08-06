package com.wow.libre.infrastructure.entities;

import com.wow.libre.infrastructure.entities.dto.CharacterSkillsId;
import jakarta.persistence.*;
import lombok.Data;

@IdClass(CharacterSkillsId.class)
@Data
@Entity
@Table(name = "character_skills")
public class CharacterSkillsEntity {
    @Id
    @Column(name = "guid")
    private Long characterId;
    @Id
    private Long skill;
    private Long value;
    private Long max;
}
