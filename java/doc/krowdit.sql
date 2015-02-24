# ************************************************************
# Sequel Pro SQL dump
# Version 3327
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.13)
# Database: krowdit
# Generation Time: 2011-07-12 11:28:30 +0800
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Table_Achievement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Achievement`;

CREATE TABLE `Table_Achievement` (
  `AchievementID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AchievementName` varchar(255) DEFAULT '0',
  `AchievementDetail` varchar(255) DEFAULT '0',
  `CreatorID` bigint(20) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`AchievementID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Administrator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Administrator`;

CREATE TABLE `Table_Administrator` (
  `AdministratorID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) DEFAULT '0',
  `Pwd` varchar(255) DEFAULT '0',
  `Email` varchar(255) DEFAULT '0',
  `RoleID` bigint(20) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  `LastLogin` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`AdministratorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Album
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Album`;

CREATE TABLE `Table_Album` (
  `AlbumID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UID` bigint(20) DEFAULT '0',
  `URL` varchar(255) DEFAULT '0',
  PRIMARY KEY (`AlbumID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_Album` WRITE;
/*!40000 ALTER TABLE `Table_Album` DISABLE KEYS */;

INSERT INTO `Table_Album` (`AlbumID`, `UID`, `URL`)
VALUES
	(1,1,'Asenal.png'),
	(2,1,'Barcelona.png'),
	(3,1,'Bayern.png');

/*!40000 ALTER TABLE `Table_Album` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_BeFans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_BeFans`;

CREATE TABLE `Table_BeFans` (
  `BeFansID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UID` bigint(20) DEFAULT '0',
  `FansID` bigint(20) DEFAULT '0',
  `BeFansTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`BeFansID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Comment`;

CREATE TABLE `Table_Comment` (
  `CommentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PostID` bigint(20) DEFAULT '0',
  `Content` varchar(255) DEFAULT '0',
  `CreatorID` bigint(20) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`CommentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Deal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Deal`;

CREATE TABLE `Table_Deal` (
  `DealID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DealName` varchar(255) DEFAULT '0',
  `Content` varchar(255) DEFAULT '0',
  `ShopID` bigint(20) DEFAULT '0',
  `StartTime` datetime DEFAULT '0000-00-00 00:00:00',
  `StopTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`DealID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_JoinKrowd
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_JoinKrowd`;

CREATE TABLE `Table_JoinKrowd` (
  `JoinID` bigint(20) NOT NULL AUTO_INCREMENT,
  `KrowdID` bigint(20) DEFAULT '0',
  `UID` bigint(20) DEFAULT '0',
  `JoinTime` datetime DEFAULT '0000-00-00 00:00:00',
  `SupportingTeamId` bigint(20) DEFAULT '0',
  PRIMARY KEY (`JoinID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_JoinKrowd` WRITE;
/*!40000 ALTER TABLE `Table_JoinKrowd` DISABLE KEYS */;

INSERT INTO `Table_JoinKrowd` (`JoinID`, `KrowdID`, `UID`, `JoinTime`, `SupportingTeamId`)
VALUES
	(1,1,1,'2011-06-22 17:56:32',2),
	(2,2,2,'2011-06-24 15:48:56',2),
	(3,1,3,'2011-06-24 15:48:56',2),
	(7,1,4,'2011-06-24 15:48:56',1);

/*!40000 ALTER TABLE `Table_JoinKrowd` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_Krowd
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Krowd`;

CREATE TABLE `Table_Krowd` (
  `KrowdID` bigint(20) NOT NULL AUTO_INCREMENT,
  `KrowdName` varchar(255) DEFAULT '0',
  `KrowdTypeID` bigint(20) DEFAULT '0',
  `Home` varchar(255) DEFAULT '0',
  `Away` varchar(255) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  `LocationID` bigint(20) DEFAULT '0',
  `CreatorID` bigint(20) DEFAULT '0',
  `StartTime` datetime DEFAULT '0000-00-00 00:00:00',
  `ReachEmptyTime` datetime DEFAULT '0000-00-00 00:00:00',
  `CloseTime` datetime DEFAULT '0000-00-00 00:00:00',
  `LastScanTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`KrowdID`),
  KEY `StartTime` (`StartTime`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_Krowd` WRITE;
/*!40000 ALTER TABLE `Table_Krowd` DISABLE KEYS */;

INSERT INTO `Table_Krowd` (`KrowdID`, `KrowdName`, `KrowdTypeID`, `Home`, `Away`, `CreateTime`, `LocationID`, `CreatorID`, `StartTime`, `ReachEmptyTime`, `CloseTime`, `LastScanTime`)
VALUES
	(1,'WorldCup',1,'1','2','2011-06-04 19:00:00',1,1,'2011-07-12 15:00:00','2011-12-31 12:00:00','2011-12-31 12:00:00','2011-07-12 11:27:19'),
	(2,'Olympic',1,'3','2','2011-06-04 19:00:00',2,1,'2011-07-12 14:00:00','2011-12-31 12:00:00','2011-12-31 12:00:00','2011-07-12 11:27:19');

/*!40000 ALTER TABLE `Table_Krowd` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_KrowdType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_KrowdType`;

CREATE TABLE `Table_KrowdType` (
  `KrowdTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `KrowdTypeName` varchar(255) DEFAULT '0',
  `Public` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`KrowdTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_KrowdType` WRITE;
/*!40000 ALTER TABLE `Table_KrowdType` DISABLE KEYS */;

INSERT INTO `Table_KrowdType` (`KrowdTypeID`, `KrowdTypeName`, `Public`)
VALUES
	(1,'NFL',0),
	(2,'NBA',1),
	(3,'MLB',1);

/*!40000 ALTER TABLE `Table_KrowdType` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_League
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_League`;

CREATE TABLE `Table_League` (
  `TeamID` bigint(20) NOT NULL AUTO_INCREMENT,
  `KrowdTypeID` bigint(20) DEFAULT '0',
  `TeamName` varchar(255) DEFAULT '0',
  `Logo` varchar(255) DEFAULT '0',
  `Color1` bigint(20) DEFAULT '0',
  `Color2` bigint(20) DEFAULT '0',
  `Color3` bigint(20) DEFAULT '0',
  `HomeLocationID` bigint(20) DEFAULT '0',
  PRIMARY KEY (`TeamID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_League` WRITE;
/*!40000 ALTER TABLE `Table_League` DISABLE KEYS */;

INSERT INTO `Table_League` (`TeamID`, `KrowdTypeID`, `TeamName`, `Logo`, `Color1`, `Color2`, `Color3`, `HomeLocationID`)
VALUES
	(1,1,'Barcelona','Barcelona.png',0,0,0,1),
	(2,1,'Arsenal','Arsenal.png',0,0,0,2),
	(3,1,'Bayern','Bayern.png',0,0,0,1),
	(4,2,'Lakers','',0,0,0,0),
	(5,2,'Rocket','',0,0,0,0);

/*!40000 ALTER TABLE `Table_League` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_Location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Location`;

CREATE TABLE `Table_Location` (
  `LocationID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LocationName` varchar(255) DEFAULT '0',
  `LocationTypeID` bigint(20) DEFAULT '0',
  `latitude` float DEFAULT '0',
  `longitude` float DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  `CreatorID` bigint(20) DEFAULT '0',
  PRIMARY KEY (`LocationID`),
  KEY `coordinates` (`latitude`,`longitude`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_Location` WRITE;
/*!40000 ALTER TABLE `Table_Location` DISABLE KEYS */;

INSERT INTO `Table_Location` (`LocationID`, `LocationName`, `LocationTypeID`, `latitude`, `longitude`, `CreateTime`, `CreatorID`)
VALUES
	(1,'San Siro Stadium',1,10,10,'2011-06-24 15:45:49',1),
	(2,'Tianjin',1,9.95,10.02,'2011-06-24 15:45:49',1),
	(3,'Beijing',1,9.98,10.03,'2011-06-24 15:45:49',1),
	(4,'Shanghai',1,8,8,'2011-06-24 15:45:49',1);

/*!40000 ALTER TABLE `Table_Location` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_LocationType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_LocationType`;

CREATE TABLE `Table_LocationType` (
  `LocationTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LocationTypeName` varchar(255) DEFAULT '0',
  PRIMARY KEY (`LocationTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Post`;

CREATE TABLE `Table_Post` (
  `PostID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatorID` bigint(20) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  `Positive` int(11) DEFAULT '0',
  `negative` int(11) DEFAULT '0',
  `Content` varchar(255) DEFAULT '0',
  `Pic` bigint(20) DEFAULT '0',
  `KrowdID` bigint(20) DEFAULT '0',
  `CallAnAction` varchar(255) DEFAULT '0',
  PRIMARY KEY (`PostID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Profile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Profile`;

CREATE TABLE `Table_Profile` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PicID` bigint(20) DEFAULT '0',
  `LastLogin` datetime DEFAULT '0000-00-00 00:00:00',
  `TotalKrowdsJoined` int(11) DEFAULT '0',
  `KrowdsOf25Members` int(11) DEFAULT '0',
  `NumberOfTextPosts` int(11) DEFAULT '0',
  `NumberOfPicPosts` int(11) DEFAULT '0',
  `LargestKrowdID` bigint(20) DEFAULT '0',
  `FavoriteKrowdTypeID` bigint(20) DEFAULT '0',
  `FavoriteLocationID` bigint(20) DEFAULT '0',
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_Role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_Role`;

CREATE TABLE `Table_Role` (
  `RoleID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(255) DEFAULT '0',
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_User`;

CREATE TABLE `Table_User` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) DEFAULT '0',
  `Pwd` varchar(255) DEFAULT '0',
  `Email` varchar(255) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  `UTypeID` bigint(20) DEFAULT '0',
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

LOCK TABLES `Table_User` WRITE;
/*!40000 ALTER TABLE `Table_User` DISABLE KEYS */;

INSERT INTO `Table_User` (`UID`, `UserName`, `Pwd`, `Email`, `CreateTime`, `UTypeID`)
VALUES
	(1,'KrowditStaff','123','no-reply@tadosoft.com','2011-06-22 14:59:25',1),
	(2,'tom','','','2011-06-24 15:45:49',2),
	(5,'ryan','123','ryan@tadosoft.com','2011-06-27 11:13:04',2),
	(15,'cindy','123','cindy@tadosoft.com','2011-06-27 11:13:04',1),
	(25,'fj','123','fujing@tadosoft.com','2011-06-27 11:13:04',1),
	(35,'jacky','123','jacky@tadosoft.com','2011-06-27 11:13:04',1);

/*!40000 ALTER TABLE `Table_User` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Table_UserAchievement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_UserAchievement`;

CREATE TABLE `Table_UserAchievement` (
  `UAID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AchievementID` bigint(20) DEFAULT '0',
  `UID` bigint(20) DEFAULT '0',
  `CreateTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`UAID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table Table_UType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Table_UType`;

CREATE TABLE `Table_UType` (
  `UTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UTypeName` varchar(255) DEFAULT '0',
  PRIMARY KEY (`UTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
