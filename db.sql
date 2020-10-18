DROP TABLE IF EXISTS sbt.employees;
DROP TABLE IF EXISTS sbt.departments;

CREATE TABLE sbt.employees
(
  id bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  age int,
  department VARCHAR
);

CREATE TABLE sbt.departments
(
  id bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
  name VARCHAR(50) NOT NULL
);

INSERT INTO sbt.employees (first_name, last_name, age, department)
VALUES
  ('Андрей', 'Иванов', 27, 'Отдел резработки'),
  ('Илья', 'Думаченко', 34, 'Отдел резработки'),
  ('Наталья', 'Синичкина', 23, 'Отдел резработки'),
  ('Владимир', 'Орлов', 43, 'Отдел тестирования'),
  ('Елизавета', 'Котова', 39, 'Отдел тестирования'),
  ('Оксана', 'Саратова', 26, 'Отдел тестирования'),
  ('Александр', 'Вертушкин', 29, 'Отдел внедрения'),
  ('Виктория', 'Соколова', 31, 'Отдел внедрения'),
  ('Семён', 'Тулупов', 46, 'Отдел резработки'),
  ('Вероника', 'Филатова', 38, 'Отдел резработки'),
  ('Наталья', 'Васильева', 33, 'Отдел резработки'),
  ('Валентин', 'Амельченко', 19, 'Отдел тестирования'),
  ('Анна', 'Плетнёва', 28, 'Отдел тестирования'),
  ('Игорь', 'Степанов', 33, 'Отдел тестирования'),
  ('Потап', 'Горьев', 24, 'Отдел тестирования'),
  ('Марина', 'Тополёва', 37, 'Отдел внедрения');


INSERT INTO sbt.departments (name)
VALUES
  ('Отдел разработки'),
  ('Отдел тестирования'),
  ('Отдел техподдержки'),
  ('Отдел маркетинга');

SELECT * FROM sbt.employees;

