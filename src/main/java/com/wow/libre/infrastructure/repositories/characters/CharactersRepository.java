package com.wow.libre.infrastructure.repositories.characters;

import com.wow.libre.infrastructure.entities.CharactersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharactersRepository extends CrudRepository<CharactersEntity, Long> {
  List<CharactersEntity> findByAccount(Long id);

  Optional<CharactersEntity> findByGuidAndAccount(Long guid, Long account);

  Optional<CharactersEntity> findByGuid(Long guid);

  @Query("SELECT c FROM CharactersEntity c WHERE  c.online=1")
  List<CharactersEntity> findCharactersAndOnline();
}
