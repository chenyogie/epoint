/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : medicalmanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-01-23 17:01:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for doctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `doctorinfo`;
CREATE TABLE `doctorinfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` int(11) NOT NULL,
  `birth` date NOT NULL,
  `docnative` varchar(100) NOT NULL,
  `politics` int(11) NOT NULL,
  `departments` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `count` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `others` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctorinfo
-- ----------------------------
INSERT INTO `doctorinfo` VALUES ('DC2018010001', 'qwe', '0', '2018-01-25', '北京-东城区', '0', 'qweqwe', 'qweqwe', 'qweqwe', '112', '0', 'qweqwe');
INSERT INTO `doctorinfo` VALUES ('DC2018010002', 'qweqweasd', '0', '2018-01-26', '山西-太原', '1', 'qweqwe', 'qweqwe', '123123123', '12', '2', 'qweqwe');

-- ----------------------------
-- Table structure for patientinfo
-- ----------------------------
DROP TABLE IF EXISTS `patientinfo`;
CREATE TABLE `patientinfo` (
  `medicalnumber` varchar(20) NOT NULL,
  `doctorid` varchar(20) NOT NULL,
  `patientname` varchar(50) NOT NULL,
  `departments` varchar(50) NOT NULL,
  `sex` int(11) NOT NULL,
  `identity` varchar(50) NOT NULL,
  `comeindate` date NOT NULL,
  `totalcost` double(18,2) NOT NULL,
  `remark` text,
  PRIMARY KEY (`medicalnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patientinfo
-- ----------------------------
