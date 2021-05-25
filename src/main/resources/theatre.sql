create SCHEMA `theatre` ;


create TABLE `theatre`.`director` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `telephone` VARCHAR(45) NULL,
  `experience` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


create TABLE `theatre`.`repetition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NULL,
  `place` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


create TABLE `theatre`.`actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `telephone` VARCHAR(45) NULL,
  `experience` VARCHAR(45) NULL,
  `id_repetition` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_repo`
    FOREIGN KEY (`id_repetition`)
    REFERENCES `theatre`.`repetition` (`id`)
    ON delete NO ACTION
    ON update NO ACTION);


create TABLE `theatre`.`performance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `date` VARCHAR(45) NULL,
  `id_director` INT NULL,
  `id_repetition` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_repetition`
    FOREIGN KEY (`id_repetition`)
    REFERENCES `theatre`.`repetition` (`id`)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT `fk_director`
    FOREIGN KEY (`id_director`)
    REFERENCES `theatre`.`director` (`id`)
    ON delete NO ACTION
    ON update NO ACTION);


