package com.wow.libre.infrastructure.repositories.guild_member;

import com.wow.libre.infrastructure.entities.GuildMemberEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GuildMemberRepository extends CrudRepository<GuildMemberEntity, Long> {
    @Query("SELECT COUNT(g) FROM GuildMemberEntity g WHERE g.id = ?1")
    long countById(Long id);
}
