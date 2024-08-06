package com.wow.libre.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "character_services")
public class CharacterServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "character_id")
    private Long characterId;
    @Column(name = "skill_id")
    private Long skillId;
    private String name;
    private String description;
    private Double score;
    @Column(name = "public")
    private boolean isPublic;
}
