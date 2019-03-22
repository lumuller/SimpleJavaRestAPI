-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: videolocadora
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_movies`
--

DROP TABLE IF EXISTS `tb_movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_movies` (
  `id` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `director` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_movies`
--

LOCK TABLES `tb_movies` WRITE;
/*!40000 ALTER TABLE `tb_movies` DISABLE KEYS */;
INSERT INTO `tb_movies` VALUES (11,_binary '','Director','Movie One'),(12,_binary '\0','Director','Movie Two'),(13,_binary '\0','Director','Movie Three'),(14,_binary '','Director','Movie Four'),(15,_binary '\0','Director','Movie Five'),(16,_binary '','Director','Movie Six'),(17,_binary '','Director','Movie Seven'),(18,_binary '','Director','Movie Eight'),(19,_binary '','Director','Movie Nine'),(20,_binary '','Director','Movie Ten');
/*!40000 ALTER TABLE `tb_movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rents`
--

DROP TABLE IF EXISTS `tb_rents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_rents` (
  `id` int(11) NOT NULL,
  `rental_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `fk_tb_movies` int(11) NOT NULL,
  `fk_tb_users` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhd6k1mpe5yq12o58pi7go7v61` (`fk_tb_movies`),
  KEY `FKoi3mubmff6mdfbs2jgsi79g9m` (`fk_tb_users`),
  CONSTRAINT `FKhd6k1mpe5yq12o58pi7go7v61` FOREIGN KEY (`fk_tb_movies`) REFERENCES `tb_movies` (`id`),
  CONSTRAINT `FKoi3mubmff6mdfbs2jgsi79g9m` FOREIGN KEY (`fk_tb_users`) REFERENCES `tb_users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rents`
--

LOCK TABLES `tb_rents` WRITE;
/*!40000 ALTER TABLE `tb_rents` DISABLE KEYS */;
INSERT INTO `tb_rents` VALUES (152,'2019-03-21 23:00:50','2019-03-21 23:02:32',11,'luana@gmail.com'),(153,'2019-03-21 23:01:44',NULL,12,'maria@gmail.com'),(154,'2019-03-21 23:01:51',NULL,13,'joao@gmail.com'),(155,'2019-03-21 23:02:46','2019-03-21 23:03:05',16,'luana@gmail.com'),(156,'2019-03-21 23:02:55',NULL,15,'luana@gmail.com');
/*!40000 ALTER TABLE `tb_rents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_users` (
  `username` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES ('joao@gmail.com','Joao','123456','USER'),('luana@gmail.com','Luana','123456','ADMIN'),('maria@gmail.com','Maria','123456','USER'),('marta@gmail.com','Marta','123456','USER');
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'videolocadora'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-21 20:06:05
