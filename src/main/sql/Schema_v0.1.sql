DROP TABLE answers;
DROP TABLE questions;
CREATE TABLE  IF NOT EXISTS questions (
  uid SERIAL PRIMARY KEY,
  question_value VARCHAR(400)
);

CREATE TABLE IF NOT EXISTS answers(
  uid SERIAL PRIMARY KEY,
  question_id INT NOT NULL REFERENCES questions(uid),
  answer_value  VARCHAR(400),
  right_answer BOOL

)