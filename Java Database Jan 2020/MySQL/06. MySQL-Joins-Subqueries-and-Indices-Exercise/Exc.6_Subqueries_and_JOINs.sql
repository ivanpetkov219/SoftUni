SELECT e.employee_id, e.job_title, e.address_id, a.address_text
FROM employees AS e
JOIN addresses as a
ON e.address_id = a.address_id
ORDER BY address_id
LIMIT 5;

SELECT e.first_name, e.last_name, t.`name`, a.address_text
FROM employees AS e
JOIN addresses AS a
ON e.address_id = a.address_id
JOIN towns AS t
ON a.town_id = t.town_id
ORDER BY first_name, last_name
LIMIT 5;

SELECT e.employee_id, e.first_name, e.last_name, d.`name`
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE d.`name` = 'Sales'
ORDER BY e.employee_id DESC;

SELECT e.employee_id, e.first_name, e.salary, d.`name`
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE e.salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

SELECT e.employee_id, e.first_name
FROM employees AS e
LEFT JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
WHERE ep.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

SELECT e.first_name, e.last_name, e.hire_date, d.`name`
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE d.`name` IN ('Sales', 'Finance') AND DATE(hire_date) > '1999-01-01'
ORDER BY hire_date;

SELECT e.employee_id, e.first_name, p.`name`
FROM employees AS e
JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
JOIN projects AS p
ON ep.project_id = p.project_id
WHERE DATE(p.start_date) > '2002-08-13'AND p.end_date IS NULL
ORDER BY e.first_name, p.`name`
LIMIT 5;


SELECT e.employee_id, e.first_name, 
IF(YEAR(start_date) >=2005, '', `name`) AS 'project_name'
FROM employees AS e
JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
JOIN projects AS p
ON ep.project_id = p.project_id
WHERE e.employee_id = 24
ORDER BY `project_name`;


SELECT e.employee_id,
CONCAT(e.first_name, ' ', e.last_name) AS 'employee_name',
CONCAT(em.first_name, ' ', em.last_name) AS 'manager_name',
d.`name` AS 'department_name'
FROM employees AS e
JOIN employees AS em
ON e.manager_id = em.employee_id
JOIN departments AS d
ON e.department_id = d.department_id
ORDER BY e.employee_id
LIMIT 5;

SELECT AVG(salary) AS 'min_average_salary'
FROM employees
GROUP BY department_id
ORDER BY min_average_salary
LIMIT 1;








