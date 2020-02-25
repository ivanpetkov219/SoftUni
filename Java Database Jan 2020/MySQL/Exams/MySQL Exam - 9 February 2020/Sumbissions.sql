#5th task
SELECT first_name, age, salary
FROM players
ORDER BY salary DESC;

#6th task
SELECT p.id, CONCAT(p.first_name, ' ', p.last_name) AS 'full_name',
p.age, p.position, p.hire_date FROM players AS p
JOIN skills_data AS sd
ON p.skills_data_id = sd.id
WHERE p.age < 23 AND p.hire_date IS NULL AND sd.strength > 50
ORDER BY p.salary, p.age;

#7th task
SELECT t.name, t.established, t.fan_base AS 'fan_base', COUNT(p.id) AS 'players_count'
FROM teams AS t
LEFT JOIN players AS p
ON t.id = p.team_id
GROUP BY t.id
ORDER BY players_count DESC, fan_base DESC;

#8th task
SELECT MAX(speed) AS 'max_speed', t.name AS 'town_name'
FROM skills_data AS sd
RIGHT JOIN players AS p
ON sd.id = p.skills_data_id
RIGHT JOIN teams AS te
ON p.team_id = te.id
RIGHT JOIN stadiums AS s
ON s.id = te.stadium_id
RIGHT JOIN towns AS t
ON t.id = s.town_id
WHERE te.name != 'Devify'
GROUP BY t.name
ORDER BY max_speed DESC, town_name;

#9th task
SELECT c.name, COUNT(p.id) AS 'total_count_of_players', SUM(p.salary) AS 'total_sum_of_salaries'
FROM countries AS c
LEFT JOIN towns AS t
ON t.country_id = c.id
LEFT JOIN stadiums AS s
ON s.town_id = t.id
LEFT JOIN teams AS te
ON te.stadium_id = s.id
LEFT JOIN players AS p
ON p.team_id = te.id
GROUP BY c.id
ORDER BY total_count_of_players DESC, c.name;

#10th task
DELIMITER $$
CREATE FUNCTION `udf_stadium_players_count` (stadium_name VARCHAR(30))

RETURNS INTEGER
DETERMINISTIC
BEGIN

RETURN 
(SELECT COUNT(p.id)
FROM stadiums AS s
JOIN teams AS t
ON t.stadium_id = s.id
JOIN players AS p
ON p.team_id = t.id
WHERE s.name = stadium_name);

END $$

SELECT udf_stadium_players_count('Jaxworks');

#11th Task
DELIMITER $$
CREATE PROCEDURE `udp_find_playmaker` (min_dribble_points INT, team_name VARCHAR(45) )
BEGIN
SELECT CONCAT(p.first_name, ' ', p.last_name) AS 'player_name', p.age, p.salary, sd.dribbling, sd.speed, t.name AS 'team_name'
FROM teams AS t
JOIN players AS p
ON p.team_id = t.id
JOIN skills_data AS sd
ON sd.id = p.skills_data_id
WHERE sd.dribbling > min_dribble_points AND t.name = team_name
ORDER BY sd.speed DESC LIMIT 1;

END$$

CALL udp_find_playmaker ( 20, 'Skyble');



#2nd Task

INSERT INTO coaches (first_name, last_name, salary, coach_level)
SELECT first_name, last_name, salary * 2, CHAR_LENGTH(first_name) 
FROM players 
WHERE age >= 45;

#3rd Task

UPDATE coaches
SET coach_level = coach_level + 1
WHERE id IN(
SELECT coach_id
FROM (SELECT * FROM players_coaches) AS pc
JOIN (SELECT * FROM coaches) AS c
ON pc.coach_id = c.id
WHERE c.first_name LIKE 'A%'
GROUP BY coach_id
HAVING COUNT(player_id) >= 1
);

#4th Task

DELETE FROM players
WHERE id IN 
(
SELECT pl.id
FROM (SELECT * FROM players) AS pl
WHERE pl.age >= 45
);

DELETE
FROM players
WHERE age >= 45;




