package com.wow.libre.infrastructure.repositories.character_social;

import com.wow.libre.infrastructure.entities.CharacterSocialEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CharacterSocialRepository extends CrudRepository<CharacterSocialEntity, Long> {
  @Query("SELECT  ch FROM CharacterSocialEntity ch WHERE ch.guid = :guid")
  List<CharacterSocialEntity> findByGuid(@Param("guid") Long guid);


}
