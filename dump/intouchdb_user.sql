-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: intouchdb
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `user_image` varchar(400) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `registration_date` datetime NOT NULL,
  `last_visit` datetime NOT NULL,
  `token` varchar(45) NOT NULL,
  `device_id` varchar(200) NOT NULL,
  `application_id` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `skype` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Vlad','Zavadski',NULL,5,'vlad','123','2016-03-24 20:42:17','2016-05-01 18:21:23','token','',NULL,NULL,NULL,NULL),('vika','kovalenok',NULL,6,'vika_viktoria','123321','2016-04-14 21:07:52','2016-04-14 21:07:52','f253e18f-81a7-4a83-927a-c6e860f17dc3','',NULL,NULL,NULL,NULL),('asd','asd',NULL,7,'LOGIN','123123','2016-04-18 19:14:12','2016-05-01 17:54:05','639dd38f-3a43-4473-96d5-9d787fe1bf00','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,8,'jhgjg0.05385386440960138','123123','2016-04-18 19:14:12','2016-04-18 19:14:12','a95e71d8-2d08-4f59-871b-847567c32c1c','assdfsdsdd','fsdfs',NULL,NULL,NULL),('Vladislav','Zavadski',NULL,9,'vlad999123','1461190644115','2016-04-21 13:59:26','2016-04-21 13:59:26','81148680-4fcb-47cf-bb35-a754c61a0019','hahahaha','feruhgeiu',NULL,NULL,NULL),('Vladislav','Zavadski',NULL,10,'vlad99912123','1461190644115','2016-04-21 14:00:21','2016-04-21 14:00:21','d0afab7a-c47c-417d-a49c-5b61f62f86a7','hahahaha','feruhgeiu',NULL,NULL,NULL),('Vladislav','Zavadski',NULL,11,'vladzxa','1461190644115','2016-04-21 14:00:36','2016-04-21 14:00:36','87e84471-ad24-4d11-8f67-b0a66757dd0e','hahahaha','feruhgeiu',NULL,NULL,NULL),('Vladislav','Zavadski',NULL,12,'vlad22zxa','1461190644115','2016-04-21 14:02:09','2016-04-21 15:11:25','94fcc8c9-c0e9-4dd7-b7f9-3da7c07a6740','hahahaha','feruhgeiu',NULL,NULL,NULL),('asd','asd',NULL,13,'jhgjg0.4748773277440578','123123','2016-04-24 19:03:22','2016-04-24 19:03:22','ec2566b9-65fd-4184-939f-7ec5a84f561e','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,14,'jhgjg0.08695735457558018','123123','2016-04-24 19:15:02','2016-04-24 19:15:02','bc58024e-b891-4fbc-a884-2cb4e5d072b3','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,15,'jhgjg0.2909759436457736','123123','2016-04-24 19:16:49','2016-04-24 19:16:49','33b54db9-c21a-42c1-a645-7c2e901adf84','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,16,'jhgjg0.7092894889624101','123123','2016-04-24 19:18:25','2016-04-24 19:18:25','6e39773e-69f2-4577-845d-af770fd316a1','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,17,'jhgjg0.5944477601973075','123123','2016-04-24 19:19:53','2016-04-24 19:19:53','52875649-6f95-42eb-81a3-e66ad4522bd6','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,18,'jhgjg0.9296385579802963','123123','2016-04-24 19:56:08','2016-04-24 19:56:08','c1ad411c-c6e2-4f14-9631-a5382e7e9bc5','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,19,'jhgjg0.07178502574796297','123123','2016-04-25 00:00:34','2016-04-25 00:00:34','fdd75d64-b094-4164-aefd-581a9efb93ca','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,20,'jhgjg0.05424413631808578','123123','2016-05-01 17:50:13','2016-05-01 17:50:13','a9ca8500-618e-4772-8d62-0eb2ae4eb0fd','assdfsdsdd','fsdfs',NULL,NULL,NULL),('asd','asd',NULL,21,'jhgjg0.40316171588625294','123123','2016-05-01 17:54:05','2016-05-01 17:54:05','a01196da-d492-4d2d-9a8a-2801659a0e14','assdfsdsdd','fsdfs',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-01 18:44:08
