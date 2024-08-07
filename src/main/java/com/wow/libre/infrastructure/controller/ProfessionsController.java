package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.ProfessionServicesDto;
import com.wow.libre.domain.model.CharacterProfessionsModel;
import com.wow.libre.domain.ports.in.character_skills.CharacterSkillsPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_WEB_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/professions")
public class ProfessionsController {
    private final CharacterSkillsPort characterSkillsPort;

    public ProfessionsController(CharacterSkillsPort characterSkillsPort) {
        this.characterSkillsPort = characterSkillsPort;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<CharacterProfessionsModel>>> professions(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestParam(name = "character_id") final Long characterId) {

        List<CharacterProfessionsModel> professions = characterSkillsPort.getProfessions(characterId, transactionId);

        if (professions == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new GenericResponseBuilder<List<CharacterProfessionsModel>>
                        (transactionId).ok(professions).build());
    }

    @PostMapping(path = "services")
    public ResponseEntity<GenericResponse<Void>> createService(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT, required = false) final Long accountWebId,
            @RequestBody ProfessionServicesDto request) {

        characterSkillsPort.assignService(accountWebId, request, transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GenericResponseBuilder<Void>(transactionId).ok().build());
    }

    @PutMapping(path = "services")
    public ResponseEntity<GenericResponse<Void>> updateService(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT, required = false) final Long accountWebId,
            @RequestBody ProfessionServicesDto request) {

        characterSkillsPort.updateService(accountWebId, request, transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GenericResponseBuilder<Void>(transactionId).ok().build());
    }

}
