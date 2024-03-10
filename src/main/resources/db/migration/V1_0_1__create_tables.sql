CREATE TABLE employee
(
    id            BINARY(16)   NOT NULL,
    employee_name VARCHAR(45)  NULL,
    password      VARCHAR(200) NOT NULL,
    email         VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id           BINARY(16)  NOT NULL,
    role         VARCHAR(45) NOT NULL,
    employee_id  BINARY(16),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BINARY(16)  NOT NULL,
    product_name  VARCHAR(45) NOT NULL,
    unit_price    VARCHAR(45) NOT NULL,
    unit_in_stock INT(2) NOT NULL,
    min_stock     INT(2) NOT NULL,
    PRIMARY KEY (id)
);