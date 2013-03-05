ALTER TABLE participants DROP COLUMN canBeParticipant,
                         DROP COLUMN canBeStaff,
                         DROP COLUMN canBeStaffYouth,
                         DROP COLUMN canBeBoard,
                         DROP COLUMN canBeExtendedBoard,
                         DROP COLUMN canBeMAK,
                         DROP COLUMN canBeAGE,
                         DROP COLUMN canBeKitchen,
                         DROP COLUMN canBeSeminar,
                         DROP COLUMN canBeMisc;

ALTER TABLE campParticipants DROP COLUMN isParticipant,
                             DROP COLUMN isStaff,
                             DROP COLUMN isStaffYouth,
                             DROP COLUMN isBoard,
                             DROP COLUMN isExtendedBoard,
                             DROP COLUMN isMAK,
                             DROP COLUMN isAGE,
                             DROP COLUMN isKitchen,
                             DROP COLUMN isSeminar,
                             DROP COLUMN isMisc;

ALTER TABLE campParticipants ADD COLUMN sinceInCamp DATE NOT NULL,
                             ADD COLUMN sinceNotInCamp DATE,
                             ADD COLUMN role INTEGER DEFAULT 3;

UPDATE campParticipants SET sinceInCamp=CURDATE();

ALTER TABLE camps ADD COLUMN cancelledSince DATE;
