

CREATE TABLE "refresh_tokens" (
    token VARCHAR(512) NOT NULL PRIMARY KEY ,
    member_id UUID NOT NULL,
    FOREIGN KEY(member_id) REFERENCES members(id)
);
