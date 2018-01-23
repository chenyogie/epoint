/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : petmanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-01-23 17:04:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hostinfo
-- ----------------------------
DROP TABLE IF EXISTS `hostinfo`;
CREATE TABLE `hostinfo` (
  `vipno` varchar(50) NOT NULL COMMENT '会员编号',
  `petid` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sex` int(11) NOT NULL,
  `birth` date NOT NULL,
  `identify` varchar(50) NOT NULL COMMENT '省份证号',
  `address` varchar(50) NOT NULL,
  `telphone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`vipno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hostinfo
-- ----------------------------
INSERT INTO `hostinfo` VALUES ('69ff9000-797f-45d0-b948-e6bf8ce63b09', 'Pet2018010002', '测试2', '0', '2018-01-22', '123123', '123123', '123123', '123123', '123123');
INSERT INTO `hostinfo` VALUES ('d59370c1-0976-4515-bad4-f22684625cd7', 'Pet2018010001', '测试1', '0', '2018-01-22', '123123123', '123123123', '123123123', '123123123', '123123');

-- ----------------------------
-- Table structure for petinfo
-- ----------------------------
DROP TABLE IF EXISTS `petinfo`;
CREATE TABLE `petinfo` (
  `id` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL DEFAULT '',
  `sex` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `weight` double NOT NULL,
  `isvaccinated` int(11) NOT NULL COMMENT '是否接种疫苗',
  `photo` varchar(200) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petinfo
-- ----------------------------
INSERT INTO `petinfo` VALUES ('Pet2018010001', '宠物1', '1', '12', '0', '23', '0', 'files\\upload\\1.png', null);
INSERT INTO `petinfo` VALUES ('Pet2018010002', '宠物2', '0', '12', '1', '23', '1', 'files\\upload\\5.png', null);
