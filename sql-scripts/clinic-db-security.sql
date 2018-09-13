DROP DATABASE  IF EXISTS `clinic-database-security`;

CREATE DATABASE  IF NOT EXISTS `clinic-database-security`;
USE `clinic-database-security`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES 
('admin','{bcrypt}$2a$10$darfHQ1kAgYzCGRL0A60TetBrDPCRunh14O9l5W6CCKd7dMQnSOWi',1 );

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `AUTHORITIES_UNIQUE` (`username`,`authority`),
  CONSTRAINT `FK_AUTHORITIES_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities` 
VALUES 
('admin','ROLE_ADMIN'),
('admin','ROLE_PATIENT'),
('admin','ROLE_DOCTOR');