/*
SQLyog Community v12.01 (64 bit)
MySQL - 5.6.17 : Database - geometry-infringement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`geometry-infringement` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `geometry-infringement`;

/*Table structure for table `achievement` */

DROP TABLE IF EXISTS `achievement`;

CREATE TABLE `achievement` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `naam` tinytext NOT NULL,
  `beschrijving` tinytext,
  `foto` tinytext,
  `moeilijkheidsgraad` tinytext NOT NULL,
  `type` tinytext NOT NULL,
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `achievement` */

/*Table structure for table `clan` */

DROP TABLE IF EXISTS `clan`;

CREATE TABLE `clan` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `clannaam` tinytext NOT NULL,
  `spelernr` int(10) NOT NULL,
  `foto` tinytext,
  `rank` int(32) NOT NULL DEFAULT '1',
  `achievementnr` int(32) DEFAULT NULL,
  PRIMARY KEY (`nr`),
  KEY `spelers` (`spelernr`),
  KEY `achievementsClan` (`achievementnr`),
  CONSTRAINT `achievementsClan` FOREIGN KEY (`achievementnr`) REFERENCES `achievement` (`nr`),
  CONSTRAINT `spelers` FOREIGN KEY (`spelernr`) REFERENCES `speler` (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `clan` */

/*Table structure for table `drone` */

DROP TABLE IF EXISTS `drone`;

CREATE TABLE `drone` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `naam` tinytext NOT NULL,
  `beschrijving` tinytext,
  `hp` int(5) NOT NULL DEFAULT '100',
  `kracht` int(5) DEFAULT '2',
  `uiterlijk` tinytext,
  `level` int(10) NOT NULL DEFAULT '1',
  `experience` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `drone` */

/*Table structure for table `schip` */

DROP TABLE IF EXISTS `schip`;

CREATE TABLE `schip` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `hp` int(10) NOT NULL DEFAULT '100',
  `kracht` int(10) NOT NULL DEFAULT '10',
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `schip` */

/*Table structure for table `skin` */

DROP TABLE IF EXISTS `skin`;

CREATE TABLE `skin` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `skinnaam` tinytext NOT NULL,
  `foto` tinytext,
  `kost` int(32) NOT NULL,
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `skin` */

/*Table structure for table `speler` */

DROP TABLE IF EXISTS `speler`;

CREATE TABLE `speler` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `gebruikersnaam` tinytext NOT NULL,
  `wachtwoord` tinytext NOT NULL,
  `email` tinytext NOT NULL,
  `level` int(2) NOT NULL DEFAULT '1',
  `experience` int(32) NOT NULL DEFAULT '0',
  `profielfoto` tinytext,
  `rank` int(128) DEFAULT NULL,
  `nuggets` int(32) NOT NULL DEFAULT '0',
  `golden nuggets` int(32) NOT NULL DEFAULT '0',
  `achievementnr` int(10) DEFAULT NULL,
  `clannr` int(10) DEFAULT NULL,
  `dronenr` int(10) DEFAULT NULL,
  `schipnr` int(10) DEFAULT NULL,
  PRIMARY KEY (`nr`),
  KEY `achievementsSpeler` (`achievementnr`),
  KEY `clanSpeler` (`clannr`),
  KEY `droneSpeler` (`dronenr`),
  KEY `schipSpeler` (`schipnr`),
  CONSTRAINT `achievementsSpeler` FOREIGN KEY (`achievementnr`) REFERENCES `achievement` (`nr`),
  CONSTRAINT `clanSpeler` FOREIGN KEY (`clannr`) REFERENCES `clan` (`nr`),
  CONSTRAINT `droneSpeler` FOREIGN KEY (`dronenr`) REFERENCES `drone` (`nr`),
  CONSTRAINT `schipSpeler` FOREIGN KEY (`schipnr`) REFERENCES `schip` (`nr`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `speler` */

insert  into `speler`(`nr`,`gebruikersnaam`,`wachtwoord`,`email`,`level`,`experience`,`profielfoto`,`rank`,`nuggets`,`golden nuggets`,`achievementnr`,`clannr`,`dronenr`,`schipnr`) values (1,'Yentl','-116105118-27-7565421-67-238-6777-1821-33-79103-87-56115-475-72-883111142-7672-8724','yentl.volcke@student.howest.be',1,0,NULL,0,0,0,NULL,NULL,NULL,NULL),(2,'Renzie','-617527-27-8617120-123916-9115-47-12192247-106-67-11523-4-118-79886212439-949785','renzie.omana@student.howest.be',1,0,NULL,0,0,0,NULL,NULL,NULL,NULL);

/*Table structure for table `upgrade` */

DROP TABLE IF EXISTS `upgrade`;

CREATE TABLE `upgrade` (
  `nr` int(5) NOT NULL AUTO_INCREMENT,
  `naam` tinytext NOT NULL,
  `beschrijving` text,
  `level` int(2) NOT NULL,
  `foto` tinytext,
  `kost` int(32) NOT NULL,
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `upgrade` */

/*Table structure for table `vijand` */

DROP TABLE IF EXISTS `vijand`;

CREATE TABLE `vijand` (
  `nr` int(10) NOT NULL AUTO_INCREMENT,
  `naam` tinytext NOT NULL,
  `beschrijving` tinytext,
  `hp` int(2) NOT NULL,
  `kracht` int(5) NOT NULL,
  `uiterlijk` tinytext NOT NULL,
  `experience` int(32) NOT NULL DEFAULT '100',
  `score` int(32) NOT NULL DEFAULT '1000',
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vijand` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
