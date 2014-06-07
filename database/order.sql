/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50172
Source Host           : localhost:3306
Source Database       : proj2

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2014-06-07 16:50:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `orderId` int(1) NOT NULL AUTO_INCREMENT,
  `orderState` int(1) NOT NULL,
  `driverId` int(1) DEFAULT NULL,
  `psgId` int(1) NOT NULL,
  `time` int(5) NOT NULL,
  `orgin` int(4) NOT NULL,
  `destination` int(4) NOT NULL,
  `commentId` int(1) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `driverId` (`driverId`),
  KEY `psgId` (`psgId`),
  KEY `CommentId` (`commentId`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`driverId`) REFERENCES `person` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`psgId`) REFERENCES `person` (`id`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`commentId`) REFERENCES `comment` (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
