-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema health_care
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema health_care
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `health_care` DEFAULT CHARACTER SET utf8 ;
USE `health_care` ;

-- -----------------------------------------------------
-- Table `health_care`.`tel_auth_number`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`tel_auth_number` (
  `tel_number` VARCHAR(11) NOT NULL COMMENT '전화번호',
  `auth_number` VARCHAR(4) NOT NULL COMMENT '인증번호',
  PRIMARY KEY (`tel_number`),
  UNIQUE INDEX `tel_number_UNIQUE` (`tel_number` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = '전화번호 인증';


-- -----------------------------------------------------
-- Table `health_care`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`customer` (
  `user_id` VARCHAR(20) NOT NULL COMMENT '아이디',
  `name` VARCHAR(15) NOT NULL COMMENT '이름',
  `nickname` VARCHAR(30) NOT NULL COMMENT '닉네임',
  `password` VARCHAR(255) CHARACTER SET 'armscii8' NOT NULL COMMENT '비밀번호',
  `tel_number` VARCHAR(11) NOT NULL COMMENT '전화번호',
  `join_path` VARCHAR(5) NOT NULL COMMENT '가입경로',
  `sns_id` VARCHAR(255) NULL COMMENT 'snsID',
  `height` FLOAT NOT NULL COMMENT '키(cm)',
  `profile_image` TEXT NULL COMMENT '프로필이미지',
  `personal_goals` TEXT NULL COMMENT '개인 목표',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `tel_number_UNIQUE` (`tel_number` ASC) VISIBLE,
  CONSTRAINT `tel_auth_number_customer`
    FOREIGN KEY (`tel_number`)
    REFERENCES `health_care`.`tel_auth_number` (`tel_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자정보';


-- -----------------------------------------------------
-- Table `health_care`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`board` (
  `board_number` INT NOT NULL AUTO_INCREMENT COMMENT '게시물 번호',
  `board_title` VARCHAR(80) NOT NULL COMMENT '게시물 제목',
  `user_id` VARCHAR(20) NOT NULL COMMENT '게시물을 작성한 사용자 아이디',
  `board_category` VARCHAR(30) NOT NULL COMMENT '게시물 카테고리',
  `board_tag` VARCHAR(30) NOT NULL COMMENT '게시물 태그',
  `board_contents` TEXT NOT NULL COMMENT '게시물 내용',
  `youtube_video_link` VARCHAR(255) NULL COMMENT '유튜브비디오링크',
  `board_upload_date` DATE NOT NULL COMMENT '사용자 작성 게시물 생성날짜',
  `board_view_count` INT NOT NULL DEFAULT 0 COMMENT '조회수',
  `board_like_count` INT NOT NULL DEFAULT 0 COMMENT '게시물 추천 수',
  PRIMARY KEY (`board_number`),
  INDEX `post_category_INDEX` (`board_category` ASC) VISIBLE,
  UNIQUE INDEX `board_number_UNIQUE` (`board_number` ASC) VISIBLE,
  CONSTRAINT `customer_user_board`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 게시물';


-- -----------------------------------------------------
-- Table `health_care`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`comment` (
  `comment_number` INT NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
  `user_id` VARCHAR(20) NOT NULL COMMENT '댓글을 작성한 사용자 아이디',
  `board_number` INT NOT NULL COMMENT '댓글을 작성한 게시물 번호',
  `comment_contents` TEXT NOT NULL COMMENT '댓글 내용',
  `comment_date` DATE NOT NULL COMMENT '댓글  작성날짜',
  `comment_like_count` INT NOT NULL DEFAULT 0 COMMENT '댓글 추천 수',
  UNIQUE INDEX `comments_number_UNIQUE` (`comment_number` ASC) VISIBLE,
  PRIMARY KEY (`comment_number`),
  INDEX `user_post_comments_idx` (`board_number` ASC) VISIBLE,
  CONSTRAINT `user_board_comments`
    FOREIGN KEY (`board_number`)
    REFERENCES `health_care`.`board` (`board_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_comments`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 게시물 댓글';


-- -----------------------------------------------------
-- Table `health_care`.`meal_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`meal_schedule` (
  `meal_schedule_number` INT NOT NULL AUTO_INCREMENT COMMENT '식단 스케줄 번호',
  `user_id` VARCHAR(20) NOT NULL COMMENT '아이디',
  `meal_title` VARCHAR(20) NOT NULL COMMENT '식사타입(일정 제목)',
  `meal_memo` TEXT NOT NULL COMMENT '식품정보(일정 내용)',
  `meal_schedule_start` DATE NOT NULL COMMENT '스케줄 등록을 위한 시작날짜',
  `meal_schedule_end` DATE NOT NULL COMMENT '스케줄 등록을 위한 마지막날짜\n',
  PRIMARY KEY (`meal_schedule_number`),
  UNIQUE INDEX `meal_schedule_number_UNIQUE` (`meal_schedule_number` ASC) VISIBLE,
  INDEX `customer_meal_schedule_idx` (`user_id` ASC) INVISIBLE,
  INDEX `meal_title_INDEX` (`meal_title` ASC) VISIBLE,
  CONSTRAINT `customer_meal_schedule`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 식단';


-- -----------------------------------------------------
-- Table `health_care`.`health_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`health_schedule` (
  `health_schedule_number` INT NOT NULL AUTO_INCREMENT COMMENT '운동 스케줄 번호',
  `user_id` VARCHAR(20) NOT NULL COMMENT '아이디',
  `health_title` VARCHAR(20) NOT NULL COMMENT '일정 제목',
  `health_memo` TEXT NOT NULL COMMENT '일정 내용\n',
  `health_schedule_start` DATE NOT NULL COMMENT '스케줄 등록을 위한 시작날짜\n',
  `health_schedule_end` DATE NOT NULL COMMENT '스케줄 등록을 위한 마지막날짜\n',
  PRIMARY KEY (`health_schedule_number`),
  UNIQUE INDEX `meal_schedule_number_UNIQUE` (`health_schedule_number` ASC) VISIBLE,
  INDEX `cutomer_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `cutomer_health_schedule`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 운동 정보';


-- -----------------------------------------------------
-- Table `health_care`.`three_major_lift`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`three_major_lift` (
  `three_major_lift_number` INT NOT NULL AUTO_INCREMENT COMMENT '사용자 3대 측정 정보 번호',
  `user_id` VARCHAR(20) NOT NULL COMMENT '아이디',
  `deadlift` FLOAT NULL COMMENT '데드리프트(kg)',
  `bench_press` FLOAT NULL COMMENT '벤치프레스(kg)',
  `squat` FLOAT NULL COMMENT '스쿼트(kg)',
  `three_major_lift_date` DATE NOT NULL COMMENT '사용자 3대 측정 정보 등록 날짜',
  PRIMARY KEY (`three_major_lift_number`),
  UNIQUE INDEX `user_id_UNIQUE` (`three_major_lift_number` ASC) VISIBLE,
  CONSTRAINT `customer_health_machine_measurement`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 3대 측정 정보';


-- -----------------------------------------------------
-- Table `health_care`.`board_health_map`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`board_health_map` (
  `board_number` INT NOT NULL COMMENT '게시물 번호',
  `map_lat` FLOAT NOT NULL COMMENT '위도',
  `map_lng` FLOAT NOT NULL COMMENT '경도',
  PRIMARY KEY (`board_number`),
  UNIQUE INDEX `board_number_UNIQUE` (`board_number` ASC) VISIBLE,
  CONSTRAINT `user_board_board_health_map`
    FOREIGN KEY (`board_number`)
    REFERENCES `health_care`.`board` (`board_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 게시물 헬스장 지도';


-- -----------------------------------------------------
-- Table `health_care`.`meal_schedule_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`meal_schedule_detail` (
  `meal_schedule_detail_number` INT NOT NULL AUTO_INCREMENT COMMENT '식품정보 번호',
  `meal_schedule_number` INT NOT NULL COMMENT '식단 스케줄 번호',
  `meal_name` VARCHAR(30) NOT NULL COMMENT '식품명',
  `meal_kcal` FLOAT NOT NULL COMMENT '칼로리',
  `meal_count` INT NOT NULL COMMENT '식품 갯수',
  PRIMARY KEY (`meal_schedule_detail_number`),
  UNIQUE INDEX `meal_schedule_detail_number_UNIQUE` (`meal_schedule_detail_number` ASC) VISIBLE,
  INDEX `meal_schedule_number_idx` (`meal_schedule_number` ASC) VISIBLE,
  CONSTRAINT `meal_schedule_meal_schedule_detail`
    FOREIGN KEY (`meal_schedule_number`)
    REFERENCES `health_care`.`meal_schedule` (`meal_schedule_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 식단 식품정보(일정내용)';


-- -----------------------------------------------------
-- Table `health_care`.`user_muscle_fat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`user_muscle_fat` (
  `user_muscle_fat_number` INT NOT NULL AUTO_INCREMENT COMMENT '사용자 신체 정보 번호',
  `user_id` VARCHAR(20) NOT NULL COMMENT '아이디',
  `weight` FLOAT NOT NULL COMMENT '몸무게(kg)',
  `skeletal_muscle_mass` FLOAT NULL COMMENT '골격근량(kg)',
  `body_fat_mass` FLOAT NULL COMMENT '체지방량(kg)',
  `user_muscle_fat_date` DATE NOT NULL COMMENT '사용자 신체 정보 등록 날짜',
  PRIMARY KEY (`user_muscle_fat_number`),
  UNIQUE INDEX `user_muscle_fat_number_UNIQUE` (`user_muscle_fat_number` ASC) VISIBLE,
  CONSTRAINT `customer_user_muscle_fat`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '사용자 신체 정보';


-- -----------------------------------------------------
-- Table `health_care`.`board_file_contents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care`.`board_file_contents` (
  `board_file_number` INT NOT NULL AUTO_INCREMENT,
  `board_number` INT NOT NULL,
  `board_file_contents` TEXT NOT NULL,
  PRIMARY KEY (`board_file_number`),
  UNIQUE INDEX `board_file_number_UNIQUE` (`board_file_number` ASC) VISIBLE,
  INDEX `boards_board_file_contents_idx` (`board_number` ASC) VISIBLE,
  CONSTRAINT `boards_board_file_contents`
    FOREIGN KEY (`board_number`)
    REFERENCES `health_care`.`board` (`board_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
