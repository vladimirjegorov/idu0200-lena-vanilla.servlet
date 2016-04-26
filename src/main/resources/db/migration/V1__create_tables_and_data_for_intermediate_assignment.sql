CREATE TABLE team (
  id             SERIAL       NOT NULL,
  title           VARCHAR(100) NOT NULL,
  members_amount INTEGER DEFAULT 0 ,
  description        TEXT,
  PRIMARY KEY (id)
);

-- Random copy-paste from wikipedia
INSERT INTO team (title, members_amount, description)
VALUES ('Bayern München', 10, 'German sports club based in Munich, Bavaria, Germany. It is best known for its professional football team, which plays in the Bundesliga, the top tier of the German football league system, and is the most successful club in German football history, having won a record 25 national titles and 17 national cups');

INSERT INTO team (title, members_amount, description)
VALUES ('Barcelona', 11, 'Barcelona is one of the most supported teams in the world, and has the largest social media following in the world among sports teams.[9][10][11] Barcelona''s players have won a record number of Ballon d''Or awards (11), as well as a record number of FIFA World Player of the Year awards (7).');

INSERT INTO team (title, members_amount, description)
VALUES ('Real Madrid', 12, 'The club is the most valuable sports team in the world, worth €2.5 billion ($3.4 billion) and the world''s highest-earning football club for 2013–14, with an annual revenue of €549.5 million.[5][6][7] The club is one of the most widely supported teams in the world.');
