-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: tsdms
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `Ano` char(20) NOT NULL,
  `Apassword` char(20) NOT NULL,
  `Atel` char(15) NOT NULL,
  `Aname` char(20) DEFAULT NULL,
  `Asex` enum('男','女') DEFAULT '男',
  PRIMARY KEY (`Ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('1507064124','qwe123','12312312312','张三','男'),('1507064138','qwe123','12312312312','赵四','男');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `Cno` char(20) NOT NULL,
  `Cnumber` int(11) NOT NULL,
  `Cmonitor` char(20) NOT NULL,
  PRIMARY KEY (`Cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('15070641',43,'张生'),('15070642',40,'刘文');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distribution`
--

DROP TABLE IF EXISTS `distribution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distribution` (
  `Ano` char(20) NOT NULL,
  `Bno` char(20) NOT NULL,
  `Bname` char(20) DEFAULT NULL,
  `Bnum` int(11) DEFAULT NULL,
  `Lname` char(20) DEFAULT NULL,
  `Ddate` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribution`
--

LOCK TABLES `distribution` WRITE;
/*!40000 ALTER TABLE `distribution` DISABLE KEYS */;
INSERT INTO `distribution` VALUES ('1507064138','9787040406641','数据库系统概论',50,'李四','2018/01/18');
/*!40000 ALTER TABLE `distribution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `press`
--

DROP TABLE IF EXISTS `press`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `press` (
  `Pno` char(15) NOT NULL,
  `Pname` char(20) DEFAULT NULL,
  `Ptel` char(15) DEFAULT NULL,
  PRIMARY KEY (`Pno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `press`
--

LOCK TABLES `press` WRITE;
/*!40000 ALTER TABLE `press` DISABLE KEYS */;
INSERT INTO `press` VALUES ('001','人民出版社','010-65251359'),('010','高等教育出版社','010-58581118'),('012','人民教育出版社','010-58758866'),('019','人民文学出版社','010-65252965'),('033','机械工业出版社','010-88361066'),('045','人民邮电出版社','010-81055055'),('235','清华大学出版社','010-62781733'),('290','电子工业出版社','010-88254114');
/*!40000 ALTER TABLE `press` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `Tno` char(20) NOT NULL,
  `Bno` char(20) NOT NULL,
  `Snum` int(11) DEFAULT NULL,
  `Sdo` enum('Y','N') DEFAULT 'N',
  `Sdate` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES ('1507064134','9787040406641',50,'N','2018年春季学期'),('1507064134','9787302351078',50,'Y','2018年春季学期');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `Tno` char(20) NOT NULL,
  `Tpassword` char(20) NOT NULL,
  `Ttel` char(15) NOT NULL,
  `Tname` char(20) DEFAULT NULL,
  `Tsex` enum('男','女') DEFAULT '男',
  `Cno` char(20) DEFAULT NULL,
  PRIMARY KEY (`Tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('1507064134','qwe123','12312312312','方一','男',NULL),('1507064136','qwe123','12312312312',NULL,NULL,NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching`
--

DROP TABLE IF EXISTS `teaching`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching` (
  `Tno` char(20) NOT NULL,
  `Cno` char(20) NOT NULL,
  `course` char(20) DEFAULT NULL,
  PRIMARY KEY (`Tno`,`Cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching`
--

LOCK TABLES `teaching` WRITE;
/*!40000 ALTER TABLE `teaching` DISABLE KEYS */;
INSERT INTO `teaching` VALUES ('1507064134','15070641','数据库'),('1507064134','15070642','数据库');
/*!40000 ALTER TABLE `teaching` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `textbook`
--

DROP TABLE IF EXISTS `textbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `textbook` (
  `Bno` char(20) NOT NULL,
  `Bname` char(20) NOT NULL,
  `Bversion` char(20) DEFAULT NULL,
  `Bprice` float DEFAULT NULL,
  `Bnum` int(11) DEFAULT NULL,
  `Pno` char(15) DEFAULT NULL,
  PRIMARY KEY (`Bno`),
  KEY `Pno` (`Pno`),
  CONSTRAINT `textbook_ibfk_1` FOREIGN KEY (`Pno`) REFERENCES `press` (`Pno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textbook`
--

LOCK TABLES `textbook` WRITE;
/*!40000 ALTER TABLE `textbook` DISABLE KEYS */;
INSERT INTO `textbook` VALUES ('9787040406641','数据库系统概论','5',39.6,1200,'010'),('9787111089681','数值计算方法','2',25,1000,'033'),('9787302351078','计算机科学导论','4',39,800,'235');
/*!40000 ALTER TABLE `textbook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 12:04:09
