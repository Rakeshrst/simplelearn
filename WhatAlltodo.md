CREATE TABLE `bootcamp`.`instructor` (
  `id` INT GENERATED ALWAYS AS (),
  `instructor_name` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));