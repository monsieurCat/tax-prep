/*

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL UNIQUE,
  birthday DATE,
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street_1 VARCHAR(100) NOT NULL,
    street_2 VARCHAR (50) DEFAULT '',
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE filing_status (
  id SERIAL PRIMARY KEY,
  status VARCHAR(50) NOT NULL UNIQUE,
);

CREATE TABLE tax_brackets (
  id SERIAL PRIMARY KEY,
  rate INT NOT NULL,
  min_income INT NOT NULL,
  max_income INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE tax_info (
  id SERIAL PRIMARY KEY,
  annual_income DECIMAL(10, 2) DEFAULT 0,
  withholdings DECIMAL (10, 2) DEFAULT 0,
  deductions DECIMAL (10, 2) DEFAULT 0,
  tuition_and_fees DECIMAL (10, 2) DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id),
);

CREATE TABLE dependents (
  id SERIAL PRIMARY KEY,
  age INT NOT NULL,
  FOREIGN KEY (tax_info_id) REFERENCES tax_info(id)
);

*/

INSERT INTO filing_status (status) VALUES ('Single');
INSERT INTO filing_status (status) VALUES ('Married filing jointly');
INSERT INTO filing_status (status) VALUES ('Married filing separately');
INSERT INTO filing_status (status) VALUES ('Head of Household');
INSERT INTO filing_status (status) VALUES ('Qualifying Surviving Spouse');

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 11000, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 11001, 44725, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 44726, 95375, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 95376, 182100, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 182101, 231250, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 231251, 578125, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 578126, 2147483647, 1);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 22000, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 22001, 89450, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 89451, 190750, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 190751, 364200, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 364201, 462500, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 462501, 693750, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 693751, 2147483647, 2);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 11000, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 11001, 44725, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 44726, 95375, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 95376, 182100, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 182101, 231250, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 231251, 346875, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 346876, 2147483647, 3);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 15700, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 15701, 59850, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 59851, 95350, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 95351, 182100, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 182101, 231250, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 231251, 578100, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 578101, 2147483647, 4);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 22000, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 22001, 89450, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 89451, 190750, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 190751, 364200, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 364201, 462500, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 462501, 693750, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 693751, 2147483647, 5);
/*

INSERT INTO warehouses (name, description, max_capacity) VALUES ('warehouse1', 'main warehouse', 200);
INSERT INTO warehouses (name, description, max_capacity) VALUES ('warehouse2', 'secondary warehouse', 100);

INSERT INTO products (name, price, category_id, size) VALUES ('TV', 1200, 1, 'Large');
INSERT INTO products (name, price, category_id, size) VALUES ('Computer', 800, 1, 'Medium');
INSERT INTO products (name, price, category_id, size) VALUES ('Sofa', 1200, 2, 'ExtraLarge');
INSERT INTO products (name, price, category_id, size) VALUES ('The Hobbit', 8, 3, 'Small'); */