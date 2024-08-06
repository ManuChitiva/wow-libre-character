/* ACCOUNT WEB */
/*
  DESCRIPTION : Responsible for managing the system's web services.
*/

ALTER TABLE characters.guild
    ADD COLUMN public_access boolean DEFAULT TRUE;


CREATE TABLE characters.benefit
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    logo          text,
    title         varchar(255)          NOT NULL,
    sub_title     TEXT                  NOT NULL,
    description   TEXT,
    item_id       VARCHAR(30)           NOT NULL,
    quantity      int                   NOT NULL,
    status        boolean               NOT NULL,
    link_wow_head text                  NOT NULL,
    benefit_code  varchar(50)           NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT title_uq UNIQUE (benefit_code)
);

INSERT INTO characters.benefit (title, logo, sub_title, description, item_id, quantity, status, link_wow_head,
                                benefit_code)
VALUES ('Mas De 500 Miembros', "", "Mas De 500 Miembros",
        "Al unirte a esta hermandad podras reclamar el beneficio de los 500 miembros.", 52200, 1, true,
        "https://www.wowhead.com/wotlk/es/item=52200/riendas-del-destrero-de-la-muerte-carmes%C3%AD", "001");

INSERT INTO characters.benefit (title, logo, sub_title, description, item_id, quantity, status, link_wow_head,
                                benefit_code)
VALUES ('Banco Con Todas Las Casillas', "", "Banco Con Todas Las Casillas",
        "Al unirte a esta hermandad, podrás disfrutar del beneficio de contar con un compañero", 49284, 1, true,
        "https://www.wowhead.com/wotlk/es/item=49284/riendas-del-tigre-espectral-presto", "002");

INSERT INTO characters.benefit (title, logo, sub_title, description, item_id, quantity, status, link_wow_head,
                                benefit_code)
VALUES ('Acumulador', "", "Acumulador", "Al unirte a esta hermandad, podras reclamar el beneficio de una montura",
        50250, 1,
        true, "https://www.wowhead.com/wotlk/es/item=50250/rompecorazones-x-45", "003");



CREATE TABLE characters.guild_benefits
(
    id               bigint auto_increment NOT NULL,
    guild_id         bigint                NOT NULL,
    acquisition_date DATE                  NOT NULL,
    expiration_date  DATE                  NOT NULL,
    benefit_id       bigint                not null,
    PRIMARY KEY (id),
    constraint fk_benefit_id FOREIGN key (benefit_id) references characters.benefit (id)
);



alter table characters.guild
    add column banner_primary   text,
    add column banner_secondary text,
    add column logo             text;





CREATE TABLE characters.character_benefit
(
    id             bigint auto_increment NOT NULL,
    character_id   bigint                NOT NULL,
    benefit_code   varchar(50)           NOT NULL,
    transaction_id text                  NOT NULL,
    CONSTRAINT benefit_code_character_id_uq UNIQUE (character_id, benefit_code),
    PRIMARY KEY (id)
);


CREATE TABLE characters.character_services
(
    id           bigint auto_increment NOT NULL,
    character_id bigint                NOT NULL,
    skill_id     bigint                NOT NULL,
    name         varchar(35)           NOT NULL,
    description  varchar(300)          NOT NULL,
    score        double,
    public       boolean,

    PRIMARY KEY (id),
    CONSTRAINT skill_id_character_id_uq UNIQUE (skill_id, character_id)
);