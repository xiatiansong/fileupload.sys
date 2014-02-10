/*
SQLyog Ultimate v10.42 
MySQL - 5.5.34-0ubuntu0.12.04.1-log : Database - zqfus
*********************************************************************
*/

CREATE database zqfus;

drop table if exists T_USER_FILE;

CREATE TABLE `T_USER_FILE` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` bigint(11) DEFAULT NULL COMMENT '帐号',
  `OPERATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `IP_ADDRESS` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `FILE_NAME` varchar(128) DEFAULT NULL COMMENT '文件名',
  `FILE_SIZE` varchar(32) DEFAULT NULL COMMENT '文件大小(M)',
  `FILE_PATH` varchar(256) DEFAULT NULL COMMENT '文件路径',
  `UPLOAD_TYPE` int(1) DEFAULT NULL COMMENT '上传类别,1-内网上传,2-外网上传',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户上传文件';