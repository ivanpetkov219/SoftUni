CREATE DATABASE table_relations;

#1st Task
CREATE TABLE passports (
	`passport_id` INT UNIQUE PRIMARY KEY,
    `passport_number` VARCHAR(20) UNIQUE
);

INSERT INTO passports (`passport_id`, `passport_number`)
VALUES 
	(101, 'N34FG21B'),
	(102, 'K65LO4R7'),
	(103, 'ZE657QP2');
    
    CREATE TABLE persons (
	`person_id` INT UNIQUE PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20),
    `salary` DECIMAL(10,2),
    `passport_id` INT UNIQUE
);

INSERT INTO persons (`person_id`, `first_name`, `salary`, `passport_id`)
VALUES 
	(1,'Roberto', 43300.00, 102),
	(2,'Tom', 56100.00, 103),
	(3,'Yana', 60200.00, 101);
    
ALTER TABLE persons
ADD CONSTRAINT fk_persons_passports
FOREIGN KEY persons(passport_id)
REFERENCES passports(passport_id);

#2nd Task

CREATE TABLE manufacturers (
	`manufacturer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    `established_on` DATE 
);

INSERT INTO manufacturers (`manufacturer_id`, `name`, `established_on`)
VALUES 
	(1, 'BMW', '1916-03-01'),
	(2, 'Tesla', '2003-01-01'),
	(3, 'Lada', '1966-05-01');	
    
CREATE TABLE models (
	`model_id` INT PRIMARY KEY,
    `name` VARCHAR(50),
    `manufacturer_id` INT,
    
	CONSTRAINT fk_models_manufacturers
    FOREIGN KEY (manufacturer_id)
    REFERENCES manufacturers(manufacturer_id)
);

INSERT INTO models (`model_id`, `name`, `manufacturer_id`)
VALUES 
	(101, 'X1', 1),
	(102, 'i6', 1),
	(103, 'Model S',2),
	(104, 'Model X', 2),
	(105, 'Model 3', 2),
	(106, 'Nova', 3);
    
#3rd Task

CREATE TABLE students (
	`student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

INSERT INTO students (`name`)
VALUES 
	('Mila'),
	('Toni'),
	('Ron');
    
    
CREATE TABLE exams (
	`exam_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

INSERT INTO exams (`exam_id`, `name`)
VALUES 
	('101', 'Spring MVC'),
	('102', 'Neo4j'),
	('103', 'Oracle 11g');

CREATE TABLE students_exams (
`student_id` INT,
`exam_id` INT
);


