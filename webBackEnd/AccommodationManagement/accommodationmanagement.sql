/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : accommodationmanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-01-23 17:05:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accommodationinfo
-- ----------------------------
DROP TABLE IF EXISTS `accommodationinfo`;
CREATE TABLE `accommodationinfo` (
  `id` varchar(50) NOT NULL COMMENT '宿舍编号',
  `name` varchar(50) NOT NULL COMMENT '学生姓名',
  `dormitory` int(11) NOT NULL COMMENT '宿舍区',
  `buildingNo` int(11) NOT NULL COMMENT '栋号',
  `roomNo` int(11) NOT NULL COMMENT '寝室号',
  `arrivalDate` datetime NOT NULL COMMENT '入住时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accommodationinfo
-- ----------------------------

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
  `stuId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sex` int(11) NOT NULL COMMENT '0：男；1：女',
  `age` int(11) NOT NULL,
  `native` varchar(100) NOT NULL COMMENT '籍贯',
  `phone` varchar(50) NOT NULL,
  `roomInfo` varchar(100) NOT NULL COMMENT '宿舍信息',
  `detail` varchar(100) DEFAULT NULL COMMENT '其他信息',
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('XH2017120001', '张三_0001', '0', '20', '四川成都', '18113739827', '未办理住宿', null);
INSERT INTO `studentinfo` VALUES ('XH2017120002', '张三_0002', '1', '18', '江苏南京', '18112340987', '未办理住宿', null);
INSERT INTO `studentinfo` VALUES ('XH2018010003', '李四', '0', '18', '江苏省苏州市', '13600008888', '未办理住宿', 'qwe');
INSERT INTO `studentinfo` VALUES ('XH2018010004', '李四_0004', '0', '23', '四川省广元市', '15600001111', '未办理住宿', '123');
INSERT INTO `studentinfo` VALUES ('XH2018010005', '王五', '0', '18', '江苏省南京市', '13800002222', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010006', '王五_0006', '0', '53', '江苏省无锡市', '18122229999', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010007', '王五_0007', '0', '18', '四川省成都市', '19800002222', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010008', 'wangwu', '0', '18', '四川省绵阳市', '11122223333', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010009', 'wangwu_0009', '1', '25', '四川省绵阳市', '12344445555', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010010', '绝地求生', '0', '18', '四川省广元市', '00011112222', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010011', '绝地求生_0011', '0', '18', '四川省广元市', '11100009999', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010012', '绝地求生_0012', '0', '18', '四川省成都市', '12345678901', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010013', '绝地求生_0013', '0', '18', '四川省成都市', '12345678901', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010014', '绝地球生', '0', '18', '四川省广元市', '12345678901', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010015', '9527', '0', '18', '江苏省南京市', '12345678901', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010016', '9527_0016', '0', '18', '四川省成都市', '09876543211', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010017', '9527_0017', '0', '18', '江苏省南京市', '12345678900', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010018', '9527_0018', '0', '18', '江苏省苏州市', '11111111111', '未办理住宿', '');
INSERT INTO `studentinfo` VALUES ('XH2018010019', '英雄联盟', '1', '23', '四川省广元市', '22222222222', '未办理住宿', '');
