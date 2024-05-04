DROP TABLE IF EXISTS standardized_deduction CASCADE;
DROP TABLE IF EXISTS filing_status CASCADE;
DROP TABLE IF EXISTS tax_brackets CASCADE;
DROP TABLE IF EXISTS income_w2 CASCADE;
DROP TABLE IF EXISTS income_1099 CASCADE;
DROP TABLE IF EXISTS user_address CASCADE;
DROP TABLE IF EXISTS tax_info CASCADE;
DROP TABLE IF EXISTS users CASCADE;


CREATE TABLE filing_status (
  id SERIAL PRIMARY KEY,
  status VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE standardized_deduction (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  deduction_amount INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE tax_brackets (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  rate DECIMAL(5, 2) NOT NULL,
  min_income INT NOT NULL,
  max_income INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  middle_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(50) UNIQUE,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL,
  user_role VARCHAR(50) NOT NULL,
  birthday DATE
);

CREATE TABLE user_address (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    street_1 VARCHAR(100) NOT NULL,
    street_2 VARCHAR(50) DEFAULT '',
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE tax_info (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  filing_status_id INT,
  num_dependents INT,
  mortgage_interest DECIMAL(20, 2) DEFAULT 0,
  donations DECIMAL(20, 2) DEFAULT 0,
  property_tax DECIMAL(20, 2) DEFAULT 0,
  medical DECIMAL(20, 2) DEFAULT 0,
  student_loan_interest DECIMAL(20, 2) DEFAULT 0,
  other_deduction DECIMAL(20, 2) DEFAULT 0,
  other_income DECIMAL(20, 2) DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE income_w2 (
  id SERIAL PRIMARY KEY,
  tax_info_id INT NOT NULL,
  income DECIMAL(20, 2) DEFAULT 0,
  withholdings DECIMAL(20, 2) DEFAULT 0,
  employer_ein VARCHAR(25),
  employer_street_1 VARCHAR(100),
  employer_street_2 VARCHAR(50),
  employer_city VARCHAR(50),
  employer_state VARCHAR(25),
  employer_zipcode VARCHAR(25),
  FOREIGN KEY (tax_info_id) REFERENCES tax_info(id)
);

CREATE TABLE income_1099 (
  id SERIAL PRIMARY KEY,
  tax_info_id INT NOT NULL,
  income DECIMAL(20, 2) DEFAULT 0,
  withholdings DECIMAL(20, 2) DEFAULT 0,
  employer_ein VARCHAR(25),
  employer_street_1 VARCHAR(100),
  employer_street_2 VARCHAR(50),
  employer_city VARCHAR(50),
  employer_state VARCHAR(25),
  employer_zipcode VARCHAR(25),
  FOREIGN KEY (tax_info_id) REFERENCES tax_info(id)
);