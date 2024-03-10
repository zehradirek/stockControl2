INSERT INTO role (id, role, employee_id) VALUES (unhex(replace(uuid(), '-', '')), 'admin', (SELECT id FROM employee WHERE employee_name = 'admin'));
INSERT INTO role (id, role, employee_id) VALUES (unhex(replace(uuid(), '-', '')), 'user', (SELECT id FROM employee WHERE employee_name = 'user'));
