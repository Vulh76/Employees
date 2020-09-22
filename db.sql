DROP TABLE IF EXISTS sbt.employees;
CREATE TABLE sbt.employees
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  age int,
  department VARCHAR,
)

INSERT INTO sbt.employees (first_name, last_name, age, department)
VALUES
  ('Андрей', 'Иванов', 27, 'Отдел резработки'),
  ('Илья', 'Думаченко', 34, 'Отдел резработки'),
  ('Наталья', 'Синичкина', 23, 'Отдел резработки'),
  ('Владимир', 'Орлов', 43, 'Отдел тестирования'),
  ('Елизавета', 'Котова', 39, 'Отдел тестирования'),
  ('Оксана', 'Саратова', 22, 'Отдел тестирования'),
  ('Александр', 'Вертушкин', 29, 'Отдел внедрения'),
  ('Виктория', 'Соколова', 31, 'Отдел внедрения')
