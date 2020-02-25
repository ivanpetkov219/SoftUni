#1st task
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT first_name, last_name FROM employees
WHERE salary > 35000
ORDER BY first_name, last_name, employee_id;

END$$


#2nd task
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(min_salary DECIMAL(19,4))
BEGIN
SELECT first_name, last_name FROM employees
WHERE salary >= min_salary
ORDER BY first_name, last_name, employee_id;

END$$



#3nd task
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(pattern VARCHAR(45))
BEGIN

SELECT `name` FROM towns
WHERE `name` LIKE CONCAT(pattern, '%')
ORDER BY `name`;

END$$

#4th task

DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(pattern VARCHAR(45))
BEGIN

SELECT e.first_name, e.last_name 
FROM employees AS e
JOIN addresses AS a
ON e.address_id = a.address_id
JOIN towns AS t
ON t.town_id = a.town_id
WHERE t.`name` = pattern
ORDER BY first_name, last_name, employee_id;

END$$

#5th task

DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(19,4))
RETURNS VARCHAR(45)
DETERMINISTIC
BEGIN
DECLARE salary_level VARCHAR(45);

IF salary < 30000 THEN SET salary_level := 'Low';
ELSEIF salary BETWEEN 30000 AND 50000 THEN SET salary_level := 'Average';
ELSEIF salary > 50000 THEN SET salary_level := 'High';
END IF;
RETURN salary_level;
END$$






