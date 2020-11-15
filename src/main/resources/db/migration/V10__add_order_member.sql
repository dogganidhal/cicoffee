

ALTER TABLE "orders"
    DROP COLUMN "quantity",
    ADD COLUMN "member_id" UUID NOT NULL,
    ADD FOREIGN KEY(member_id) REFERENCES members(id);
