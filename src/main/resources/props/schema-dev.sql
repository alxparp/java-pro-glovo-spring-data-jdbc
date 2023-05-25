-- CREATE TABLE "PRODUCT" (
--     PRODUCT_ID INT AUTO_INCREMENT NOT NULL,
--     NAME VARCHAR(45) NOT NULL,
--     COST DOUBLE PRECISION NOT NULL,
--     CONSTRAINT PRODUCT_PKEY
--         PRIMARY KEY (PRODUCT_ID)
-- );
--
-- CREATE TABLE "ORDER" (
--     ORDER_ID INT AUTO_INCREMENT NOT NULL,
--     DATE DATE NOT NULL,
--     COST DOUBLE PRECISION NOT NULL,
--     CONSTRAINT ORDER_PKEY
--         PRIMARY KEY (ORDER_ID)
-- );
--
-- CREATE TABLE "ORDER_PRODUCT" (
--     ORDER_ID INT NOT NULL,
--     PRODUCT_ID INT NOT NULL,
--     CONSTRAINT ORDER_PRODUCT_PKEY
--         PRIMARY KEY (ORDER_ID, PRODUCT_ID),
--     CONSTRAINT ORDER_FK
--         FOREIGN KEY (ORDER_ID)
--             REFERENCES "ORDER" (ORDER_ID)
--             ON UPDATE CASCADE
--             ON DELETE CASCADE,
--     CONSTRAINT PRODUCT_FK
--         FOREIGN KEY (PRODUCT_ID)
--             REFERENCES "PRODUCT" (PRODUCT_ID)
--             ON UPDATE CASCADE
--             ON DELETE CASCADE
-- );
--
-- CREATE TABLE "USER"
-- (
--     "USERNAME" VARCHAR(32) NOT NULL,
--     "PASSWORD" VARCHAR(64) NOT NULL,
--     "FIRST_NAME" VARCHAR(32) NOT NULL,
--     "LAST_NAME" VARCHAR(32) NOT NULL,
--     "EMAIL" VARCHAR(32) NOT NULL,
--     "LOCKED" BOOL NOT NULL,
--     "DISABLED" BOOL NOT NULL,
--     CONSTRAINT "USER_PKEY"
--         PRIMARY KEY ("USERNAME")
-- );
--
--
--
-- CREATE TABLE "ROLE"
-- (
--     "ROLE_ID" INT AUTO_INCREMENT NOT NULL,
--     "ROLE_NAME" VARCHAR(32) NOT NULL,
--     CONSTRAINT "ROLE_PKEY"
--         PRIMARY KEY ("ROLE_ID")
-- );
--
--
--
-- CREATE TABLE "PERMISSION"
-- (
--     "PERMISSION_ID" INT AUTO_INCREMENT NOT NULL,
--     "PERMISSION_NAME" VARCHAR(32) NOT NULL,
--     CONSTRAINT "PERMISSION_PKEY"
--         PRIMARY KEY ("PERMISSION_ID")
-- );
--
--
--
-- CREATE TABLE "USER_ROLE"
-- (
--     "USERNAME" VARCHAR(32) NOT NULL,
--     "ROLE_ID" INT NOT NULL,
--     CONSTRAINT "USER_ROLE_PKEY"
--         PRIMARY KEY ("USERNAME", "ROLE_ID"),
--     CONSTRAINT "UR_ROLE_FK"
--         FOREIGN KEY ("ROLE_ID")
--             REFERENCES "ROLE" ("ROLE_ID")
--             ON UPDATE NO ACTION
--             ON DELETE NO ACTION,
--     CONSTRAINT "UR_USER_FK"
--         FOREIGN KEY ("USERNAME")
--             REFERENCES "USER" ("USERNAME")
--             ON UPDATE CASCADE
--             ON DELETE CASCADE
-- );
--
--
--
-- CREATE TABLE "ROLE_PERMISSION"
-- (
--     "ROLE_ID" INT NOT NULL,
--     "PERMISSION_ID" INT NOT NULL,
--     CONSTRAINT "ROLE_PERMISSION_PKEY"
--         PRIMARY KEY ("ROLE_ID", "PERMISSION_ID"),
--     CONSTRAINT "RP_PERMISSION_FK"
--         FOREIGN KEY ("PERMISSION_ID")
--             REFERENCES "PERMISSION" ("PERMISSION_ID")
--             ON UPDATE CASCADE
--             ON DELETE CASCADE,
--     CONSTRAINT "RP_ROLE_FK"
--         FOREIGN KEY ("ROLE_ID")
--             REFERENCES "ROLE" ("ROLE_ID")
--             ON UPDATE CASCADE
--             ON DELETE CASCADE
-- );
--
-- CREATE TABLE "CONFIRMATION_TOKEN"
-- (
--     "CONFIRMATION_TOKEN_ID" INT AUTO_INCREMENT NOT NULL,
--     "TOKEN" VARCHAR(64) NOT NULL,
--     "CREATED_AT" TIMESTAMP NOT NULL,
--     "EXPIRES_AT" TIMESTAMP NOT NULL,
--     "CONFIRMED_AT" TIMESTAMP,
--     "USERNAME" VARCHAR(32),
--     CONSTRAINT "CONFIRMATION_TOKEN_PKEY"
--         PRIMARY KEY ("CONFIRMATION_TOKEN_ID"),
--     CONSTRAINT "CT_USER_FK"
--         FOREIGN KEY ("USERNAME")
--             REFERENCES "USER" ("USERNAME")
--             ON UPDATE CASCADE
--             ON DELETE CASCADE
-- );

DROP TABLE IF EXISTS PUBLIC."PRODUCT" CASCADE;
DROP TABLE IF EXISTS PUBLIC."ORDER" CASCADE;
DROP TABLE IF EXISTS PUBLIC."ORDER_PRODUCT" CASCADE;
DROP TABLE IF EXISTS PUBLIC."USER" CASCADE;
DROP TABLE IF EXISTS PUBLIC."ROLE" CASCADE;
DROP TABLE IF EXISTS PUBLIC."PERMISSION" CASCADE;
DROP TABLE IF EXISTS PUBLIC."USER_ROLE" CASCADE;
DROP TABLE IF EXISTS PUBLIC."ROLE_PERMISSION" CASCADE;
DROP TABLE IF EXISTS PUBLIC."CONFIRMATION_TOKEN" CASCADE;