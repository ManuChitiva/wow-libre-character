package com.wow.libre.domain.model;

public record ProfessionServicesModel(Long id, Long characterId, Long skillId, String name, String description,
                                      Double score, boolean isPublic) {
}
