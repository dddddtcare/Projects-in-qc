CREATE DATABASE  IF NOT EXISTS `database` ;
USE `database`;


CREATE TABLE IF NOT EXISTS `sr` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_tittle` varchar(200) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `time_posted` varchar(200) DEFAULT NULL,
  `ad_type` varchar(200) DEFAULT NULL,
  `ad_url` varchar(200) DEFAULT NULL,
  `Saved` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'N',
  PRIMARY KEY (`ad_id`),
  UNIQUE KEY `ad_id_UNIQUE` (`ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;


