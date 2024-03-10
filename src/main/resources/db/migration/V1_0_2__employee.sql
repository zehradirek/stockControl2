INSERT INTO employee (id, employee_name, password, email)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ahmet', '12345', 'ahmet@gmail.com');

INSERT INTO employee (id, employee_name, password, email)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'mehmet', '12345', 'mehmet@gmail.com');

INSERT INTO employee (id, employee_name, password, email)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'admin', '12345', 'admin@gmail.com');