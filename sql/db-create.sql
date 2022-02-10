DROP database IF EXISTS travel_agency;

CREATE database travel_agency;

USE travel_agency;

CREATE TABLE users
(
    id               INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(40)  NOT NULL,
    surname          VARCHAR(40)  NOT NULL,
    phoneNumber      VARCHAR(13)  NOT NULL,
    email            VARCHAR(40)  NOT NULL,
    password         VARCHAR(150) NOT NULL,
    birthDay         DATE                                        DEFAULT NULL,
    balanceInUSD     DOUBLE                                      DEFAULT 0,
    personalDiscount DOUBLE                                      DEFAULT 0,
    maxDiscount      DOUBLE                                      DEFAULT 20,
    isBanned         BOOL                                        DEFAULT FALSE,
    accessLevel      ENUM ('ADMINISTRATOR', 'MANAGER', 'CLIENT') DEFAULT 'CLIENT'
);

CREATE TABLE tours
(
    id          INT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    state       ENUM ('AVAILABLE', 'ONGOING', 'COMPLETED', 'CANCELED') DEFAULT 'AVAILABLE',
    isHot       BOOL                                                   DEFAULT FALSE,
    date        DATE  NOT NULL,
    nightsCount INT   NOT NULL,
    type        ENUM ('RELAX', 'SIGHTSEEING', 'SHOPPING'),
    costInUSD   FLOAT NOT NULL,
    hotelRating INT   NOT NULL,
    info        VARCHAR(1000),
    name        VARCHAR(20)
);

CREATE TABLE orders
(
    id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    tourId INT NOT NULL,
    state  ENUM ('BOOKED', 'PAYED', 'CANCELED') DEFAULT 'BOOKED',
    CONSTRAINT user_id_fk
        FOREIGN KEY (userId)
            REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT team_id_fk
        FOREIGN KEY (tourId)
            REFERENCES tours (id) ON DELETE CASCADE
);

CREATE TABLE saved_entries
(
    uuId  VARCHAR(36) NOT NULL PRIMARY KEY,
    userId INT NOT NULL,
    CONSTRAINT saved_user_id_fk
        FOREIGN KEY (userId)
            REFERENCES users (id) ON DELETE CASCADE
);
