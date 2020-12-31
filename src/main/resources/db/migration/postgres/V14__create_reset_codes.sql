CREATE TABLE "reset_codes" (
    id UUID NOT NULL PRIMARY KEY,
    code TEXT NOT NULL UNIQUE,
    created_date TIMESTAMP NOT NULL DEFAULT NOW(),
    member_id UUID NOT NULL,
    FOREIGN KEY(member_id) REFERENCES members(id)
);
