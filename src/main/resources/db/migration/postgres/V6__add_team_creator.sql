

ALTER TABLE "teams"
    ADD COLUMN creator_id UUID NOT NULL,
    ADD FOREIGN KEY(creator_id) REFERENCES members(id);
