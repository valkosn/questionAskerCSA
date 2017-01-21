-- 1.v1 Скільки людей живе у Львові
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRANSACTION;
SELECT count(userid)
FROM userinfo
WHERE cityid IN (SELECT cityid
                 FROM city
                 WHERE cityname = 'Lviv');
COMMIT TRANSACTION;
-- 1.v2
SELECT count(userid)
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
WHERE cityname = 'Lviv';

-- 1.v3
SELECT count(userid)
FROM userinfo
WHERE cityid = 1
GROUP BY cityid;

-- 2.v1 Скільки людей живе в Україні
SELECT count(userid)
FROM userinfo
WHERE cityid IN (SELECT cityid
                 FROM city
                 WHERE country = 'Ukraine');

-- 2.v2
SELECT count(userid)
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
WHERE country = 'Ukraine';

-- 2.v3
SELECT count(userid)
FROM userinfo
  JOIN city ON (userinfo.cityid = city.cityid)
GROUP BY country
HAVING country = 'Ukraine';

--3.v1 Який максимальний вік мешканців по містіх та країнах (вивести вік, місто та країну)
SELECT
  u.max_age,
  cityname,
  country
FROM (SELECT
        max(age) AS max_age,
        cityid
      FROM userinfo
      GROUP BY cityid) u
  JOIN city ON u.cityid = city.cityid;

-- 3.v2
SELECT
  max(age),
  cityname,
  country
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
GROUP BY country, cityname;

-- 4.v1 Який мінімальний вік мешканців по містіх та країнах (вивести вік, місто та країну)
SELECT
  u.max_age,
  cityname,
  country
FROM (SELECT
        min(age) AS max_age,
        cityid
      FROM userinfo
      GROUP BY cityid) u
  JOIN city ON u.cityid = city.cityid;

-- 4.v2
SELECT
  min(age),
  cityname,
  country
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
GROUP BY cityname, country;

-- 5.v1 Який середній вік мешканців по містіх та країнах (вивести вік, місто та країну)
SELECT
  u.max_age,
  cityname,
  country
FROM (SELECT
        avg(age) AS max_age,
        cityid
      FROM userinfo
      GROUP BY cityid) u
  JOIN city ON u.cityid = city.cityid;

--5.v2
SELECT
  avg(age),
  cityname,
  country
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
GROUP BY cityname, country;

--6.v1 Вивести всі міста які не є в Україні
SELECT cityname
FROM city
WHERE city.country NOT IN ('Ukraine');

--6.v2
SELECT
  cityname,
  country
FROM city
GROUP BY cityname, country
HAVING country NOT LIKE 'Ukraine';

-- 7 Вивести кількість людей по містах із віком більшим за 16 та меншим за 39 років
SELECT
  cityname,
  count(userid)
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
WHERE age > 16 AND age < 39
GROUP BY cityname;

-- 8  Вивести всіх людей по містах із віком більшим за 20 та меншим за 40 років
SELECT
  cityname,
  userid,
  username,
  age,
  country
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
WHERE age > 20 AND age < 40
ORDER BY cityname;

-- 9.v1 Вивисти міста максимальний вік мешканців яких білше ніж середній по всіх
SELECT cityname
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
WHERE age > (SELECT avg(age)
             FROM userinfo)
GROUP BY cityname;

--9.v2
SELECT
  max(age) AS max_age,
  username
FROM userinfo
GROUP BY username
HAVING avg(age);

--9.v_from Orest
SELECT cityname
FROM userinfo
  JOIN city ON userinfo.cityid = city.cityid
GROUP BY cityname, age
HAVING avg(age) <= age;


--Other queries
INSERT INTO city (cityname, country) VALUES ('Ternopil', 'Ukraine');
INSERT INTO userinfo (username, age, cityid)
VALUES ('User18', 78, 5), ('User19', 12, 5), ('User20', 35, 5), ('User21', 63, 5);