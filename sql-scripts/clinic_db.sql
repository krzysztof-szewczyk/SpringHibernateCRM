DROP SCHEMA IF EXISTS `clinic-database`;

CREATE SCHEMA `clinic-database`;

use `clinic-database`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `pesel` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PESEL_UNIQUE` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `licence` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PESEL_UNIQUE` (`licence`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature` varchar(10) DEFAULT NULL,
  `type_of_examination` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DOCTOR_idx` (`doctor_id`),
  KEY `FK_PATIENT_idx` (`patient_id`),
  CONSTRAINT `FK_DOCTOR`
  FOREIGN KEY (`doctor_id`) 
  REFERENCES `doctor` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PATIENT`
  FOREIGN KEY (`patient_id`) 
  REFERENCES `patient` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
