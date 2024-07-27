package com.wow.libre.infrastructure.entities.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class CharacterSkillsId implements Serializable {
    @Id
    @Column(name = "guid")
    private Long characterId;
    @Id
    private Long skill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterSkillsId that = (CharacterSkillsId) o;
        return Objects.equals(characterId, that.characterId) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId, skill);
    }
}
