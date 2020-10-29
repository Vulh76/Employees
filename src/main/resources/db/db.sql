DROP TABLE IF EXISTS sbt.employees;
DROP TABLE IF EXISTS sbt.departments;

CREATE TABLE sbt.employees
(
    id            bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name    VARCHAR(50)        NOT NULL,
    last_name     VARCHAR(50)        NOT NULL,
    age           int,
    department_id bigint
);

CREATE TABLE sbt.departments
(
    id   bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50)        NOT NULL
);

INSERT INTO sbt.departments (name)
VALUES ('Отдел разработки'),
       ('Отдел тестирования'),
       ('Отдел техподдержки'),
       ('Отдел маркетинга');


INSERT INTO sbt.employees (first_name, last_name, age, department_id)
VALUES ('Андрей', 'Иванов', 27, 1),
       ('Илья', 'Думаченко', 34, 1),
       ('Наталья', 'Синичкина', 23, 1),
       ('Владимир', 'Орлов', 43, 1),
       ('Елизавета', 'Котова', 39, 2),
       ('Оксана', 'Саратова', 26, 2),
       ('Александр', 'Вертушкин', 29, 2),
       ('Виктория', 'Соколова', 31, 3),
       ('Семён', 'Тулупов', 46, 3),
       ('Вероника', 'Филатова', 38, 1),
       ('Наталья', 'Васильева', 33, 1),
       ('Валентин', 'Амельченко', 19, 2),
       ('Анна', 'Плетнёва', 28, 2),
       ('Игорь', 'Степанов', 33, 1),
       ('Потап', 'Горьев', 24, 4),
       ('Марина', 'Тополёва', 37, 4);

SELECT *
FROM sbt.employees;

