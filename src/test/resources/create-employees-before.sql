DROP TABLE IF EXISTS sbt.employees;

CREATE TABLE sbt.employees
(
    id            bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name    VARCHAR(50)        NOT NULL,
    last_name     VARCHAR(50)        NOT NULL,
    age           int,
    department_id bigint
);
