CREATE TABLE participants
(
    id                  INTEGER NOT NULL,
    lastName            VARCHAR(255) NOT NULL,
    foreName            VARCHAR(100) NOT NULL,
    gender              CHAR(1) NOT NULL,
    denomination        INTEGER NOT NULL,
    birth               DATE NOT NULL,
    livingStreet        VARCHAR(100),
    livingCity          VARCHAR(100),
    livingPlz           INTEGER,
    postStreet          VARCHAR(100),
    postCity            VARCHAR(100),
    postPlz             INTEGER,
    phone               VARCHAR(50),
    fax                 VARCHAR(50),
    mobilePhone         VARCHAR(50),
    phoneParents        VARCHAR(50),
    mailAddress         VARCHAR(100),
    countyCouncil       INTEGER NOT NULL,
    bankCodeNumber      INTEGER,
    bank                VARCHAR(100),
    bankAccountNumber   INTEGER,
    commentar           TEXT,
    sinceInDb           DATE,
    dateUpInDb          DATE,
    canBeParticipant    BOOL,
    canBeStaff          BOOL,
    canBeStaffYouth     BOOL,
    canBeBoard          BOOL,
    canBeExtendedBoard  BOOL,
    canBeMAK            BOOL,
    canBeAGE            BOOL,
    canBeKitchen        BOOL,
    canBeSeminar        BOOL,
    canBeMisc           BOOL,
    Primary Key (id)
);

CREATE TABLE camps
(
    id                  INTEGER NOT NULL,
    name                VARCHAR(255) NOT NULL,
    fromDate            DATE NOT NULL,
    until               DATE NOT NULL,
    location            VARCHAR(255) NOT NULL,
    ratePerParticipant  VARCHAR(50) NOT NULL,
    ratePerDayChild     VARCHAR(50),
    Primary Key (id)
);

CREATE TABLE campParticipants
(
    id                  INTEGER NOT NULL,
    camp                INTEGER NOT NULL,
    isParticipant       BOOL,
    isStaff             BOOL,
    isStaffYouth        BOOL,
    isBoard             BOOL,
    isExtendedBoard     BOOL,
    isMAK               BOOL,
    isAGE               BOOL,
    isKitchen           BOOL,
    isSeminar           BOOL,
    isMisc              BOOL,
    Primary Key (id, camp),
    Foreign Key (camp) references camps(id)
    
);

