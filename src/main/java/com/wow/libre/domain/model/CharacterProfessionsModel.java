package com.wow.libre.domain.model;

public record CharacterProfessionsModel(int id, String logo, String name, Long value, Long max,
                                        ProfessionServicesModel service) {

}
