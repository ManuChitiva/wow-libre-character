package com.wow.libre.infrastructure.entities.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;


public class GuildMemberId implements Serializable {
    @Id
    @Column(name = "guildid")
    private Long id;
    @Id
    private Long guid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuildMemberId that = (GuildMemberId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(guid, that.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guid);
    }
}
