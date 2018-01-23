/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : musicmanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-01-23 14:18:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for musicinfo
-- ----------------------------
DROP TABLE IF EXISTS `musicinfo`;
CREATE TABLE `musicinfo` (
  `selectnumber` varchar(50) NOT NULL,
  `musicid` varchar(50) NOT NULL,
  `singer` varchar(50) NOT NULL,
  `comeindate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `totalcost` int(11) NOT NULL,
  `sorder` int(11) NOT NULL,
  `isswitch` int(11) NOT NULL,
  `remark` text,
  PRIMARY KEY (`selectnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of musicinfo
-- ----------------------------
INSERT INTO `musicinfo` VALUES ('0bf1e927-7831-4de4-b44f-3445cb348774', 'song1', 'singer', '2018-01-23 14:12:00', '0', '4', '0', '');
INSERT INTO `musicinfo` VALUES ('362713ba-11ff-4923-8872-64ecb000bd65', 'song2', 'adsasd', '2018-01-23 14:11:04', '0', '2', '0', '');
INSERT INTO `musicinfo` VALUES ('3a61a574-d169-4e73-9a16-b9feb51a4662', 'song4', 'qwe', '2018-01-23 14:12:57', '0', '7', '0', '');
INSERT INTO `musicinfo` VALUES ('a0d4088f-93d5-4d0e-9ab2-9387f74884f7', 'song2', 'adsasd', '2018-01-23 14:12:04', '0', '5', '0', '');
INSERT INTO `musicinfo` VALUES ('afb47315-6ebe-49c0-a58c-801c5c67e686', 'song3', '123', '2018-01-23 14:12:07', '0', '6', '0', '');
INSERT INTO `musicinfo` VALUES ('b8890568-c4db-4e2f-aa4f-5e0a841f127b', 'song1', 'singer', '2018-01-23 14:11:01', '0', '1', '0', '');
INSERT INTO `musicinfo` VALUES ('d82ba5fe-fde0-4ff7-b41f-3b89e1fb4246', 'song3', '123', '2018-01-23 14:11:08', '0', '3', '0', '');

-- ----------------------------
-- Table structure for songinfo
-- ----------------------------
DROP TABLE IF EXISTS `songinfo`;
CREATE TABLE `songinfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` int(11) NOT NULL,
  `lyrics` varchar(50) NOT NULL,
  `compose` varchar(50) NOT NULL,
  `singer` varchar(50) NOT NULL,
  `degree` int(11) NOT NULL,
  `langues` varchar(50) NOT NULL,
  `duration` int(11) NOT NULL,
  `hotlevel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of songinfo
-- ----------------------------
INSERT INTO `songinfo` VALUES ('GQ2018010001', 'song1', '1', 'sname', 'nasing', 'singer', '2', '3', '0', '15');
INSERT INTO `songinfo` VALUES ('GQ2018010002', 'song2', '3', 'adad', 'ad', 'adsasd', '0', '1', '3', '15');
INSERT INTO `songinfo` VALUES ('GQ2018010003', 'song3', '1', '123', '123', '123', '0', '3', '2', '9');
INSERT INTO `songinfo` VALUES ('GQ2018010004', 'song4', '2', 'qwe', 'qwe', 'qwe', '1', '3', '3', '3');
