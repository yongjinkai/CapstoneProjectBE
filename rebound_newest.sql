-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rebound_db
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `nurse_id` bigint NOT NULL AUTO_INCREMENT,
  `expertise` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `licence_no` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`nurse_id`),
  UNIQUE KEY `UK_bl2q6g60tor4whxe60hlrbdx8` (`user_id`),
  CONSTRAINT `FKr5s9i2lqsfmloldv6tqbh3eg4` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (301,'Critical Care Nursing','Male','A7B2C9',21),(302,'Pediatric Nursing','Female','X3Y8Z1',22),(303,'Oncology Nursing','Male','M5N4P6',23),(304,'Emergency Nursing','Female','K2L7J9',24),(305,'Geriatric Nursing','Male','Q9R3S5',25),(312,'Obstetric Nursing','Female','Y7X2Z6',75);
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package` (
  `package_id` bigint NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`package_id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (200,'No Package',0),(201,'Half-day',300),(202,'Full-day',500);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patient_id` bigint NOT NULL AUTO_INCREMENT,
  `additional_notes` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `medical_prescriptions` varchar(255) DEFAULT NULL,
  `medical_records` varchar(255) DEFAULT NULL,
  `next_of_kin_name` varchar(255) DEFAULT NULL,
  `next_of_kin_phone` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `package_id` bigint DEFAULT NULL,
  `nurse_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `UK_6i3fp8wcdxk473941mbcvdao4` (`user_id`),
  KEY `FK486p46n02r1vank2mvhatkmcs` (`nurse_id`),
  KEY `FKryqik8oew5dm9dxw6ylb32jje` (`package_id`),
  CONSTRAINT `FK486p46n02r1vank2mvhatkmcs` FOREIGN KEY (`nurse_id`) REFERENCES `nurse` (`nurse_id`),
  CONSTRAINT `FKp6ttmfrxo2ejiunew4ov805uc` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKryqik8oew5dm9dxw6ylb32jje` FOREIGN KEY (`package_id`) REFERENCES `package` (`package_id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (101,'','2024-10-31','Lisinopril 10mg daily; Metformin 500mg twice daily','Hypertension; Diabetes Type 2','Chen Mei','93345678','2024-10-01',202,301,1),(102,'','2024-10-31','Albuterol inhaler as needed; Cetirizine 10mg daily','Asthma; Allergies','John Doe','23456789','2024-10-01',201,301,2),(103,'','2024-10-31','Sumatriptan 50mg as needed; Sertraline 50mg daily','Migraine; Anxiety','Aisha Patel','34567890','2024-10-01',202,302,3),(104,'','2024-09-30','Acetaminophen 500mg as needed; Atorvastatin 20mg daily','Osteoarthritis; High Cholesterol','Kim Sung-jin','45678901','2024-09-01',202,302,4),(105,'','2024-10-31','Fluoxetine 20mg daily; Zolpidem 5mg as needed','Depression; Insomnia','Emma Thompson','56789012','2024-10-01',202,303,5),(106,'','2024-10-31','Omeprazole 20mg daily; Ferrous sulfate 325mg daily','Gastric Ulcer; Anemia','Liu Wei','67890123','2024-10-01',202,303,6),(107,'','2024-10-31','Levothyroxine 100mcg daily; Alendronate 70mg weekly','Hypothyroidism; Osteoporosis','Maria Rodriguez','78901234','2024-10-01',202,304,7),(108,'','2024-10-31','Aspirin 81mg daily; Allopurinol 300mg daily','Coronary Artery Disease; Gout','Hiroshi Tanaka','89012345','2024-10-01',201,304,8),(109,'','2024-10-31','Methotrexate 15mg weekly; Pregabalin 75mg twice daily','Rheumatoid Arthritis; Fibromyalgia','Sarah Johnson','90123456','2024-10-01',201,305,9),(110,'','2024-10-31','Tiotropium inhaler daily; CPAP machine nightly','COPD; Sleep Apnea','Raj Gupta','1234567','2024-10-01',202,305,10);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('Admin','Patient','Nurse') NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'liwei@email.com','Li Wei','92345678','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(2,'sarahj@email.com','Sarah Johnson','23456789','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(3,'hiroshi.t@email.com','Hiroshi Tanaka','34567890','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(4,'emilyc@email.com','Emily Chen','45678901','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(5,'mohammed.af@email.com','Mohammed Al-Fayed','56789012','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(6,'priyap@email.com','Priya Patel','67890123','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(7,'johns@email.com','John Smith','78901234','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(8,'yuki.s@email.com','Yuki Sato','89012345','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(9,'mariag@email.com','Maria Garcia','90123456','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(10,'rajg@email.com','Raj Gupta','1234567','Patient','$2a$10$eKwmIp4v/Gdg8k0D8JKlouMBnXsDQ411rieMpgxDGny.7AHnBoh6W'),(21,'ethanw@gmail.com','Ethan Walker','7249816','Nurse','$2a$10$FuS5RWqNblKJitN6vZf6aO6Ano6tfwWuR2w8/mpYvWs65gGwHLTqO'),(22,'oliviab@gmail.com','Olivia Bennett','6132749','Nurse','$2a$10$FuS5RWqNblKJitN6vZf6aO6Ano6tfwWuR2w8/mpYvWs65gGwHLTqO'),(23,'jamesc@gmail.com','James Carter','8754321','Nurse','$2a$10$FuS5RWqNblKJitN6vZf6aO6Ano6tfwWuR2w8/mpYvWs65gGwHLTqO'),(24,'emmah@gmail.com','Emma Harrison','4926578','Nurse','$2a$10$FuS5RWqNblKJitN6vZf6aO6Ano6tfwWuR2w8/mpYvWs65gGwHLTqO'),(25,'samuelb@gmail.com','Samuel Brooks','7309452','Nurse','$2a$10$FuS5RWqNblKJitN6vZf6aO6Ano6tfwWuR2w8/mpYvWs65gGwHLTqO'),(74,'cheryl@gmail.com','Cheryl','75629588','Admin','$2a$10$t7UXYVILp2NohcVCUxlDSOxqw8YWJm7JjFqUp8u9Slcm44h7ulZuu'),(75,'charlottet@gmail.com','Charlotte Turner','67492079','Nurse','$2a$10$FuS5RWqNblKJitN6vZf6aO6Ano6tfwWuR2w8/mpYvWs65gGwHLTqO');
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

-- Dump completed on 2024-10-24  9:16:42
