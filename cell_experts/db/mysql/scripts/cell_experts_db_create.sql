SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `cell_experts` DEFAULT CHARACTER SET latin1 ;
USE `cell_experts` ;

-- -----------------------------------------------------
-- Table `cell_experts`.`employees`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`employees` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(45) NULL DEFAULT NULL ,
  `last_name` VARCHAR(45) NULL DEFAULT NULL ,
  `start_dt` DATE NULL DEFAULT NULL ,
  `phone` INT(11) NULL DEFAULT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `end_dt` VARCHAR(45) NULL DEFAULT NULL ,
  `address` VARCHAR(100) NULL DEFAULT NULL ,
  PRIMARY KEY (`employee_id`) ,
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 10011
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cell_experts`.`employee_timesheet`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`employee_timesheet` (
  `employee_id` INT(11) NOT NULL ,
  `weekend_dt` DATE NOT NULL ,
  `submitted` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`employee_id`, `weekend_dt`) ,
  INDEX `employee_timesheet_id_idx` (`employee_id` ASC) ,
  INDEX `weekend_dt_idx` (`weekend_dt` ASC) ,
  CONSTRAINT `fk_employee_timesheet`
    FOREIGN KEY (`employee_id` )
    REFERENCES `cell_experts`.`employees` (`employee_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cell_experts`.`daily_timesheet_dtls`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`daily_timesheet_dtls` (
  `employee_id` INT(11) NOT NULL ,
  `today_dt` DATE NOT NULL ,
  `weekend_dt` DATE NOT NULL ,
  `day` VARCHAR(45) NULL DEFAULT NULL ,
  `hours` DOUBLE NULL DEFAULT NULL ,
  `overtime` DECIMAL(10,0) NULL DEFAULT NULL ,
  `dayOff` TINYINT(1) NULL DEFAULT NULL ,
  `minutes` DOUBLE NULL DEFAULT NULL ,
  `cash` DOUBLE NULL DEFAULT NULL ,
  `notes` VARCHAR(75) NULL DEFAULT NULL ,
  `lastuser` VARCHAR(45) NULL DEFAULT NULL ,
  `time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`employee_id`, `today_dt`, `weekend_dt`) ,
  INDEX `employee_timesheet_id_idx` (`employee_id` ASC, `weekend_dt` ASC) ,
  INDEX `today_dt_idx` (`today_dt` ASC) ,
  CONSTRAINT `fk_daily_timesheet`
    FOREIGN KEY (`employee_id` , `weekend_dt` )
    REFERENCES `cell_experts`.`employee_timesheet` (`employee_id` , `weekend_dt` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cell_experts`.`store`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`store` (
  `store_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `city` VARCHAR(45) NULL DEFAULT NULL ,
  `store_nm` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`store_id`) ,
  UNIQUE INDEX `store_id_UNIQUE` (`store_id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cell_experts`.`store_employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`store_employee` (
  `employee_id` INT(11) NOT NULL ,
  `store_id` INT(11) NOT NULL ,
  PRIMARY KEY (`employee_id`, `store_id`) ,
  INDEX `employees_idx` (`employee_id` ASC) ,
  INDEX `store_d_idx` (`store_id` ASC) ,
  CONSTRAINT `fk_employee_id`
    FOREIGN KEY (`employee_id` )
    REFERENCES `cell_experts`.`employees` (`employee_id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_id`
    FOREIGN KEY (`store_id` )
    REFERENCES `cell_experts`.`store` (`store_id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cell_experts`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`users` (
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(60) NOT NULL ,
  `enabled` TINYINT(1) NOT NULL DEFAULT '1' ,
  PRIMARY KEY (`username`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cell_experts`.`user_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`user_roles` (
  `user_role_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `role` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`user_role_id`) ,
  UNIQUE INDEX `uni_username_role` (`role` ASC, `username` ASC) ,
  INDEX `fk_username_idx` (`username` ASC) ,
  CONSTRAINT `fk_username`
    FOREIGN KEY (`username` )
    REFERENCES `cell_experts`.`users` (`username` ))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
