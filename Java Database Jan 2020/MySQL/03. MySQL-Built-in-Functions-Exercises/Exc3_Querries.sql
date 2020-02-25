SELECT first_name FROM employees
WHERE department_id IN (3,10)
AND YEAR(hire_date) BETWEEN 1995 AND 2005
ORDER BY employee_id;

SELECT first_name, last_name FROM employees
WHERE job_title NOT LIKE '%engineer%'
ORDER BY employee_id;

SELECT `name` FROM `towns`
WHERE CHAR_LENGTH(`name`) IN (5,6)
ORDER BY `name`;

SELECT `town_id`,`name` FROM `towns`
WHERE LEFT(`name`, 1) IN('M','K','B','E')
ORDER BY `name`;

SELECT `town_id`,`name` FROM `towns`
WHERE LEFT(`name`, 1) NOT IN('R','B','D')
ORDER BY `name`;

CREATE VIEW `v_employees_hired_after_2000` AS
SELECT first_name, last_name FROM employees
WHERE YEAR(hire_date) > 2000;

SELECT * FROM `v_employees_hired_after_2000`; 

SELECT first_name, last_name FROM employees
WHERE CHAR_LENGTH(last_name) = 5;

SELECT country_name, iso_code FROM countries
WHERE country_name LIKE '%A%A%A%'
ORDER BY iso_code;

SELECT p.peak_name, r.river_name, 
LOWER(CONCAT(peak_name, SUBSTRING(river_name, 2))) AS 'mix'
FROM peaks AS p, rivers AS r
WHERE RIGHT(peak_name, 1) = LEFT(river_name, 1)
ORDER BY mix;

SELECT `name`, DATE(`start`)
 FROM games
 WHERE YEAR(`start`) IN(2011,2012)
 ORDER BY `start`, `name`
 LIMIT 50;
 
SELECT user_name, 
SUBSTRING(email, LOCATE('@', email) + 1) AS 'Email'
FROM users
ORDER BY Email, user_name;

SELECT user_name, ip_address 
FROM users
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

SELECT `name`,
CASE 
	WHEN HOUR(`start`) BETWEEN 0 AND 11 THEN 'Morning'
	WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
	WHEN HOUR(`start`) BETWEEN 18 AND 23 THEN 'Evening'
END  AS 'Part of the Day',
CASE 
	WHEN duration BETWEEN 1 AND 3 THEN 'Extra Short'
	WHEN duration BETWEEN 4 AND 6 THEN 'Short'
	WHEN duration BETWEEN 7 AND 10 THEN 'Long'
	ELSE 'Extra Long'
END  AS 'Duration'
FROM games;

SELECT product_name, order_date,
ADDDATE(order_date, INTERVAL 3 DAY) AS 'pay_due',
ADDDATE(order_date, INTERVAL 1 MONTH) AS 'delivery_due'
FROM orders;





















