DROP TABLE IF EXISTS dependents CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS tax_info CASCADE;
DROP TABLE IF EXISTS tax_info CASCADE;
DROP TYPE  IF EXISTS users CASCADE;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL UNIQUE,
  user_role VARCHAR(50) NOT NULL,
  address_id INT,
  birthday DATE,
  FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street_1 VARCHAR(100) NOT NULL,
    street_2 VARCHAR (50) DEFAULT '',
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL
);

CREATE TABLE filing_status (
  id SERIAL PRIMARY KEY,
  status VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE tax_brackets (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  rate DECIMAL(5, 2) NOT NULL,
  min_income INT NOT NULL,
  max_income INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE tax_info (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  filing_status_id INT NOT NULL,
  annual_income DECIMAL(20, 2) DEFAULT 0,
  withholdings DECIMAL (20, 2) DEFAULT 0,
  deductions DECIMAL (20, 2) DEFAULT 0,
  tuition_and_fees DECIMAL (10, 2) DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE dependents (
  id SERIAL PRIMARY KEY,
  tax_info_id INT NOT NULL,
  age INT NOT NULL,
  FOREIGN KEY (tax_info_id) REFERENCES tax_info(id)
);