DROP database IF EXISTS travel_agency;

CREATE database travel_agency;

USE travel_agency;

CREATE TABLE access_levels
(
    id   INT         NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE transfer_types
(
    id   INT         NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE tour_types
(
    id   INT         NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE tour_states
(
    id   INT         NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE order_states
(
    id   INT         NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE users
(
    id               INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(40)  NOT NULL,
    surname          VARCHAR(40)  NOT NULL,
    phoneNumber      VARCHAR(13)  NOT NULL,
    email            VARCHAR(40)  NOT NULL,
    password         VARCHAR(150) NOT NULL,
    birthDay         DATE   DEFAULT NULL,
    balanceInUSD     DOUBLE DEFAULT 0,
    personalDiscount DOUBLE DEFAULT 0,
    maxDiscount      DOUBLE DEFAULT 20,
    isBanned         BOOL   DEFAULT FALSE,
    accessLevelId    INT    DEFAULT 3,
    CONSTRAINT accessLevelId_fk
        FOREIGN KEY (accessLevelId)
            REFERENCES access_levels (id)
);

CREATE TABLE tours
(
    id             INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(40)   NOT NULL,
    info           VARCHAR(1000) NOT NULL,
    country        VARCHAR(20)   NOT NULL,
    tourStateId    INT  DEFAULT 1,
    tourTypeId     INT           NOT NULL,
    isHot          BOOL DEFAULT FALSE,
    date           DATE          NOT NULL,
    nightsCount    INT           NOT NULL,
    personsCount   INT           NOT NULL,
    hotelRating    INT           NOT NULL,
    hotelName      VARCHAR(20)   NOT NULL,
    transferTypeId INT           NOT NULL,
    costInUSD      FLOAT         NOT NULL,
    previewFile    VARCHAR(250),
    CONSTRAINT transferType_fk
        FOREIGN KEY (transferTypeId)
            REFERENCES transfer_types (id),
    CONSTRAINT tourType_fk
        FOREIGN KEY (tourTypeId)
            REFERENCES tour_types (id),
    CONSTRAINT tour_state_fk
        FOREIGN KEY (tourStateId)
            REFERENCES tour_states (id)
);

CREATE TABLE orders
(
    id           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId       INT NOT NULL,
    tourId       INT NOT NULL,
    orderStateId INT DEFAULT 1,
    CONSTRAINT user_id_fk
        FOREIGN KEY (userId)
            REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT team_id_fk
        FOREIGN KEY (tourId)
            REFERENCES tours (id) ON DELETE CASCADE,
    CONSTRAINT order_state_fk
        FOREIGN KEY (orderStateId)
            REFERENCES order_states (id) ON DELETE CASCADE
);

CREATE TABLE saved_entries
(
    uuId       VARCHAR(36) NOT NULL PRIMARY KEY,
    userId     INT         NOT NULL,
    validUntil DATE,
    CONSTRAINT saved_user_id_fk
        FOREIGN KEY (userId)
            REFERENCES users (id) ON DELETE CASCADE
);


