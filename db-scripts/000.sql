-- MySQL Script generated by MySQL Workbench
-- Thu Nov  2 20:03:24 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema LeagueOfChallenges
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `LeagueOfChallenges` ;

-- -----------------------------------------------------
-- Schema LeagueOfChallenges
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LeagueOfChallenges` DEFAULT CHARACTER SET utf8 ;
USE `LeagueOfChallenges` ;

-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Avatar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Avatar` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Avatar` (
  `avatar_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `avatar_image` BLOB NOT NULL,
  `avatar_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`avatar_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Role` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Role` (
  `role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`User` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`User` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'played games budem brat jako count z nakej tabulky',
  `created` DATETIME NOT NULL default CURRENT_TIMESTAMP,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `last_login` DATETIME,
  `Avatars_avatar_id` INT UNSIGNED NOT NULL,
  `Role_role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_Users_Avatars1_idx` (`Avatars_avatar_id` ASC),
  INDEX `fk_Users_Role1_idx` (`Role_role_id` ASC),
  CONSTRAINT `fk_Users_Avatars1`
    FOREIGN KEY (`Avatars_avatar_id`)
    REFERENCES `LeagueOfChallenges`.`Avatar` (`avatar_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Role1`
    FOREIGN KEY (`Role_role_id`)
    REFERENCES `LeagueOfChallenges`.`Role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Game` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Game` (
  `game_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `game_name` VARCHAR(45) NOT NULL,
  `game_description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`game_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Friend` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Friend` (
  `friend_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_friend_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`friend_id`),
  INDEX `fk_Friends_User1_idx` (`user_friend_id` ASC),
  INDEX `fk_Friend_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Friends_User1`
    FOREIGN KEY (`user_friend_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Friend_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Message` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Message` (
  `message_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL default CURRENT_TIMESTAMP,
  `text` VARCHAR(200) NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `from_user_id` INT UNSIGNED NOT NULL,
  `to_user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`message_id`),
  INDEX `fk_Message_User1_idx` (`from_user_id` ASC),
  INDEX `fk_Message_User2_idx` (`to_user_id` ASC),
  CONSTRAINT `fk_Message_User1`
    FOREIGN KEY (`from_user_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_User2`
    FOREIGN KEY (`to_user_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`League`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`League` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`League` (
  `leagu_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `league_name` VARCHAR(45) NOT NULL,
  `league_description` VARCHAR(45) NOT NULL,
  `Game_game_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`leagu_id`),
  INDEX `fk_League_Game1_idx` (`Game_game_id` ASC),
  CONSTRAINT `fk_League_Game1`
    FOREIGN KEY (`Game_game_id`)
    REFERENCES `LeagueOfChallenges`.`Game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Team` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Team` (
  `team_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'played games budem brat jako count z nakej tabulky\npocet vyher tymu a pocet proher tymu taky budem brat z nakej jinej tabulky(jako COUNT) sracka aby to bylo tady\nvlozime ho do bronze ligy hry co si zvoli a z ligy pak vime i hru ktera se hraje',
  `created` DATETIME NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `League_leagu_id` INT UNSIGNED NOT NULL,
  `rating` INT NULL,
  PRIMARY KEY (`team_id`),
  INDEX `fk_Team_League1_idx` (`League_leagu_id` ASC),
  CONSTRAINT `fk_Team_League1`
    FOREIGN KEY (`League_leagu_id`)
    REFERENCES `LeagueOfChallenges`.`League` (`leagu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Team_User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Team_User` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Team_User` (
  `team_user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_team_id` INT UNSIGNED NOT NULL,
  `user_user_id` INT UNSIGNED NOT NULL,
  INDEX `fk_Team_User_Team1_idx` (`team_team_id` ASC),
  INDEX `fk_Team_User_User1_idx` (`user_user_id` ASC),
  PRIMARY KEY (`team_user_id`),
  CONSTRAINT `fk_Team_User_Team1`
    FOREIGN KEY (`team_team_id`)
    REFERENCES `LeagueOfChallenges`.`Team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Team_User_User1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Challenge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Challenge` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Challenge` (
  `challenge_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Hru zjistis getTeam.getLeague.getGame',
  `challenger_team_id` INT UNSIGNED NOT NULL,
  `oponnent_team_id` INT UNSIGNED NULL,
  `Challenge_start` DATETIME NOT NULL,
  `Challenge_end` DATETIME NOT NULL,
  `Text` VARCHAR(45) NULL,
  `CoordsLat` VARCHAR(255) NOT NULL,
  `CoordsLng` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`challenge_id`),
  INDEX `fk_Challenge_Team1_idx` (`challenger_team_id` ASC),
  INDEX `fk_Challenge_Team2_idx` (`oponnent_team_id` ASC),
  CONSTRAINT `fk_Challenge_Team1`
    FOREIGN KEY (`challenger_team_id`)
    REFERENCES `LeagueOfChallenges`.`Team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Challenge_Team2`
    FOREIGN KEY (`oponnent_team_id`)
    REFERENCES `LeagueOfChallenges`.`Team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Challenge_Result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Challenge_Result` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Challenge_Result` (
  `challenge_result_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Challenges_challenge_id` INT UNSIGNED NOT NULL,
  `score` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  `winner_team_id` INT UNSIGNED NOT NULL,
  `draw` TINYINT(1) NULL,
  PRIMARY KEY (`challenge_result_id`),
  INDEX `fk_Challenge_results_Challenges1_idx` (`Challenges_challenge_id` ASC),
  INDEX `fk_Challenge_results_Teams1_idx` (`winner_team_id` ASC),
  CONSTRAINT `fk_Challenge_results_Challenges1`
    FOREIGN KEY (`Challenges_challenge_id`)
    REFERENCES `LeagueOfChallenges`.`Challenge` (`challenge_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Challenge_results_Teams1`
    FOREIGN KEY (`winner_team_id`)
    REFERENCES `LeagueOfChallenges`.`Team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Report` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Report` (
  `report_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `reason` VARCHAR(45) NOT NULL,
  `reason_text` VARCHAR(255) NULL,
  `reporting_user_id` INT UNSIGNED NOT NULL,
  `reported_user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`report_id`),
  INDEX `fk_Reports_Users1_idx` (`reporting_user_id` ASC),
  INDEX `fk_Reports_Users2_idx` (`reported_user_id` ASC),
  CONSTRAINT `fk_Reports_Users1`
    FOREIGN KEY (`reporting_user_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reports_Users2`
    FOREIGN KEY (`reported_user_id`)
    REFERENCES `LeagueOfChallenges`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeagueOfChallenges`.`Game_param`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeagueOfChallenges`.`Game_param` ;

CREATE TABLE IF NOT EXISTS `LeagueOfChallenges`.`Game_param` (
  `game_param_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created` DATETIME NOT NULL,
  `value` VARCHAR(255) NULL,
  `Game_game_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`game_param_id`),
  INDEX `fk_Game_param_Game1_idx` (`Game_game_id` ASC),
  CONSTRAINT `fk_Game_param_Game1`
    FOREIGN KEY (`Game_game_id`)
    REFERENCES `LeagueOfChallenges`.`Game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
