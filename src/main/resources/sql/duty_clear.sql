/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.6.44 : Database - duty
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dianthus` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dianthus`;



CREATE TABLE `d_sys_dictionary` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级id',
  `dict_name` varchar(100) DEFAULT NULL COMMENT '字典名',
  `dict_value` varchar(100) DEFAULT NULL COMMENT '字典值',
  `dict_type` varchar(100) DEFAULT NULL COMMENT '字典类型',
  `dict_desc` varchar(255) DEFAULT NULL COMMENT '字典说明',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable_flag` int(11) DEFAULT '1' COMMENT '逻辑删除字段 0 已删除 1 正常使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_dictionary` */

/*Table structure for table `d_sys_log` */

DROP TABLE IF EXISTS `d_sys_log`;

CREATE TABLE `d_sys_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `operation` int(11) DEFAULT NULL COMMENT '操作 1 登陆 2 save 3 update 4 delete 5 select 6.',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `operation_desc` varchar(255) DEFAULT NULL COMMENT '操作说明',
  `operation_result` int(11) DEFAULT NULL COMMENT '操作结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable_flag` int(11) DEFAULT '1' COMMENT '逻辑删除字段 0 已删除 1 正常使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_log` */

/*Table structure for table `d_sys_menu` */

DROP TABLE IF EXISTS `d_sys_menu`;

CREATE TABLE `d_sys_menu` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `menu_desc` varchar(255) DEFAULT NULL COMMENT '菜单说明',
  `menu_remark` varchar(255) DEFAULT NULL COMMENT '菜单备注',
  `menu_order` int(11) DEFAULT NULL COMMENT '菜单排序',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单链接',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级菜单ID',
  `menu_ico` varchar(50) DEFAULT NULL COMMENT '图标',
  `has_submenu` int(11) DEFAULT '0' COMMENT '是否有子菜单 1 是 0 否',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable_flag` int(11) DEFAULT '1' COMMENT '逻辑删除字段 0 已删除 1 正常使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_menu` */

insert  into `d_sys_menu`(`id`,`menu_name`,`menu_desc`,`menu_remark`,`menu_order`,`menu_url`,`parent_id`,`menu_ico`,`has_submenu`,`create_time`,`enable_flag`) values 
('8a808eb96f401fa2016f401fbe9f0000','系统管理',NULL,NULL,70,NULL,NULL,'/images/ico_08.png',1,'2019-12-26 10:53:40',1),
('8a808eb96f40d5e7016f40d601d40000','用户管理',NULL,NULL,10,'/sys/user/list','8a808eb96f401fa2016f401fbe9f0000',NULL,NULL,'2019-12-26 14:12:45',1),
('8a808eb96f40d5e7016f40d602250001','角色管理',NULL,NULL,20,'/sys/role/list','8a808eb96f401fa2016f401fbe9f0000',NULL,0,'2019-12-26 14:12:45',1),
('8a808eb96f40d5e7016f40d602290002','权限管理',NULL,NULL,30,'/sys/menu/list','8a808eb96f401fa2016f401fbe9f0000',NULL,0,'2019-12-26 14:12:45',1),
('8a808ec36dec656c016dec65811d0000','首页',NULL,NULL,10,'/project/index',NULL,'/images/ico_01.png',0,'2019-10-21 11:39:01',1),
('8a808ecb6f896168016f8968090c0004','组织管理',NULL,NULL,40,'/sys/org/list','8a808eb96f401fa2016f401fbe9f0000',NULL,NULL,'2020-01-09 16:24:48',1);

/*Table structure for table `d_sys_org` */

DROP TABLE IF EXISTS `d_sys_org`;

CREATE TABLE `d_sys_org` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `push_id` varchar(100) DEFAULT NULL COMMENT '平台推送过来的ID',
  `org_name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级组织',
  `org_level` int(11) DEFAULT NULL COMMENT '组织层级',
  `org_order` int(11) DEFAULT NULL COMMENT '组织排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable_flag` int(11) DEFAULT '1' COMMENT '逻辑删除字段 0 已删除 1 正常使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_org` */

insert  into `d_sys_org`(`id`,`push_id`,`org_name`,`parent_id`,`org_level`,`org_order`,`create_time`,`enable_flag`) values
('ff143f22cf6d4ca4a672e349caf478dd',NULL,'TaiJi','',NULL,1,'2020-01-07 16:13:42',1);

/*Table structure for table `d_sys_role` */

DROP TABLE IF EXISTS `d_sys_role`;

CREATE TABLE `d_sys_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色说明',
  `role_remark` varchar(255) DEFAULT NULL COMMENT '角色备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable_flag` int(11) DEFAULT '1' COMMENT '逻辑删除字段 0 已删除 1 正常使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_role` */

insert  into `d_sys_role`(`id`,`role_name`,`role_desc`,`role_remark`,`create_time`,`enable_flag`) values 
('8a808ec36dec4f74016dec4f84fb0001','ROLE_ADMIN','管理员角色','全局管理员角色','2019-10-21 11:15:00',1);

/*Table structure for table `d_sys_role_menu` */

DROP TABLE IF EXISTS `d_sys_role_menu`;

CREATE TABLE `d_sys_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单主键	 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_role_menu` */

insert  into `d_sys_role_menu`(`role_id`,`menu_id`) values 
('8a808ec36dec4f74016dec4f84fb0001','8a808eb96f401fa2016f401fbe9f0000'),
('8a808ec36dec4f74016dec4f84fb0001','8a808eb96f40d5e7016f40d602250001'),
('8a808ec36dec4f74016dec4f84fb0001','8a808eb96f40d5e7016f40d602290002'),
('8a808ec36dec4f74016dec4f84fb0001','8a808ec36dec656c016dec65811d0000'),
('8a808ec36dec4f74016dec4f84fb0001','8a808ecb6f896168016f8968090c0004'),
('8a808ec36dec4f74016dec4f84fb0001','8a808eb96f40d5e7016f40d601d40000');

/*Table structure for table `d_sys_user` */

DROP TABLE IF EXISTS `d_sys_user`;

CREATE TABLE `d_sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `push_id` varchar(100) DEFAULT NULL COMMENT '平台推送过来的ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `user_login_name` varchar(255) DEFAULT NULL COMMENT '用户登录名称',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `user_mail` varchar(255) DEFAULT NULL COMMENT 'user_mail',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织ID',
  `last_login_time` varchar(100) DEFAULT NULL COMMENT '上次登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable_flag` int(11) DEFAULT '1' COMMENT '逻辑删除字段 0 已删除 1 正常使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_user` */

insert  into `d_sys_user`(`id`,`push_id`,`user_name`,`user_login_name`,`user_password`,`user_phone`,`user_mail`,`org_id`,`last_login_time`,`create_time`,`enable_flag`) values 
('f07bf386da044ae48fe7bbd08478ceb6',NULL,'全站管理员','admin','$2a$10$puBHZpl2vUzGOVijjUssO.JQOijRVA6GqJDEgnCYCbROIzMMFQ.Ca',NULL,'admin@admin.com','ff143f22cf6d4ca4a672e349caf478dd',NULL,'2020-03-17 10:58:47',1);

/*Table structure for table `d_sys_user_role` */

DROP TABLE IF EXISTS `d_sys_user_role`;

CREATE TABLE `d_sys_user_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_sys_user_role` */

insert  into `d_sys_user_role`(`role_id`,`user_id`) values 
('8a808ec36dec4f74016dec4f84fb0001','f07bf386da044ae48fe7bbd08478ceb6');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
