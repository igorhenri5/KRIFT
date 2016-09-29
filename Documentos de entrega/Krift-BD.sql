﻿/*
Created: 22/09/2016
Modified: 28/09/2016
Model: PostgreSQL 9.4
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- Table USUARIO

CREATE TABLE USUARIO(
 NOM_LOGIN Character varying(20) NOT NULL,
 SEQ_IMAGEM Bigint NOT NULL,
 NOM_PERFIL_USUARIO Character varying(80) NOT NULL,
 EMAIL Character varying(256) NOT NULL,
 SENHA Character varying(41) NOT NULL,
 DES_USUARIO Character varying(255),
 IDT_TENDENCIA Character varying(3) DEFAULT 'SEM' NOT NULL
        CONSTRAINT CheckConstraintA1 CHECK (IDT_TENDENCIA IN ('ONV','VGT', 'VGN', 'CRN', 'SEM')),
 NRO_PONTOS Bigint DEFAULT 0 NOT NULL,
 POS_RANKING Integer NOT NULL
)
;

-- Create indexes for table USUARIO

CREATE INDEX IX_Relationship20 ON USUARIO (SEQ_IMAGEM)
;

-- Add keys for table USUARIO

ALTER TABLE USUARIO ADD CONSTRAINT Key1 PRIMARY KEY (NOM_LOGIN)
;

-- Table RECEITA

CREATE TABLE RECEITA(
 NRO_SEQ_RECEITA BigSerial NOT NULL,
 NOM_LOGIN Character varying(20) NOT NULL,
 SEQ_IMAGEM Bigint,
 NOM_RECEITA Character varying(40) NOT NULL,
 DES_RECEITA Character varying(255) NOT NULL,
 DAT_PUBLICACAO Date DEFAULT NOW() NOT NULL,
 IDT_TENDENCIA Character varying(3) DEFAULT 'SEM' NOT NULL
        CONSTRAINT CHECK_TENDENCIA CHECK (IDT_TENDENCIA IN ('ONV','VGT', 'VGN', 'CRN', 'SEM')),
 QTD_TEMPO Integer,
 QTD_RENDIMENTO Integer NOT NULL
)
;

-- Create indexes for table RECEITA

CREATE INDEX IX_Relationship1 ON RECEITA (NOM_LOGIN)
;

CREATE INDEX IX_Relationship42 ON RECEITA (SEQ_IMAGEM)
;

-- Add keys for table RECEITA

ALTER TABLE RECEITA ADD CONSTRAINT Key2 PRIMARY KEY (NRO_SEQ_RECEITA)
;

-- Table INGREDIENTE

CREATE TABLE INGREDIENTE(
 NRO_SEQ_INGREDIENTE BigSerial NOT NULL,
 NRO_SEQ_RECEITA Bigint NOT NULL,
 DES_QUANTIDADE Character varying(40) NOT NULL,
 NOM_INGREDIENTE Character varying(20) NOT NULL
)
;

-- Add keys for table INGREDIENTE

ALTER TABLE INGREDIENTE ADD CONSTRAINT Key4 PRIMARY KEY (NRO_SEQ_RECEITA,NRO_SEQ_INGREDIENTE)
;

-- Table AVALIACAO

CREATE TABLE AVALIACAO(
 NRO_SEQ_RECEITA Bigint NOT NULL,
 NOM_LOGIN Character varying(20) NOT NULL,
 VLR_AVALIACAO Smallint NOT NULL
)
;

-- Add keys for table AVALIACAO

ALTER TABLE AVALIACAO ADD CONSTRAINT Key7 PRIMARY KEY (NRO_SEQ_RECEITA,NOM_LOGIN)
;

-- Table COMENTARIO

CREATE TABLE COMENTARIO(
 NOM_LOGIN Character varying(20) NOT NULL,
 NRO_SEQ_RECEITA Bigint NOT NULL,
 DAT_PUBLICACAO Timestamp DEFAULT NOW() NOT NULL,
 DES_COMENTARIO Character varying(255) NOT NULL
)
;

-- Add keys for table COMENTARIO

ALTER TABLE COMENTARIO ADD CONSTRAINT Key8 PRIMARY KEY (DAT_PUBLICACAO,NRO_SEQ_RECEITA,NOM_LOGIN)
;

-- Table PROCEDIMENTO

CREATE TABLE PROCEDIMENTO(
 NRO_SEQ_PROCEDIMENTO BigSerial NOT NULL,
 NRO_SEQ_RECEITA Bigint NOT NULL,
 DES_PROCEDIMENTO Character varying(255) NOT NULL
)
;

-- Add keys for table PROCEDIMENTO

ALTER TABLE PROCEDIMENTO ADD CONSTRAINT Key10 PRIMARY KEY (NRO_SEQ_PROCEDIMENTO,NRO_SEQ_RECEITA)
;

-- Table DENUNCIA

CREATE TABLE DENUNCIA(
 NRO_SEQ_DENUNCIA BigSerial NOT NULL,
 NRO_SEQ_RECEITA Bigint NOT NULL,
 DES_COMENTARIO Character varying(255) NOT NULL
)
;

-- Add keys for table DENUNCIA

ALTER TABLE DENUNCIA ADD CONSTRAINT Key12 PRIMARY KEY (NRO_SEQ_RECEITA,NRO_SEQ_DENUNCIA)
;

-- Table HISTORICO

CREATE TABLE HISTORICO(
 NOM_LOGIN Character varying(20) NOT NULL,
 NRO_SEQ_RECEITA Bigint NOT NULL,
 DAT_ACESSO Timestamp DEFAULT NOW() NOT NULL
)
;

-- Add keys for table HISTORICO

ALTER TABLE HISTORICO ADD CONSTRAINT Key13 PRIMARY KEY (NOM_LOGIN,NRO_SEQ_RECEITA,DAT_ACESSO)
;

-- Table IMAGEM

CREATE TABLE IMAGEM(
 SEQ_IMAGEM BigSerial NOT NULL,
 ARQ_IMAGEM Bytea NOT NULL
)
;

-- Add keys for table IMAGEM

ALTER TABLE IMAGEM ADD CONSTRAINT Key15 PRIMARY KEY (SEQ_IMAGEM)
;

-- Table FAVORITO

CREATE TABLE FAVORITO(
 NOM_LOGIN Character varying(20) NOT NULL,
 NRO_SEQ_RECEITA Bigint NOT NULL
)
;

-- Add keys for table FAVORITO

ALTER TABLE FAVORITO ADD CONSTRAINT Key19 PRIMARY KEY (NOM_LOGIN,NRO_SEQ_RECEITA)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE FAVORITO ADD CONSTRAINT Relationship40 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE FAVORITO ADD CONSTRAINT Relationship39 FOREIGN KEY (NOM_LOGIN) REFERENCES USUARIO (NOM_LOGIN) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE DENUNCIA ADD CONSTRAINT Relationship36 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE AVALIACAO ADD CONSTRAINT Relationship24 FOREIGN KEY (NOM_LOGIN) REFERENCES USUARIO (NOM_LOGIN) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE COMENTARIO ADD CONSTRAINT Relationship23 FOREIGN KEY (NOM_LOGIN) REFERENCES USUARIO (NOM_LOGIN) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE USUARIO ADD CONSTRAINT Relationship20 FOREIGN KEY (SEQ_IMAGEM) REFERENCES IMAGEM (SEQ_IMAGEM) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE HISTORICO ADD CONSTRAINT Relationship17 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE HISTORICO ADD CONSTRAINT Relationship16 FOREIGN KEY (NOM_LOGIN) REFERENCES USUARIO (NOM_LOGIN) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE COMENTARIO ADD CONSTRAINT Relationship14 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE AVALIACAO ADD CONSTRAINT Relationship13 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE INGREDIENTE ADD CONSTRAINT Relationship2 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE RECEITA ADD CONSTRAINT Relationship1 FOREIGN KEY (NOM_LOGIN) REFERENCES USUARIO (NOM_LOGIN) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE PROCEDIMENTO ADD CONSTRAINT Relationship41 FOREIGN KEY (NRO_SEQ_RECEITA) REFERENCES RECEITA (NRO_SEQ_RECEITA) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE RECEITA ADD CONSTRAINT Relationship42 FOREIGN KEY (SEQ_IMAGEM) REFERENCES IMAGEM (SEQ_IMAGEM) ON DELETE NO ACTION ON UPDATE NO ACTION
;





