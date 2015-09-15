SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `cell_experts` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `cell_experts` ;

-- -----------------------------------------------------
-- Table `cell_experts`.`store`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`store` (
  `store_id` INT NOT NULL AUTO_INCREMENT ,
  `city` VARCHAR(45) NULL ,
  `store_nm` VARCHAR(45) NULL ,
  PRIMARY KEY (`store_id`) ,
  UNIQUE INDEX `store_id_UNIQUE` (`store_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cell_experts`.`employees`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`employees` (
  `employee_id` INT NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(45) NULL ,
  `last_name` VARCHAR(45) NULL ,
  `start_dt` DATE NULL ,
  `phone` INT NULL ,
  `email` VARCHAR(45) NULL ,
  `end_dt` VARCHAR(45) NULL ,
  `address` VARCHAR(100) NULL ,
  PRIMARY KEY (`employee_id`) ,
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 10000;


-- -----------------------------------------------------
-- Table `cell_experts`.`store_employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`store_employee` (
  `employee_id` INT NOT NULL ,
  `store_id` INT NOT NULL ,
  INDEX `employees_idx` (`employee_id` ASC) ,
  INDEX `store_d_idx` (`store_id` ASC) ,
  PRIMARY KEY (`employee_id`, `store_id`) ,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cell_experts`.`employee_timesheet`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`employee_timesheet` (
  `employee_id` INT NOT NULL ,
  `weekend_dt` DATE NOT NULL ,
  `submitted` TINYINT(1) NULL ,
  INDEX `employee_timesheet_id_idx` (`employee_id` ASC) ,
  PRIMARY KEY (`employee_id`, `weekend_dt`) ,
  INDEX `weekend_dt_idx` (`weekend_dt` ASC) ,
  CONSTRAINT `fk_employee_timesheet`
    FOREIGN KEY (`employee_id` )
    REFERENCES `cell_experts`.`employees` (`employee_id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cell_experts`.`daily_timesheet_dtls`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cell_experts`.`daily_timesheet_dtls` (
  `employee_id` INT NOT NULL ,
  `today_dt` DATE NOT NULL ,
  `weekend_dt` DATE NOT NULL ,
  `day` VARCHAR(45) NULL ,
  `hours` DECIMAL(10,0) NULL ,
  `overtime` DECIMAL(10,0) NULL ,
  `leave` TINYINT(1) NULL ,
  INDEX `employee_timesheet_id_idx` (`employee_id` ASC, `weekend_dt` ASC) ,
  PRIMARY KEY (`employee_id`, `today_dt`, `weekend_dt`) ,
  INDEX `today_dt_idx` (`today_dt` ASC) ,
  CONSTRAINT `fk_daily_timesheet`
    FOREIGN KEY (`employee_id` , `weekend_dt` )
    REFERENCES `cell_experts`.`employee_timesheet` (`employee_id` , `weekend_dt` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
