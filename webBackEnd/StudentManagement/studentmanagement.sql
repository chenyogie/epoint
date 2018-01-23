/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : studentmanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-01-23 17:02:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
  `stuId` varchar(50) NOT NULL,
  `stuName` varchar(50) NOT NULL,
  `profession` int(11) NOT NULL,
  `addDate` datetime NOT NULL,
  `sex` int(11) NOT NULL,
  `idNum` varchar(20) NOT NULL,
  `totalScore` int(11) NOT NULL,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('0faccbbd-e6ba-43a0-9e27-a41dd0db2580', '测试1', '1', '2018-01-23 00:00:00', '1', '510812199809021051', '8', '123');
INSERT INTO `studentinfo` VALUES ('bfef2433-a156-413b-a767-6012dfa22f4f', '测试3', '1', '2018-01-18 00:00:00', '0', '510812199409021051', '9', '123');

-- ----------------------------
-- Table structure for stumarkinfo
-- ----------------------------
DROP TABLE IF EXISTS `stumarkinfo`;
CREATE TABLE `stumarkinfo` (
  `markId` varchar(50) NOT NULL,
  `stuId` varchar(50) NOT NULL,
  `courseName` varchar(50) NOT NULL,
  `baseScore` double(18,2) NOT NULL,
  `testScore` double(18,2) NOT NULL,
  `finalScore` double(18,2) NOT NULL,
  `addDate` datetime NOT NULL,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`markId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stumarkinfo
-- ----------------------------
INSERT INTO `stumarkinfo` VALUES ('6c3b7f11-b39a-4ff6-aac9-383e34ab4025', '0faccbbd-e6ba-43a0-9e27-a41dd0db2580', '数学', '80.00', '80.00', '80.00', '2018-01-23 00:00:00', '123123');
INSERT INTO `stumarkinfo` VALUES ('d448f4c1-4dc3-46d4-b7d1-19a3b78639f3', 'bfef2433-a156-413b-a767-6012dfa22f4f', '数学', '98.00', '98.00', '98.00', '2018-01-19 00:00:00', '123123');
