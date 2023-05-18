insert into public."PERMISSION" ("PERMISSION_NAME")
values ('student:read'), ('student:write'), ('course:read'), ('course:write');

insert into public."ROLE" ("ROLE_NAME")
values ('ADMIN'), ('STUDENT'), ('ADMINTRAINEE');

insert into "ROLE_PERMISSION" ("ROLE_ID", "PERMISSION_ID")
values (3,1), (3,3);

insert into public."USER" ("USERNAME", "PASSWORD", "FIRST_NAME", "LAST_NAME", "EMAIL")
values ('linda', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Linda', 'Jones', 'linda@gmail.com');

insert into public."USER" ("USERNAME", "PASSWORD", "FIRST_NAME", "LAST_NAME", "EMAIL")
values ('tom', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Tom', 'Banderas', 'tom@gmail.com');

insert into "USER_ROLE" ("USERNAME", "ROLE_ID")
values ('linda', 1);

insert into "USER_ROLE" ("USERNAME", "ROLE_ID")
values ('tom', 3);