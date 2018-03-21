
-- WARNING THIS SCRIPT WILL DELETE ALL YOUR CURRENT DATA!
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE leagueofchallenges.avatar;
INSERT INTO leagueofchallenges.avatar VALUES(1, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1');
INSERT INTO leagueofchallenges.avatar VALUES(2, '2038-01-19 03:14:07', 'avatar_img_2', 'avatar_name_2');
INSERT INTO leagueofchallenges.avatar VALUES(3, '2038-01-19 03:14:07', 'avatar_img_2', 'avatar_1');

TRUNCATE TABLE leagueofchallenges.role;
INSERT INTO leagueofchallenges.role VALUES(1, '1970-01-01 00:00:01', 'USER');
INSERT INTO leagueofchallenges.role VALUES(2, '2038-01-19 03:14:07', 'ADMIN');

TRUNCATE TABLE leagueofchallenges.user; -- password is: test123
INSERT INTO leagueofchallenges.user VALUES(1, '1970-01-01 00:00:01', 'test', 'test', 'test', 'test@praise.sun', '$2a$10$aYNCQd7pNMRkqbqTAsP90eDROSepocdpQxuG/AXVgayK5S.gsvKKm', '1970-01-01 01:01:01', '1', '1', '1');
INSERT INTO leagueofchallenges.user VALUES(2, '1970-01-01 00:00:01', 'tes2', 'tes2', 'tes2', 'test@praise.sun', '$2a$10$aYNCQd7pNMRkqbqTAsP90eDROSepocdpQxuG/AXVgayK5S.gsvKKm', '1970-01-01 01:01:01', '1', '1', '1');

TRUNCATE TABLE leagueofchallenges.report;
INSERT INTO leagueofchallenges.report VALUES(1, '1970-01-01 00:00:01', 'report_reason_1', 'report_text_1', '1', '2');
INSERT INTO leagueofchallenges.report VALUES(2, '2038-01-19 03:14:07', 'report_reason_2', 'report_text_2', '2', '1');

TRUNCATE TABLE leagueofchallenges.friend;
INSERT INTO leagueofchallenges.friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO leagueofchallenges.friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

TRUNCATE TABLE leagueofchallenges.message;
INSERT INTO leagueofchallenges.message VALUES(1, '1970-01-01 00:00:01', 'message_text_1', 'message_subject_1', '0', '1', '2');
INSERT INTO leagueofchallenges.message VALUES(2, '2038-01-19 03:14:07', 'message_text_2', 'message_subject_2', '0', '2', '1');

TRUNCATE TABLE leagueofchallenges.game_param;
INSERT INTO leagueofchallenges.game_param VALUES(1, 'game_param_name_1', '1970-01-01 00:00:01', 'game_param_value_1', '1');
INSERT INTO leagueofchallenges.game_param VALUES(2, 'game_param_name_2', '2038-01-19 03:14:07', 'game_param_value_2', '2');

-- TODO add created to game table
TRUNCATE TABLE leagueofchallenges.game;
INSERT INTO leagueofchallenges.game VALUES(1, 'chess', 'game_description_1',1);
INSERT INTO leagueofchallenges.game VALUES(2, 'football', 'game_description_2',2);

-- TODO add created to league table
-- TODO add some column that says if its gold, bronze, diamond or whatever league
TRUNCATE TABLE leagueofchallenges.league;
INSERT INTO leagueofchallenges.league VALUES(1, 'GOLD', 'Centipede Deamon League', 1);
INSERT INTO leagueofchallenges.league VALUES(2, 'SILVER', 'Centipede Deamon League', 1);
INSERT INTO leagueofchallenges.league VALUES(3, 'BRONZE', 'Centipede Deamon League', 1);
INSERT INTO leagueofchallenges.league VALUES(4, 'GOLD', 'Moonlight Butterfly League', 2);
INSERT INTO leagueofchallenges.league VALUES(5, 'SILVER', 'Moonlight Butterfly League', 2);
INSERT INTO leagueofchallenges.league VALUES(6, 'BRONZE', 'Moonlight Butterfly League', 2);

TRUNCATE TABLE leagueofchallenges.team;
INSERT INTO leagueofchallenges.team VALUES(1, '1970-01-01 00:00:01', 'Nito team', 'team_description_1', '1', 42);
INSERT INTO leagueofchallenges.team VALUES(2, '2038-01-19 03:14:07', 'Ornstein and Smough', 'team_description_2', '2', 23);

TRUNCATE TABLE leagueofchallenges.team_user;
INSERT INTO leagueofchallenges.team_user VALUES(1, 1, 1);
INSERT INTO leagueofchallenges.team_user VALUES(2, 2, 2);

-- TODO add created to challenge table

SET FOREIGN_KEY_CHECKS = 1;
