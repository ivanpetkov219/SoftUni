CREATE database ruk_database;
USE ruk_database;

CREATE TABLE branches (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE employees (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10,2) NOT NULL,
started_on DATE NOT NULL,
branch_id INT NOT NULL,

constraint fk_employees_branches
foreign key (branch_id)
references branches(id)
);

CREATE TABLE clients (
id INT PRIMARY KEY AUTO_INCREMENT,
full_name VARCHAR(50) NOT NULL,
age INT NOT NULL
);

CREATE TABLE bank_accounts (
id INT PRIMARY KEY AUTO_INCREMENT,
account_number VARCHAR(10) NOT NULL,
balance DECIMAL(10,2) NOT NULL,
client_id INT NOT NULL UNIQUE,

constraint fk_bank_accounts_clients
foreign key (client_id)
references clients(id)
);

CREATE TABLE cards (
id INT PRIMARY KEY AUTO_INCREMENT,
card_number VARCHAR(19) NOT NULL,
card_status VARCHAR(7) NOT NULL,
bank_account_id INT NOT NULL,

constraint fk_cards_bank_accounts
foreign key (bank_account_id)
references bank_accounts(id)
);

CREATE TABLE employees_clients (
employee_id INT,
client_id INT ,

constraint fk_employees_clients_employees
foreign key (employee_id)
references employees(id),

constraint fk_employees_clients_clients
foreign key (client_id)
references clients(id)
);

INSERT INTO cards (card_number, card_status, bank_account_id)
(SELECT REVERSE(full_name), 'Active', id
FROM clients
WHERE id BETWEEN 191 AND 200);








