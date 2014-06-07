/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50172
Source Host           : localhost:3306
Source Database       : proj2

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2014-06-07 16:50:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(1) NOT NULL,
  `userId` varchar(8) NOT NULL,
  `pwd` varchar(8) NOT NULL,
  `sex` int(1) NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pwd` (`pwd`),
  KEY `pwd_2` (`pwd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
