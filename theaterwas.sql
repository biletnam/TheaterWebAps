-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema Theaterwas
--

CREATE DATABASE IF NOT EXISTS Theaterwas;
USE Theaterwas;

--
-- Definition of table `Bestelregel`
--

DROP TABLE IF EXISTS `Bestelregel`;
CREATE TABLE `Bestelregel` (
  `id` smallint(6) unsigned NOT NULL auto_increment,
  `voorstelling` smallint(6) unsigned NOT NULL,
  `rijnummer` smallint(6) NOT NULL,
  `stoelnummer` smallint(6) NOT NULL,
  `klant` smallint(6) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Bestelregel_1` (`klant`),
  KEY `FK_Bestelregel_2` (`voorstelling`),
  CONSTRAINT `FK_Bestelregel_1` FOREIGN KEY (`klant`) REFERENCES `klant` (`id`),
  CONSTRAINT `FK_Bestelregel_2` FOREIGN KEY (`voorstelling`) REFERENCES `voorstelling` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Bestelregel`
--

/*!40000 ALTER TABLE `Bestelregel` DISABLE KEYS */;
INSERT INTO `Bestelregel` (`id`,`voorstelling`,`rijnummer`,`stoelnummer`,`klant`) VALUES 
 (68,1,4,3,1),
 (69,1,4,4,1),
 (70,1,4,5,1),
 (71,1,3,4,2),
 (72,1,3,5,2),
 (73,1,3,6,2),
 (74,1,3,7,2),
 (75,1,2,1,4),
 (76,1,2,2,4),
 (77,1,2,3,4),
 (78,1,3,1,4),
 (79,1,3,2,4),
 (80,1,3,3,4),
 (81,1,5,1,3),
 (82,1,5,2,3),
 (83,1,5,3,3),
 (84,1,5,4,3),
 (85,1,5,5,3),
 (86,1,5,6,3),
 (87,1,5,7,3),
 (88,1,5,8,3),
 (89,1,5,9,3),
 (90,1,5,10,3),
 (91,1,6,1,3),
 (92,1,6,2,3),
 (93,1,6,3,3),
 (94,1,6,4,3),
 (95,1,6,5,3),
 (96,1,6,6,3),
 (97,1,6,7,3),
 (98,1,6,8,3),
 (99,1,6,9,3),
 (100,1,6,10,3),
 (101,1,7,1,3),
 (102,1,7,2,3),
 (103,1,7,3,3),
 (104,1,7,4,3),
 (105,1,7,5,3),
 (106,1,7,6,3),
 (107,1,7,7,3),
 (108,1,7,8,3),
 (109,1,7,9,3),
 (110,1,7,10,3),
 (111,1,8,1,3),
 (112,1,8,2,3),
 (113,1,8,3,3),
 (114,1,8,4,3),
 (115,1,8,5,3),
 (116,1,8,6,3),
 (117,1,8,7,3),
 (118,1,8,8,3),
 (119,1,8,9,3),
 (120,1,8,10,3),
 (121,1,9,4,8),
 (122,1,9,5,8),
 (123,1,9,6,8),
 (127,1,10,6,9),
 (128,2,3,4,9),
 (129,2,3,5,9),
 (130,2,3,6,9),
 (131,2,4,3,7),
 (132,2,4,4,7),
 (133,2,4,5,7),
 (134,2,5,3,7),
 (135,2,5,4,7),
 (136,2,5,5,7),
 (137,2,6,6,10),
 (138,2,6,7,10),
 (139,2,6,8,10),
 (140,3,3,1,11),
 (141,3,3,2,11),
 (142,3,3,3,11),
 (143,3,4,3,11),
 (144,3,4,4,11),
 (145,3,4,5,11),
 (146,3,4,6,11),
 (147,3,4,7,11),
 (148,3,5,3,11),
 (149,3,5,4,11),
 (150,3,5,5,11),
 (151,3,5,6,11),
 (152,3,5,7,11),
 (153,3,5,8,11),
 (154,3,5,9,11),
 (155,3,5,10,11),
 (156,3,6,3,11),
 (157,3,6,4,11),
 (158,3,6,5,11),
 (159,3,6,6,11),
 (160,3,6,7,11),
 (161,3,6,8,11),
 (162,1,1,1,13),
 (163,1,1,2,13),
 (164,1,1,3,13),
 (165,1,1,4,13),
 (166,1,1,5,13),
 (167,1,1,6,13),
 (168,1,1,7,13),
 (169,1,1,8,13),
 (170,1,1,9,13),
 (171,1,1,10,13),
 (172,1,2,4,13),
 (173,1,2,5,13),
 (174,1,2,6,13),
 (175,1,2,7,13),
 (176,1,2,8,13),
 (177,1,2,9,13),
 (178,1,2,10,13),
 (179,1,3,8,13),
 (180,1,3,9,13),
 (181,1,3,10,13),
 (182,1,4,1,13),
 (183,1,4,2,13),
 (184,1,4,6,13),
 (185,1,4,7,13),
 (186,1,4,8,13),
 (187,1,4,9,13),
 (188,1,4,10,13),
 (189,1,9,1,13),
 (190,1,9,2,13),
 (191,1,9,3,13),
 (192,1,9,7,13),
 (193,1,9,8,13),
 (194,1,9,9,13),
 (195,1,9,10,13),
 (196,1,10,1,13),
 (197,1,10,2,13),
 (198,1,10,7,13),
 (199,1,10,8,13),
 (200,1,10,9,13),
 (201,1,10,10,13),
 (202,1,11,1,13),
 (203,1,11,2,13),
 (204,1,11,3,13),
 (205,1,11,4,13),
 (206,1,11,5,13),
 (207,1,11,6,13),
 (208,1,11,7,13),
 (209,1,11,8,13),
 (210,1,11,9,13),
 (211,1,11,10,13),
 (212,1,12,1,13),
 (213,1,12,2,13),
 (214,1,12,3,13),
 (215,1,12,4,13),
 (216,1,12,5,13),
 (217,1,12,6,13),
 (218,1,12,7,13),
 (219,1,12,8,13),
 (220,1,12,9,13),
 (221,1,12,10,13),
 (222,1,13,1,13),
 (223,1,13,2,13),
 (224,1,13,3,13),
 (225,1,13,4,13),
 (226,1,13,5,13),
 (227,1,13,6,13),
 (228,1,13,7,13),
 (229,1,13,8,13),
 (230,1,13,9,13),
 (231,1,13,10,13),
 (232,1,14,1,13),
 (233,1,14,2,13),
 (234,1,14,3,13),
 (235,1,14,4,13),
 (236,1,14,5,13),
 (237,1,14,6,13),
 (238,1,14,7,13),
 (239,1,14,8,13),
 (240,1,14,9,13),
 (241,1,14,10,13),
 (242,1,15,1,13),
 (243,1,15,2,13),
 (244,1,15,3,13),
 (245,1,15,4,13),
 (246,1,15,5,13),
 (247,1,15,6,13),
 (248,1,15,7,13),
 (249,1,15,8,13),
 (250,1,15,9,13),
 (251,1,15,10,13),
 (252,2,1,1,1),
 (253,2,1,2,1),
 (254,2,1,3,1),
 (255,2,1,4,1),
 (256,2,1,5,1),
 (257,2,1,6,1),
 (258,2,1,7,1),
 (259,2,1,8,1),
 (260,2,1,9,1);
/*!40000 ALTER TABLE `Bestelregel` ENABLE KEYS */;


--
-- Definition of table `Klant`
--

DROP TABLE IF EXISTS `Klant`;
CREATE TABLE `Klant` (
  `id` smallint(6) unsigned NOT NULL auto_increment,
  `naam` varchar(35) NOT NULL,
  `telefoon` varchar(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `UC_u1` (`naam`,`telefoon`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Klant`
--

/*!40000 ALTER TABLE `Klant` DISABLE KEYS */;
INSERT INTO `Klant` (`id`,`naam`,`telefoon`) VALUES 
 (13,'CMS','020-5733111'),
 (4,'Jansen','06-22230487'),
 (10,'Jansen','06-22240487'),
 (3,'Klas2d','015-6772391'),
 (11,'Mentink','020-6802136'),
 (14,'Onbekend','020-5733111'),
 (2,'Pootjes','020-5644387'),
 (7,'Rijn','0515-701732'),
 (1,'Sint','030-7492885'),
 (12,'ter Horst','045-5672317'),
 (5,'van der Gaag','020-6823422'),
 (6,'van Rijn','06-73871261'),
 (8,'Veen','06-22484328'),
 (9,'Witsiers','030-4559871');
/*!40000 ALTER TABLE `Klant` ENABLE KEYS */;


--
-- Definition of table `Voorstelling`
--

DROP TABLE IF EXISTS `Voorstelling`;
CREATE TABLE `Voorstelling` (
  `id` smallint(6) unsigned NOT NULL auto_increment,
  `datum` date NOT NULL,
  `naam` varchar(50) NOT NULL,
  `uitverkocht` smallint(1) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `UC1` (`datum`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Voorstelling`
--

/*!40000 ALTER TABLE `Voorstelling` DISABLE KEYS */;
INSERT INTO `Voorstelling` (`id`,`datum`,`naam`,`uitverkocht`) VALUES 
 (1,'2016-05-15','Het temmen van de feeks',0),
 (2,'2016-06-12','Carmen',0),
 (3,'2016-08-19','Zomergasten speelt Rinoceritus',0),
 (4,'2016-10-10','Die Soldaten',0),
 (5,'2016-11-01','De A van Abeltje',0);
/*!40000 ALTER TABLE `Voorstelling` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
