

CREATE TABLE "mobile_devices" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(64),
    identifier VARCHAR(128) NOT NULL UNIQUE,
    member_id UUID NOT NULL,
    FOREIGN KEY(member_id) REFERENCES members(id)
);


CREATE TABLE "session_participants" (
    id UUID NOT NULL PRIMARY KEY ,
    session_id UUID NOT NULL,
    member_id UUID NOT NULL,
    FOREIGN KEY(session_id) REFERENCES sessions(id),
    FOREIGN KEY(member_id) REFERENCES members(id)
);
