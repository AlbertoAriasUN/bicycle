/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50172
Source Host           : localhost:3306
Source Database       : proj2

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2014-06-07 16:49:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` int(1) NOT NULL AUTO_INCREMENT,
  `time` int(5) NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
