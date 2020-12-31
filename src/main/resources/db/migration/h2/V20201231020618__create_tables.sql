-- -------------------------------------------------------------
-- TablePlus 3.12.1(357)
--
-- https://tableplus.com/
--
-- Database: cicoffee
-- Generation Time: 2020-12-31 02:05:56.5220
-- -------------------------------------------------------------
CREATE SCHEMA IF NOT EXISTS "public";

DROP TABLE IF EXISTS "public"."members";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."members" (
                                    "id" uuid NOT NULL,
                                    "first_name" varchar(64),
                                    "last_name" varchar(64),
                                    "email" varchar(128) NOT NULL,
                                    "password_hash" varchar(128) NOT NULL DEFAULT ''::character varying,
                                    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."mobile_devices";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."mobile_devices" (
                                           "id" uuid NOT NULL,
                                           "name" varchar(64),
                                           "identifier" varchar(128) NOT NULL,
                                           "member_id" uuid NOT NULL,
                                           PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."order_items";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."order_items" (
                                        "id" uuid NOT NULL,
                                        "order_id" uuid NOT NULL,
                                        "product_id" uuid NOT NULL,
                                        "quantity" int4 NOT NULL,
                                        PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."orders";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."orders" (
                                   "id" uuid NOT NULL,
                                   "session_id" uuid NOT NULL,
                                   "member_id" uuid NOT NULL,
                                   PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."products";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."products" (
                                     "id" uuid NOT NULL,
                                     "photo_url" varchar(4096) NOT NULL,
                                     "name" varchar(64) NOT NULL,
                                     PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."refresh_tokens";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."refresh_tokens" (
                                           "token" varchar(512) NOT NULL,
                                           "member_id" uuid NOT NULL,
                                           PRIMARY KEY ("token")
);

DROP TABLE IF EXISTS "public"."reset_codes";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."reset_codes" (
                                        "id" uuid NOT NULL,
                                        "code" text NOT NULL,
                                        "created_date" timestamp NOT NULL DEFAULT now(),
                                        "member_id" uuid NOT NULL,
                                        PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."session_participants";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."session_participants" (
                                                 "id" uuid NOT NULL,
                                                 "session_id" uuid NOT NULL,
                                                 "member_id" uuid NOT NULL,
                                                 PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."sessions";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."sessions" (
                                     "id" uuid NOT NULL,
                                     "start_date" timestamp NOT NULL,
                                     "end_date" timestamp NOT NULL,
                                     "team_id" uuid NOT NULL,
                                     "author_id" uuid NOT NULL,
                                     PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."team_members";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."team_members" (
                                         "team_id" uuid NOT NULL,
                                         "member_id" uuid NOT NULL
);

DROP TABLE IF EXISTS "public"."teams";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."teams" (
                                  "id" uuid NOT NULL,
                                  "name" varchar(64),
                                  "creator_id" uuid NOT NULL,
                                  PRIMARY KEY ("id")
);

ALTER TABLE "public"."mobile_devices" ADD FOREIGN KEY ("member_id") REFERENCES "public"."members"("id");
ALTER TABLE "public"."order_items" ADD FOREIGN KEY ("product_id") REFERENCES "public"."products"("id");
ALTER TABLE "public"."order_items" ADD FOREIGN KEY ("order_id") REFERENCES "public"."orders"("id");
ALTER TABLE "public"."orders" ADD FOREIGN KEY ("session_id") REFERENCES "public"."sessions"("id");
ALTER TABLE "public"."orders" ADD FOREIGN KEY ("member_id") REFERENCES "public"."members"("id");
ALTER TABLE "public"."refresh_tokens" ADD FOREIGN KEY ("member_id") REFERENCES "public"."members"("id");
ALTER TABLE "public"."reset_codes" ADD FOREIGN KEY ("member_id") REFERENCES "public"."members"("id");
ALTER TABLE "public"."session_participants" ADD FOREIGN KEY ("session_id") REFERENCES "public"."sessions"("id");
ALTER TABLE "public"."session_participants" ADD FOREIGN KEY ("member_id") REFERENCES "public"."members"("id");
ALTER TABLE "public"."sessions" ADD FOREIGN KEY ("team_id") REFERENCES "public"."teams"("id");
ALTER TABLE "public"."team_members" ADD FOREIGN KEY ("team_id") REFERENCES "public"."teams"("id");
ALTER TABLE "public"."team_members" ADD FOREIGN KEY ("member_id") REFERENCES "public"."members"("id");
ALTER TABLE "public"."teams" ADD FOREIGN KEY ("creator_id") REFERENCES "public"."members"("id");
