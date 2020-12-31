

ALTER TABLE "sessions"
    DROP CONSTRAINT sessions_author_id_fkey,
    DROP COLUMN "payer_id";

