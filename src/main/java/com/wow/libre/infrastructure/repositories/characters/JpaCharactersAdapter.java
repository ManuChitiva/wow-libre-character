package com.wow.libre.infrastructure.repositories.characters;

import com.wow.libre.domain.model.Character;
import com.wow.libre.domain.ports.out.characters.ObtainCharacters;
import com.wow.libre.infrastructure.entities.CharactersEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaCharactersAdapter implements ObtainCharacters {
  private final CharactersRepository charactersRepository;

  public JpaCharactersAdapter(CharactersRepository charactersRepository) {
    this.charactersRepository = charactersRepository;
  }

  @Override
  public List<Character> getCharacters(Long accountId, String transactionId) {
    return charactersRepository
        .findByAccount(accountId).stream()
        .filter(Objects::nonNull).map(this::mapToModel).toList();
  }

  @Override
  public Optional<Character> getCharacter(Long characterId, Long accountId, String transactionId) {
    return charactersRepository.findByGuidAndAccount(characterId, accountId).map(this::mapToModel);
  }

  @Override
  public Optional<Character> getCharacter(Long characterId, String transactionId) {
    return charactersRepository.findByGuid(characterId).map(this::mapToModel);
  }

  @Override
  public List<Character> getCharactersOnline(String transactionId) {
    return charactersRepository.findCharactersAndOnline().stream().map(this::mapToModel).collect(Collectors.toList());
  }

  private Character mapToModel(CharactersEntity characters) {
    return Character.builder()
        .guid(characters.getGuid()).account(characters.getAccount())
        .name(characters.getName()).race(characters.getRace())
        .classCharacters(characters.getClassCharacters()).gender(characters.getGender())
        .level(characters.getLevel()).xp(characters.getXp())
        .money(characters.getMoney()).skin(characters.getSkin())
        .face(characters.getFace()).hairStyle(characters.getHairStyle())
        .hairColor(characters.getHairColor()).facialStyle(characters.getFacialStyle())
        .bankSlots(characters.getBankSlots()).restState(characters.getRestState())
        .playerFlags(characters.getPlayerFlags()).positionX(characters.getPositionX())
        .positionY(characters.getPositionY()).positionZ(characters.getPositionZ())
        .map(characters.getMap()).instanceId(characters.getInstanceId())
        .instanceModeMask(characters.getInstanceModeMask()).orientation(characters.getOrientation())
        .taximask(characters.getTaximask()).online(characters.getOnline())
        .cinematic(characters.getCinematic()).totalTime(characters.getTotalTime())
        .levelTime(characters.getLevelTime()).logoutTime(characters.getLogoutTime())
        .isLogoutResting(characters.getIsLogoutResting()).restBonus(characters.getRestBonus())
        .resetTalentsCost(characters.getResetTalentsCost())
        .resetTalentsTime(characters.getResetTalentsTime()).transX(characters.getTransX())
        .transY(characters.getTransY()).transZ(characters.getTransZ()).transO(characters.getTransO())
        .extraFlags(characters.getExtraFlags()).atLogin(characters.getAtLogin())
        .zone(characters.getZone()).deathExpireTime(characters.getDeathExpireTime())
        .taxiPath(characters.getTaxiPath()).arenaPoints(characters.getArenaPoints())
        .totalHonorPoints(characters.getTotalHonorPoints())
        .todayHonorPoints(characters.getTodayHonorPoints())
        .yesterdayKills(characters.getYesterdayKills())
        .totalKills(characters.getTotalKills())
        .todayKills(characters.getTodayKills())
        .chosenTitle(characters.getChosenTitle())
        .knownCurrencies(characters.getKnownCurrencies())
        .watchedFaction(characters.getWatchedFaction())
        .drunk(characters.getDrunk())
        .health(characters.getHealth()).power1(characters.getPower1()).power2(characters.getPower2())
        .power3(characters.getPower3())
        .power4(characters.getPower4())
        .power5(characters.getPower5())
        .power6(characters.getPower6())
        .power7(characters.getPower7())
        .talentGroupsCount(characters.getTalentGroupsCount())
        .activeTalentGroup(characters.getActiveTalentGroup())
        .exploredZones(characters.getExploredZones())
        .equipmentCache(characters.getEquipmentCache())
        .ammold(characters.getAmmoId())
        .knownTitles(characters.getKnownTitles())
        .actionsBars(characters.getActionBars())
        .grantableLevels(characters.getGrantableLevels())
        .build();
  }
}
