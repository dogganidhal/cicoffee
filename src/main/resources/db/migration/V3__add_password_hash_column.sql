

ALTER TABLE "members"
    ADD COLUMN password_hash VARCHAR(128) NOT NULL default '';
