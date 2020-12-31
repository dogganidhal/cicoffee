

CREATE TABLE "members" (
    id UUID NOT NULL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    email VARCHAR(128) NOT NULL UNIQUE
);


CREATE TABLE "teams" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(64)
);


CREATE TABLE "team_members" (
    team_id UUID NOT NULL,
    member_id UUID NOT NULL,
    FOREIGN KEY(member_id) REFERENCES members(id),
    FOREIGN KEY(team_id) REFERENCES teams(id)
);
