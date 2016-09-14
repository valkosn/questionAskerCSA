INSERT INTO questions (value) VALUES ('question3'), ('question4'), ('question5'), ('question6'), ('question3');
INSERT INTO answers (question_id, value) VALUES (2, '2an1'), (2, 'a3n2'), (2, 'a3n1'), (3, 'anwe1'),
  (3, 'awen1'), (4, 'aqwen1'), (4, 'anqwe'), (4, 'anqwe1'), (5, 'aqwen1'), (5, 'anqwe1'), (5, 'aasdn1'), (5, 'anasd1');

SELECT
  question_value,
  answer_value
FROM questions
  JOIN answers ON questions.uid = answers.question_id
ORDER BY RANDOM()
LIMIT 5;

SELECT answers.value
FROM answers
WHERE question_id = 2;

UPDATE answers
SET right_answer = TRUE
WHERE question_id = 1;

SELECT question_value
FROM questions
ORDER BY RANDOM()
LIMIT 5;

SELECT
  question_value,
  answer_value
FROM questions
  INNER JOIN answers ON questions.uid = answers.question_id
ORDER BY RANDOM();

SELECT
  question_value,
  answer_value
FROM questions
  INNER JOIN answers ON questions.uid = answers.question_id
ORDER BY questions.uid, RANDOM()
LIMIT 5;

SELECT
  questions.uid,
  questions.question_value,
  answer_value
FROM questions
  INNER JOIN answers ON questions.uid = answers.question_id;

SELECT answer_value
FROM answers
WHERE questions_uid_seq = '114';

SELECT answer_value
FROM answers
WHERE question_id = 1 AND right_answer = TRUE;

SELECT
  q.uid,
  q.question_value,
  a.uid,
  a.answer_value
FROM questions q
  JOIN answers a ON q.uid = a.question_id AND q.uid IN (SELECT q1.uid
                                                        FROM questions q1
                                                        ORDER BY RANDOM()
                                                        LIMIT 5);

SELECT
  q.uid,
  q.question_value,
  a.answer_value
FROM questions q
  JOIN answers a ON q.uid = a.question_id AND q.uid IN (SELECT q1.uid
                                                        FROM questions q1
                                                        WHERE q1.category_id = 2
                                                        ORDER BY RANDOM()
                                                        LIMIT 5);


WITH s AS (
    SELECT
      uid,
      category_value
    FROM categories
    WHERE category_value = 'kat2'
), i AS (
  INSERT INTO categories (category_value)
    SELECT
      'kat2'
    WHERE NOT exists(SELECT 1
                     FROM s)
  RETURNING uid, category_value
)
SELECT
  uid,
  category_value
FROM i
UNION ALL
SELECT
  uid,
  category_value
FROM s