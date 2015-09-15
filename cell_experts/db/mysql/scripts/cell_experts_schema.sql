CREATE  TABLE IF NOT EXISTS `cell_experts`.`employee_timesheet` (
  `employee_id` INT NULL ,
  `weekend_dt` DATE NULL ,
  `submitted` TINYINT(1) NULL ,
  INDEX `employee_id_idx` (`employee_id` ASC) ,
  CONSTRAINT `employee_id`
    FOREIGN KEY (`employee_id` )
    REFERENCES `cell_experts`.`employees` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	PRIMARY KEY (`employee_id`,`weekend_dt`)
)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `cell_experts`.`employees` (
  `employee_id` INT NOT NULL ,
  `first_name` VARCHAR(45) NULL ,
  `last_name` VARCHAR(45) NULL ,
  `start_dt` DATE NULL ,
  `phone` INT NULL ,
  `email` VARCHAR(45) NULL ,
  `end_dt` VARCHAR(45) NULL ,
  `address` VARCHAR(100) NULL ,
  PRIMARY KEY (`employee_id`) )
ENGINE = InnoDB