
-- WARNING THIS SCRIPT WILL DELETE ALL YOUR CURRENT DATA!
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE leagueofchallenges.avatar;
INSERT INTO leagueofchallenges.avatar VALUES(1, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1');
INSERT INTO leagueofchallenges.avatar VALUES(2, '2038-01-19 03:14:07', 'avatar_img_2', 'avatar_name_2');

TRUNCATE TABLE leagueofchallenges.role;
INSERT INTO leagueofchallenges.role VALUES(1, '1970-01-01 00:00:01', 'uzivatel');
INSERT INTO leagueofchallenges.role VALUES(2, '2038-01-19 03:14:07', 'admin');

TRUNCATE TABLE leagueofchallenges.user;
INSERT INTO leagueofchallenges.user VALUES(1, '1970-01-01 00:00:01', 'Tomas', 'Bartosek', 'AsylumDemon', 'AsylumDemon@praise.sun', 'tomas123', '1970-01-01 01:01:01', '1', '1');
INSERT INTO leagueofchallenges.user VALUES(2, '2038-01-19 03:14:07', 'Jan', 'Mares', 'BellGargoyle', 'BellGargoyle@praise.sun', 'jan123', '1970-02-02 02:02:02', '2', '2');

TRUNCATE TABLE leagueofchallenges.report;
INSERT INTO leagueofchallenges.report VALUES(1, '1970-01-01 00:00:01', 'report_reason_1', 'report_text_1', '1', '2');
INSERT INTO leagueofchallenges.report VALUES(2, '2038-01-19 03:14:07', 'report_reason_2', 'report_text_2', '2', '1');

TRUNCATE TABLE leagueofchallenges.friend;
INSERT INTO leagueofchallenges.friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO leagueofchallenges.friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

TRUNCATE TABLE leagueofchallenges.message;
INSERT INTO leagueofchallenges.message VALUES(1, '1970-01-01 00:00:01', 'message_text_1', 'message_subject_1', '1', '2');
INSERT INTO leagueofchallenges.message VALUES(2, '2038-01-19 03:14:07', 'message_text_2', 'message_subject_2', '2', '1');

TRUNCATE TABLE leagueofchallenges.game_param;
INSERT INTO leagueofchallenges.game_param VALUES(1, 'game_param_name_1', '1970-01-01 00:00:01', 'game_param_value_1', '1');
INSERT INTO leagueofchallenges.game_param VALUES(2, 'game_param_name_2', '2038-01-19 03:14:07', 'game_param_value_2', '2');

-- TODO add created to game table
TRUNCATE TABLE leagueofchallenges.game;
INSERT INTO leagueofchallenges.game VALUES(1, 'Sachy', 'game_description_1');
INSERT INTO leagueofchallenges.game VALUES(2, 'Fotbal', 'game_description_2');

-- TODO add created to league table
-- TODO add some column that says if its gold, bronze, diamond or whatever league
TRUNCATE TABLE leagueofchallenges.league;
INSERT INTO leagueofchallenges.league VALUES(1, 'Centipede Deamon League', 'league_description_1', 1);
INSERT INTO leagueofchallenges.league VALUES(2, 'Moonlight Butterfly League', 'league_description_2', 2);

TRUNCATE TABLE leagueofchallenges.team;
INSERT INTO leagueofchallenges.team VALUES(1, '1970-01-01 00:00:01', 'Nito team', 'team_description_1', '1', 42);
INSERT INTO leagueofchallenges.team VALUES(2, '2038-01-19 03:14:07', 'Ornstein and Smough', 'team_description_2', '2', 23);

TRUNCATE TABLE leagueofchallenges.team_user;
INSERT INTO leagueofchallenges.team_user VALUES(1, 1, 1);
INSERT INTO leagueofchallenges.team_user VALUES(2, 2, 2);

-- TODO add created to challenge table
TRUNCATE TABLE leagueofchallenges.challenge;
INSERT INTO leagueofchallenges.challenge VALUES(1, 1, 2, '49.902238;16.439289', '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_1');
INSERT INTO leagueofchallenges.challenge VALUES(2, 2, 1, '49.904753;16.445780', '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2');

-- TODO move created to upper column in challenge_result table
TRUNCATE TABLE leagueofchallenges.challenge_result;
INSERT INTO leagueofchallenges.challenge_result VALUES(1, 1, '2:1', 'challenge_result_description_1', '1970-01-01 00:00:01', 1, 0);
INSERT INTO leagueofchallenges.challenge_result VALUES(2, 2, '2:3', 'challenge_result_description_2', '2038-01-19 03:14:07', 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
