package com.wow.libre.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "benefit")
public class BenefitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logo;
    @Column(name = "item_id")
    private String itemId;
    private Integer quantity;
    private boolean status;
    @Column(name = "link_wow_head")
    private String linkWowHead;
}
