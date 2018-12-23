/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost
 Source Database       : jeecf

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 12/23/2018 09:10:37 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `customer_action_log`
-- ----------------------------
DROP TABLE IF EXISTS `customer_action_log`;
CREATE TABLE `customer_action_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(64) NOT NULL DEFAULT '' COMMENT '主机地址',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户',
  `action_type` int(5) NOT NULL DEFAULT '0' COMMENT '操作类型',
  `action_data` varchar(255) NOT NULL DEFAULT '' COMMENT '行为数据',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`user_name`) USING BTREE,
  KEY `idx_action_type` (`action_type`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8mb4 COMMENT='客户日志';

-- ----------------------------
--  Table structure for `gen_field`
-- ----------------------------
DROP TABLE IF EXISTS `gen_field`;
CREATE TABLE `gen_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `descrition` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `sys_namespace_id` int(11) NOT NULL DEFAULT '0' COMMENT '名称空间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name_namespace` (`name`,`sys_namespace_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_namespace_id` (`sys_namespace_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='生成参数';

-- ----------------------------
--  Records of `gen_field`
-- ----------------------------
BEGIN;
INSERT INTO `gen_field` VALUES ('9', '多数据规则例子', '参数例子', '1', '', '2018-11-02 15:33:18', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-10 16:42:40', 'be50e868ce4841ebb63bb1694b2413ef'), ('10', '多数据规则例子', '参数例子', '5', '', '2018-12-02 14:05:10', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-02 14:05:10', 'be50e868ce4841ebb63bb1694b2413ef'), ('11', '多表规则例子', '参数例子', '1', '', '2018-12-19 14:32:10', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:32:10', 'be50e868ce4841ebb63bb1694b2413ef'), ('12', '多表规则例子', '参数例子', '5', '', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `gen_field_column`
-- ----------------------------
DROP TABLE IF EXISTS `gen_field_column`;
CREATE TABLE `gen_field_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gen_field_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联gen_field',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `is_null` int(1) NOT NULL DEFAULT '0' COMMENT '是否允许为空',
  `default_value` varchar(30) NOT NULL DEFAULT '' COMMENT '默认值',
  `descrition` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `idx_gen_field_id` (`gen_field_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COMMENT='生成参数列表';

-- ----------------------------
--  Records of `gen_field_column`
-- ----------------------------
BEGIN;
INSERT INTO `gen_field_column` VALUES ('88', '9', 'packageName', '1', '', '包名', '', '2018-12-19 14:25:27', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:25:27', 'be50e868ce4841ebb63bb1694b2413ef'), ('89', '9', 'filterField', '1', 'sysNamespaceId', '数据过滤', '', '2018-12-19 14:25:27', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:25:27', 'be50e868ce4841ebb63bb1694b2413ef'), ('90', '9', 'groupField', '1', 'type', '分组字段', '', '2018-12-19 14:25:27', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:25:27', 'be50e868ce4841ebb63bb1694b2413ef'), ('96', '11', 'packageName', '1', '', '包名', '', '2018-12-19 14:38:21', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:38:21', 'be50e868ce4841ebb63bb1694b2413ef'), ('97', '11', 'filterField', '1', 'sysNamespaceId', '过滤属性', '', '2018-12-19 14:38:21', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:38:21', 'be50e868ce4841ebb63bb1694b2413ef'), ('98', '11', 'manyField', '1', 'field', '分表字段', '', '2018-12-19 14:38:21', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:38:21', 'be50e868ce4841ebb63bb1694b2413ef'), ('99', '10', 'packageName', '1', '', '包名', '', '2018-12-21 13:09:32', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:09:32', 'be50e868ce4841ebb63bb1694b2413ef'), ('100', '10', 'filterField', '1', 'sysNamespaceId', '数据过滤', '', '2018-12-21 13:09:32', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:09:32', 'be50e868ce4841ebb63bb1694b2413ef'), ('101', '10', 'groupField', '1', 'type', '分组字段', '', '2018-12-21 13:09:32', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:09:32', 'be50e868ce4841ebb63bb1694b2413ef'), ('102', '12', 'packageName', '1', '', '包名', '', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef'), ('103', '12', 'filterField', '1', 'sysNamespaceId', '过滤属性', '', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef'), ('104', '12', 'manyField', '1', 'field', '分表字段', '', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:13:00', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `gen_table`
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名称',
  `comment` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '解释',
  `class_name` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '实体类名称',
  `parent_table_id` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '关联父表',
  `parent_table_fk` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '关联父表外键',
  `sys_namespace_id` int(11) NOT NULL DEFAULT '0' COMMENT '命名空间',
  `sys_dbsource_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联数据源',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注信息',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '更新者',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name_namespace` (`name`,`sys_namespace_id`,`sys_dbsource_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_namespace_id` (`sys_namespace_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务表';

-- ----------------------------
--  Records of `gen_table`
-- ----------------------------
BEGIN;
INSERT INTO `gen_table` VALUES ('23', 'gen_field', '生成参数', 'genField', '', '', '1', '2', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('26', 'gen_field', '生成参数', 'genField', '', '', '5', '2', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('27', 'sys_dict', '数据字典', 'sysDict', '', '', '5', '2', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('28', 'sys_dict', '数据字典', 'sysDict', '', '', '1', '2', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-10 18:33:54', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('29', 'sys_table_dict', '系统表组字典', 'sysTableDict', '', '', '1', '2', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('30', 'sys_table_dict', '系统表组字典', 'sysTableDict', '', '', '5', '2', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50');
COMMIT;

-- ----------------------------
--  Table structure for `gen_table_column`
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gen_table_id` int(11) NOT NULL DEFAULT '0' COMMENT '归属表编号',
  `name` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名称',
  `comment` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '解释',
  `jdbc_type` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'jdbc类型',
  `field` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'JAVA字段名',
  `is_key` int(1) NOT NULL DEFAULT '0' COMMENT '是否为主键',
  `is_null` int(1) NOT NULL DEFAULT '0' COMMENT '是否可为空',
  `is_insert` int(1) NOT NULL DEFAULT '0' COMMENT '是否为插入字段',
  `is_edit` int(1) NOT NULL DEFAULT '0' COMMENT '是否编辑字段',
  `is_list` int(1) NOT NULL DEFAULT '0' COMMENT '是否列表字段',
  `is_query` int(1) NOT NULL DEFAULT '0' COMMENT '是否查询字段',
  `query_type` int(1) NOT NULL DEFAULT '0' COMMENT '查询方式',
  `form_type` int(1) NOT NULL DEFAULT '0' COMMENT '表单类型',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注信息',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '更新者',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_table_id` (`gen_table_id`) USING BTREE,
  KEY `idx_sort` (`sort`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=359 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务表字段';

-- ----------------------------
--  Records of `gen_table_column`
-- ----------------------------
BEGIN;
INSERT INTO `gen_table_column` VALUES ('257', '23', 'id', '主键', 'int(11)', 'id', '1', '1', '0', '0', '0', '0', '0', '2', '10', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('258', '23', 'name', '名称', 'varchar(20)', 'name', '0', '1', '0', '0', '0', '0', '0', '0', '20', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('259', '23', 'descrition', '描述', 'varchar(50)', 'descrition', '0', '1', '0', '0', '0', '0', '0', '0', '30', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('260', '23', 'sys_namespace_id', '名称空间', 'int(11)', 'sysNamespaceId', '0', '1', '0', '0', '0', '0', '0', '2', '40', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('261', '23', 'remark', '备注', 'varchar(255)', 'remark', '0', '1', '0', '0', '0', '0', '0', '0', '50', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('262', '23', 'create_date', '创建时间', 'datetime', 'createDate', '0', '1', '0', '0', '0', '0', '0', '3', '60', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('263', '23', 'create_by', '创建人', 'varchar(64)', 'createBy', '0', '1', '0', '0', '0', '0', '0', '0', '70', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('264', '23', 'update_date', '更新时间', 'datetime', 'updateDate', '0', '1', '0', '0', '0', '0', '0', '3', '80', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('265', '23', 'update_by', '更新人', 'varchar(64)', 'updateBy', '0', '1', '0', '0', '0', '0', '0', '0', '90', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 16:15:24'), ('285', '26', 'id', '主键', 'int(11)', 'id', '1', '1', '1', '0', '0', '0', '0', '2', '10', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('286', '26', 'name', '名称', 'varchar(20)', 'name', '0', '1', '0', '0', '0', '0', '0', '0', '20', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('287', '26', 'descrition', '描述', 'varchar(50)', 'descrition', '0', '1', '0', '0', '0', '0', '0', '0', '30', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('288', '26', 'sys_namespace_id', '名称空间', 'int(11)', 'sysNamespaceId', '0', '1', '0', '0', '0', '0', '0', '2', '40', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('289', '26', 'remark', '备注', 'varchar(255)', 'remark', '0', '1', '0', '0', '0', '0', '0', '0', '50', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('290', '26', 'create_date', '创建时间', 'datetime', 'createDate', '0', '1', '0', '0', '0', '0', '0', '3', '60', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('291', '26', 'create_by', '创建人', 'varchar(64)', 'createBy', '0', '1', '0', '0', '0', '0', '0', '0', '70', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('292', '26', 'update_date', '更新时间', 'datetime', 'updateDate', '0', '1', '0', '0', '0', '0', '0', '3', '80', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('293', '26', 'update_by', '更新人', 'varchar(64)', 'updateBy', '0', '1', '0', '0', '0', '0', '0', '0', '90', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-06 19:12:24'), ('294', '27', 'id', '主键', 'int(11)', 'id', '1', '1', '0', '0', '0', '0', '0', '2', '10', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('295', '27', 'type', '类型', 'varchar(50)', 'type', '0', '1', '0', '0', '0', '0', '0', '0', '20', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('296', '27', 'name', '名称', 'varchar(20)', 'name', '0', '1', '0', '0', '0', '0', '0', '0', '30', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('297', '27', 'label', '标签', 'varchar(20)', 'label', '0', '1', '0', '0', '0', '0', '0', '0', '40', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('298', '27', 'value', '值', 'int(5)', 'value', '0', '1', '0', '0', '0', '0', '0', '2', '50', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('299', '27', 'description', '描述', 'varchar(50)', 'description', '0', '1', '0', '0', '0', '0', '0', '0', '60', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('300', '27', 'sys_namespace_id', '关联命名空间', 'int(11)', 'sysNamespaceId', '0', '1', '0', '0', '0', '0', '0', '2', '70', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('301', '27', 'remark', '备注', 'varchar(255)', 'remark', '0', '1', '0', '0', '0', '0', '0', '0', '80', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('302', '27', 'create_by', '创建人', 'varchar(64)', 'createBy', '0', '1', '0', '0', '0', '0', '0', '0', '90', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('303', '27', 'create_date', '创建时间', 'datetime', 'createDate', '0', '1', '0', '0', '0', '0', '0', '3', '100', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('304', '27', 'update_by', '更新人', 'varchar(64)', 'updateBy', '0', '1', '0', '0', '0', '0', '0', '0', '110', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('305', '27', 'update_date', '更新时间', 'datetime', 'updateDate', '0', '1', '0', '0', '0', '0', '0', '3', '120', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 16:06:49'), ('321', '28', 'id', '主键', 'int(11)', 'id', '1', '1', '1', '0', '0', '0', '0', '2', '10', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('322', '28', 'type', '类型', 'varchar(50)', 'type', '0', '1', '0', '0', '0', '0', '0', '0', '20', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('323', '28', 'name', '名称', 'varchar(20)', 'name', '0', '1', '0', '0', '0', '0', '0', '0', '30', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('324', '28', 'label', '标签', 'varchar(20)', 'label', '0', '1', '0', '0', '0', '0', '0', '0', '40', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('325', '28', 'value', '值', 'int(5)', 'value', '0', '1', '0', '0', '0', '0', '0', '2', '50', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('326', '28', 'description', '描述', 'varchar(50)', 'description', '0', '1', '0', '0', '0', '0', '0', '0', '60', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('327', '28', 'sys_namespace_id', '关联命名空间', 'int(11)', 'sysNamespaceId', '0', '1', '0', '0', '0', '0', '0', '2', '70', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('328', '28', 'remark', '备注', 'varchar(255)', 'remark', '0', '1', '0', '0', '0', '0', '0', '0', '80', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('329', '28', 'create_by', '创建人', 'varchar(64)', 'createBy', '0', '1', '0', '0', '0', '0', '0', '0', '90', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('330', '28', 'create_date', '创建时间', 'datetime', 'createDate', '0', '1', '0', '0', '0', '0', '0', '3', '100', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('331', '28', 'update_by', '更新人', 'varchar(64)', 'updateBy', '0', '1', '0', '0', '0', '0', '0', '0', '110', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('332', '28', 'update_date', '更新时间', 'datetime', 'updateDate', '0', '1', '0', '0', '0', '0', '0', '3', '120', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-13 13:42:38'), ('333', '29', 'id', '主键', 'int(11)', 'id', '1', '1', '0', '0', '0', '0', '0', '2', '10', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('334', '29', 'name', '组名', 'varchar(20)', 'name', '0', '1', '0', '0', '0', '0', '0', '0', '20', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('335', '29', 'table_name', '表名', 'varchar(30)', 'tableName', '0', '1', '0', '0', '0', '0', '0', '0', '30', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('336', '29', 'field', '属性', 'varchar(30)', 'field', '0', '1', '0', '0', '0', '0', '0', '0', '40', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('337', '29', 'comment', '注释', 'varchar(50)', 'comment', '0', '1', '0', '0', '0', '0', '0', '0', '50', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('338', '29', 'description', '描述', 'varchar(50)', 'description', '0', '1', '0', '0', '0', '0', '0', '0', '60', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('339', '29', 'sys_dbsource_id', '关联数据源', 'int(11)', 'sysDbsourceId', '0', '1', '0', '0', '0', '0', '0', '2', '70', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('340', '29', 'sys_namespace_id', '命名空间', 'int(11)', 'sysNamespaceId', '0', '1', '0', '0', '0', '0', '0', '2', '80', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('341', '29', 'remark', '备注', 'varchar(255)', 'remark', '0', '1', '0', '0', '0', '0', '0', '0', '90', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('342', '29', 'create_date', '创建时间', 'timestamp', 'createDate', '0', '1', '0', '0', '0', '0', '0', '3', '100', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('343', '29', 'update_date', '更新世界', 'timestamp', 'updateDate', '0', '1', '0', '0', '0', '0', '0', '3', '110', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('344', '29', 'create_by', '创建人', 'varchar(64)', 'createBy', '0', '1', '0', '0', '0', '0', '0', '0', '120', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('345', '29', 'update_by', '更新时间', 'varchar(64)', 'updateBy', '0', '1', '0', '0', '0', '0', '0', '0', '130', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:37:36'), ('346', '30', 'id', '主键', 'int(11)', 'id', '1', '1', '0', '0', '0', '0', '0', '2', '10', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('347', '30', 'name', '组名', 'varchar(20)', 'name', '0', '1', '0', '0', '0', '0', '0', '0', '20', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('348', '30', 'table_name', '表名', 'varchar(30)', 'tableName', '0', '1', '0', '0', '0', '0', '0', '0', '30', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('349', '30', 'field', '属性', 'varchar(30)', 'field', '0', '1', '0', '0', '0', '0', '0', '0', '40', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('350', '30', 'comment', '注释', 'varchar(50)', 'comment', '0', '1', '0', '0', '0', '0', '0', '0', '50', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('351', '30', 'description', '描述', 'varchar(50)', 'description', '0', '1', '0', '0', '0', '0', '0', '0', '60', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('352', '30', 'sys_dbsource_id', '关联数据源', 'int(11)', 'sysDbsourceId', '0', '1', '0', '0', '0', '0', '0', '2', '70', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('353', '30', 'sys_namespace_id', '命名空间', 'int(11)', 'sysNamespaceId', '0', '1', '0', '0', '0', '0', '0', '2', '80', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('354', '30', 'remark', '备注', 'varchar(255)', 'remark', '0', '1', '0', '0', '0', '0', '0', '0', '90', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('355', '30', 'create_date', '创建时间', 'timestamp', 'createDate', '0', '1', '0', '0', '0', '0', '0', '3', '100', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('356', '30', 'update_date', '更新世界', 'timestamp', 'updateDate', '0', '1', '0', '0', '0', '0', '0', '3', '110', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('357', '30', 'create_by', '创建人', 'varchar(64)', 'createBy', '0', '1', '0', '0', '0', '0', '0', '0', '120', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50'), ('358', '30', 'update_by', '更新时间', 'varchar(64)', 'updateBy', '0', '1', '0', '0', '0', '0', '0', '0', '130', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:33:50');
COMMIT;

-- ----------------------------
--  Table structure for `gen_template`
-- ----------------------------
DROP TABLE IF EXISTS `gen_template`;
CREATE TABLE `gen_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `sys_namespace_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属命名空间',
  `language` int(1) NOT NULL DEFAULT '1' COMMENT '语言',
  `gen_field_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联gen_field',
  `file_base_path` varchar(100) NOT NULL DEFAULT '' COMMENT '模版文件基础路径',
  `wiki_uri` varchar(100) NOT NULL DEFAULT '' COMMENT 'wiki地址',
  `descrition` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name_namespace` (`name`,`sys_namespace_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_namespace_id` (`sys_namespace_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成模版信息';

-- ----------------------------
--  Records of `gen_template`
-- ----------------------------
BEGIN;
INSERT INTO `gen_template` VALUES ('15', '多数据规则例子模版', '1', '1', '9', 'b972a628cf5441bb9b11e7521ce45616ovscs/dict', '/jianym/jeecf', '例子模版', '', '2018-11-02 15:33:45', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-21 20:15:56', 'be50e868ce4841ebb63bb1694b2413ef'), ('16', '多数据规则例子模版', '5', '1', '10', '4f4896d2ea66472fbffb1d34a549e4fe/dict', '/jianym/jeecf', '例子模版', '', '2018-12-02 14:06:00', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-02 14:06:00', 'be50e868ce4841ebb63bb1694b2413ef'), ('17', '多表规则例子模版', '1', '1', '11', 'e755bbde1e3e4775a8d3eb22e36d82a8odken/tableDict', '/jianym/jeecf', '例子模版', '', '2018-12-19 14:36:56', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-19 14:36:56', 'be50e868ce4841ebb63bb1694b2413ef'), ('18', '多表规则例子模版', '5', '1', '12', 'c68a35b3f7584df881b0df4d0a2d8b6avtmrc/tableDict', '/jianym/jeecf', '例子模版', '', '2018-12-21 13:18:07', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:18:07', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dbsource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dbsource`;
CREATE TABLE `sys_dbsource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关键字',
  `url` varchar(150) NOT NULL DEFAULT '' COMMENT '连接串',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `permission` varchar(50) NOT NULL DEFAULT '' COMMENT '权限控制',
  `Usable` int(1) NOT NULL DEFAULT '1' COMMENT '是否可用 1.可用 2.不可用',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`key_name`) USING BTREE,
  KEY `idx_permission` (`permission`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='数据库连接信息';

-- ----------------------------
--  Records of `sys_dbsource`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dbsource` VALUES ('2', 'defaultDataSourceKey', 'jdbc:mysql://127.0.0.1:3306/jeecf?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull', 'root', '12345678', 'config:sysDbsource:work', '1', '', '0', '2018-11-10 16:17:28', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-10 16:17:28', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '类型',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '标签',
  `value` int(5) NOT NULL DEFAULT '0' COMMENT '值',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `sys_namespace_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联命名空间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_type_namespace` (`type`,`sys_namespace_id`,`label`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_namespace_id` (`sys_namespace_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
--  Records of `sys_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('90', 'sex', '男', 'MAN', '1', '性别', '1', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 11:42:12', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-01 21:22:29'), ('92', 'sex', '男', 'MAN', '1', '性别', '5', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-02 14:02:33', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-02 14:02:33'), ('93', 'sex', '女', 'WOMAN', '2', '性别', '5', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-02 14:03:32', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-02 14:03:32'), ('94', 'language', '汉语', 'CHINESE', '1', '语言', '1', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-15 12:58:29', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-15 12:58:29'), ('95', 'language', '英语', 'ENGLISH', '2', '语言', '1', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-15 13:01:17', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-15 13:01:17'), ('98', 'sex', '女', 'WOMAN', '2', '性别', '1', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-18 14:12:13', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-18 14:12:13'), ('99', 'language', '汉语', 'CHINESE', '1', '语言', '5', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:23:35', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:23:35'), ('100', 'language', '英语', 'ENGLISH', '2', '语言', '5', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:24:15', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 13:24:15');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '索引',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '标签',
  `parent_id` varchar(11) NOT NULL DEFAULT '' COMMENT '父菜单索引',
  `parent_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '所有父菜单索引',
  `level` int(5) NOT NULL DEFAULT '0' COMMENT '菜单等级',
  `module_label` varchar(20) NOT NULL DEFAULT '' COMMENT '模块菜单标签',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序',
  `route` int(1) NOT NULL DEFAULT '0' COMMENT '路由',
  `permission` varchar(50) NOT NULL DEFAULT '' COMMENT '权限',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标标识',
  `is_icon` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示图标',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_label` (`label`) USING BTREE,
  KEY `idx_module_label` (`module_label`) USING BTREE,
  KEY `idx_permission` (`permission`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('4', '代码数据', 'gen', '', '', '1', 'gen', '30', '0', 'gen:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-06-09 21:43:22', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:20:35'), ('7', '数表配置', 'genTable', '34', '34', '2', 'template', '20', '1', 'template:genTable:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-06-22 21:55:25', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:00'), ('11', '配置中心', 'config', '', '', '1', 'config', '20', '0', 'config:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-04 22:31:34', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 10:36:48'), ('12', '菜单配置', 'sysMenu', '11', '11', '2', 'config', '10', '1', 'config:sysMenu:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-04 22:31:34', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 10:36:48'), ('23', '数据字典', 'sysDict', '4', '4', '2', 'gen', '10', '1', 'gen:sysDict:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-17 23:37:42', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:42:46'), ('24', '用户权限', 'userpower', '', '', '1', 'userpower', '20', '0', 'userpower:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-23 23:15:24', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-31 00:18:59'), ('25', '权限配置', 'sysPower', '24', '24', '2', 'userpower', '40', '1', 'userpower:sysPower:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-23 23:15:57', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-25 10:45:22'), ('26', '系统角色', 'sysRole', '24', '24', '2', 'userpower', '30', '1', 'userpower:sysRole:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-24 23:50:29', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-25 10:45:37'), ('27', '系统用户', 'sysUser', '24', '24', '2', 'userpower', '10', '1', 'userpower:sysUser:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-28 19:56:21', 'be50e868ce4841ebb63bb1694b2413ef', '2018-07-31 00:18:59'), ('28', '运维管理', 'operation', '', '', '1', 'operation', '50', '0', 'operation:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-11 19:52:44', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:09'), ('29', 'api 接口', 'swagger2', '28', '28', '2', 'operation', '10', '1', 'operation:swagger2:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-11 20:08:06', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:09'), ('30', '连接池监控', 'druid', '28', '28', '2', 'operation', '20', '1', 'operation:druid:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-11 22:06:06', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:09'), ('31', '密码修改', 'sysPwd', '24', '24', '2', 'userpower', '20', '1', 'userpower:sysPwd:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-25 10:45:03', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 11:27:03'), ('32', '数据库配置', 'sysDbsource', '11', '11', '2', 'config', '40', '1', 'config:sysDbsource:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-08-28 13:05:48', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 10:36:48'), ('33', '命名空间', 'sysNamespace', '11', '11', '2', 'config', '20', '1', 'config:sysNamespace:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-09-09 15:38:01', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 10:36:48'), ('34', '模版生成', 'template', '', '', '1', 'template', '40', '0', 'template:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-09-15 22:05:59', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:00'), ('37', '模版配置', 'genTemplate', '34', '34', '2', 'template', '40', '1', 'template:genTemplate:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-09-16 18:48:36', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:00'), ('38', '模版参数', 'genField', '34', '34', '2', 'template', '30', '1', 'template:genField:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-09-17 16:53:51', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-27 19:21:00'), ('39', '文档', 'doc', '', '', '1', 'doc', '10', '0', 'doc:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 10:26:04', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 21:23:03'), ('40', '功能介绍', 'functionIntroduction', '39', '39', '2', 'doc', '10', '1', 'doc:functionIntroduction:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 10:26:45', 'be50e868ce4841ebb63bb1694b2413ef', '2018-10-28 21:23:03'), ('41', '客户操作日志', 'customerActionLog', '28', '28', '2', 'operation', '30', '1', 'operation:customerActionLog:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-22 15:33:37', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-22 15:33:37'), ('42', '组织结构', 'sysOffice', '11', '11', '2', 'config', '30', '1', 'config:sysOffice:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-09 09:45:55', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-09 09:45:55'), ('43', '表组字典', 'sysTableDict', '4', '4', '2', 'gen', '40', '1', 'gen:sysTableDict:view', '', '0', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-16 10:40:17', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-16 10:40:17');
COMMIT;

-- ----------------------------
--  Table structure for `sys_namespace`
-- ----------------------------
DROP TABLE IF EXISTS `sys_namespace`;
CREATE TABLE `sys_namespace` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `permission` varchar(50) NOT NULL DEFAULT '' COMMENT '权限',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_permission` (`permission`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='系统命名空间';

-- ----------------------------
--  Records of `sys_namespace`
-- ----------------------------
BEGIN;
INSERT INTO `sys_namespace` VALUES ('1', 'work', '工作', 'config:sysNamespace:work', '', '0', '2018-11-24 08:53:18', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-24 08:53:18', 'be50e868ce4841ebb63bb1694b2413ef'), ('5', 'guest', '客户', 'config:sysNamespace:guest', '', '0', '2018-11-30 18:03:56', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-21 12:57:03', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_office`
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `enname` varchar(20) NOT NULL DEFAULT '' COMMENT '英文名称',
  `parent_id` varchar(11) NOT NULL DEFAULT '' COMMENT '父级',
  `parent_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '所有父级',
  `level` int(5) NOT NULL DEFAULT '0' COMMENT '等级',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='组织机构';

-- ----------------------------
--  Records of `sys_office`
-- ----------------------------
BEGIN;
INSERT INTO `sys_office` VALUES ('1', 'jeecf', 'jeecf', '', '', '1', '10', '', '2018-12-09 10:26:02', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-18 12:50:37', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_power`
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `permission` varchar(50) NOT NULL DEFAULT '' COMMENT '权限标识',
  `parent_id` varchar(11) NOT NULL DEFAULT '' COMMENT '父级',
  `parent_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '所有父级',
  `level` int(5) NOT NULL DEFAULT '0' COMMENT '等级',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permission` (`permission`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
--  Records of `sys_power`
-- ----------------------------
BEGIN;
INSERT INTO `sys_power` VALUES ('10', '代码生成菜单权限', 'gen:base', '', '', '1', '40', '', '2018-07-31 23:56:01', '2018-11-04 13:42:59', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('11', '业务表菜单权限', 'template:genTable:base', '26', '26', '2', '10', '', '2018-07-31 23:57:57', '2018-11-04 13:43:36', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('13', '系统用户菜单权限', 'userpower:sysUser:base', '15', '15', '2', '10', '', '2018-08-01 20:28:03', '2018-11-04 13:44:45', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('14', '配置中心菜单权限', 'config:base', '', '', '1', '20', '', '2018-08-01 20:49:57', '2018-11-04 13:40:10', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('15', '用户管理菜单权限', 'userpower:base', '', '', '1', '30', '', '2018-08-01 20:51:17', '2018-11-04 13:42:37', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('16', '菜单配置权限', 'config:sysMenu:base', '14', '14', '2', '10', '', '2018-08-02 23:41:46', '2018-11-22 23:44:43', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('17', '数据字典权限', 'gen:sysDict:base', '10', '10', '2', '20', '', '2018-08-02 23:53:28', '2018-11-04 13:43:13', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('18', '系统角色权限', 'userpower:sysRole:base', '15', '15', '2', '30', '', '2018-08-02 23:57:28', '2018-11-04 13:45:10', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('19', '权限配置菜单权限', 'userpower:sysPower:base', '15', '15', '2', '40', '', '2018-08-02 23:58:59', '2018-11-04 13:45:42', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('20', '运维管理菜单权限', 'operation:base', '', '', '1', '60', '', '2018-08-11 19:55:32', '2018-11-04 13:44:14', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('21', 'api接口菜单权限', 'operation:swagger2:base', '20', '20', '2', '10', '', '2018-08-11 20:16:01', '2018-11-04 13:44:24', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('22', '数据库连接池菜单权限', 'operation:druid:base', '20', '20', '2', '20', '', '2018-08-11 22:07:22', '2018-11-04 13:44:33', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('23', '密码修改权限', 'userpower:sysPwd:base', '15', '15', '2', '20', '', '2018-08-25 11:02:25', '2018-11-04 13:44:59', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('24', '数据库配置权限', 'config:sysDbsource:base', '14', '14', '2', '30', '', '2018-08-28 13:07:25', '2018-11-04 13:42:23', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('25', '命名空间配置权限', 'config:sysNamespace:base', '14', '14', '2', '20', '', '2018-09-09 15:39:45', '2018-11-04 13:42:00', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('26', '模版生成权限', 'template:base', '', '', '1', '50', '', '2018-09-15 22:12:49', '2018-11-04 13:43:27', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('29', '模版配置权限', 'template:genTemplate:base', '26', '26', '2', '30', '', '2018-09-16 19:04:36', '2018-11-04 13:43:49', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('30', '模版参数权限', 'template:genField:base', '26', '26', '2', '40', '', '2018-09-17 16:54:44', '2018-11-04 13:44:04', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('31', '文档权限', 'doc:base', '', '', '1', '10', '', '2018-10-28 10:37:37', '2018-12-21 13:14:01', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('32', '功能介绍文档权限', 'doc:functionIntroduction:base', '31', '31', '2', '10', '', '2018-10-28 10:39:06', '2018-12-21 13:14:06', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('33', '数据库配置视图权限', 'config:sysDbsource:view', '24', '14,24', '4', '10', '', '2018-11-01 11:08:42', '2018-11-01 11:08:42', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('34', '菜单配置视图权限', 'config:sysMenu:view', '16', '14,16', '3', '10', '', '2018-11-01 12:57:16', '2018-11-01 12:57:16', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('35', '菜单配置编辑权限', 'config:sysMenu:edit', '16', '14,16', '3', '20', '', '2018-11-01 12:57:56', '2018-11-01 12:57:56', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('36', '命名空间配置视图权限', 'config:sysNamespace:view', '25', '14,25', '4', '10', '', '2018-11-01 13:01:07', '2018-11-01 13:01:07', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('37', '命名空间配置编辑权限', 'config:sysNamespace:edit', '25', '14,25', '4', '20', '', '2018-11-01 13:01:44', '2018-11-01 13:01:44', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('38', '数据库配置编辑权限', 'config:sysDbsource:edit', '24', '14,24', '4', '20', '', '2018-11-01 13:02:37', '2018-11-01 13:02:37', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('39', '数据字典视图权限', 'gen:sysDict:view', '17', '10,17', '3', '10', '', '2018-11-01 13:03:31', '2018-11-01 13:03:31', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('40', '数据字典编辑权限', 'gen:sysDict:edit', '17', '10,17', '3', '20', '', '2018-11-01 13:04:26', '2018-11-01 13:04:26', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('41', '系统用户菜单视图权限', 'userpower:sysUser:view', '13', '15,13', '3', '10', '', '2018-11-01 13:05:39', '2018-11-01 13:05:39', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('42', '系统用户菜单编辑权限', 'userpower:sysUser:edit', '13', '15,13', '3', '20', '', '2018-11-01 13:06:37', '2018-11-01 13:06:37', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('43', '系统角色视图权限', 'userpower:sysRole:view', '18', '15,18', '3', '10', '', '2018-11-01 13:07:49', '2018-11-01 13:07:49', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('44', '系统角色编辑权限', 'userpower:sysRole:edit', '18', '15,18', '3', '40', '', '2018-11-01 13:08:33', '2018-11-01 13:08:33', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('45', '权限配置菜单视图权限', 'userpower:sysPower:view', '19', '15,19', '4', '10', '', '2018-11-01 13:09:17', '2018-11-01 13:09:17', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('46', '权限配置菜单编辑权限', 'userpower:sysPower:edit', '19', '15,19', '4', '20', '', '2018-11-01 13:09:58', '2018-11-01 13:09:58', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('47', '业务表菜单视图权限', 'template:genTable:view', '11', '26,11', '3', '10', '', '2018-11-01 13:11:28', '2018-11-01 13:11:28', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('48', '业务表菜单编辑权限', 'template:genTable:edit', '11', '26,11', '3', '20', '', '2018-11-01 13:12:02', '2018-11-01 13:12:02', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('49', '模版配置视图权限', 'template:genTemplate:view', '29', '26,29', '3', '10', '', '2018-11-01 13:13:04', '2018-11-01 13:13:04', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('50', '模版配置编辑权限', 'template:genTemplate:edit', '29', '26,29', '3', '20', '', '2018-11-01 13:13:48', '2018-11-01 13:13:48', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('51', '模版参数视图权限', 'template:genField:view', '30', '26,30', '3', '10', '', '2018-11-01 13:14:24', '2018-11-01 13:14:24', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('52', '模版参数编辑权限', 'template:genField:edit', '30', '26,30', '3', '20', '', '2018-11-01 13:14:47', '2018-11-01 13:14:47', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('54', '数据源work权限', 'config:sysDbsource:work', '14', '14', '2', '30', '', '2018-11-02 21:06:04', '2018-11-04 13:40:10', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('55', '数据源guest权限', 'config:sysDbsource:guest', '14', '14', '2', '30', '', '2018-11-03 14:38:36', '2018-11-30 17:59:48', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('57', '客户操作日志权限', 'operation:customerActionLog:view', '20', '20', '2', '30', '', '2018-11-22 15:34:52', '2018-11-22 15:38:52', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('58', '命名空间work权限', 'config:sysNamespace:work', '14', '14', '2', '20', '', '2018-11-24 09:06:51', '2018-11-24 09:06:51', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('62', '命名空间guest权限', 'config:sysNamespace:guest', '14', '14', '2', '10', '', '2018-11-30 18:03:56', '2018-12-04 14:37:51', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('63', '组织结构权限', 'config:sysOffice:base', '14', '14', '2', '30', '', '2018-12-09 09:46:42', '2018-12-09 09:46:42', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('64', '组织结构视图权限', 'config:sysOffice:view', '63', '14,63', '3', '10', '', '2018-12-09 10:00:13', '2018-12-09 10:00:13', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('65', '组织结构编辑权限', 'config:sysOffice:edit', '63', '14,63', '3', '20', '', '2018-12-09 10:03:30', '2018-12-09 10:03:30', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('66', '表组字典权限', 'gen:sysTableDict:base', '10', '10', '2', '50', '', '2018-12-16 10:41:58', '2018-12-16 10:41:58', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('67', '表组字典编辑权限', 'gen:sysTableDict:edit', '66', '10,66', '3', '10', '', '2018-12-16 10:42:28', '2018-12-16 10:42:28', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('68', '表组字典视图权限', 'gen:sysTableDict:view', '66', '10,66', '3', '20', '', '2018-12-16 10:45:36', '2018-12-16 10:45:36', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '中文名',
  `enname` varchar(20) NOT NULL DEFAULT '' COMMENT '英文名',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_enname` (`enname`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('4', '管理员', 'admin', 'admin', '2018-07-27 22:07:59', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('5', '客户', 'guest', 'guest', '2018-11-01 16:24:41', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_power`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_power`;
CREATE TABLE `sys_role_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联角色',
  `power_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联权限',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_power` (`role_id`,`power_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_power_id` (`power_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限关联信息';

-- ----------------------------
--  Records of `sys_role_power`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_power` VALUES ('164', '4', '31', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('165', '4', '32', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('166', '4', '20', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('167', '4', '57', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('168', '4', '22', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('169', '4', '21', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('170', '4', '26', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('171', '4', '30', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('172', '4', '52', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('173', '4', '51', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('174', '4', '29', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('175', '4', '50', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('176', '4', '49', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('177', '4', '11', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('178', '4', '48', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('179', '4', '47', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('180', '4', '10', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('181', '4', '66', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('182', '4', '68', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('183', '4', '67', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('184', '4', '17', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('185', '4', '40', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('186', '4', '39', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('187', '4', '15', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('188', '4', '19', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('189', '4', '46', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('190', '4', '45', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('191', '4', '18', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('192', '4', '44', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('193', '4', '43', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('194', '4', '23', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('195', '4', '13', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('196', '4', '42', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('197', '4', '41', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('198', '4', '14', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('199', '4', '63', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('200', '4', '65', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('201', '4', '64', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('202', '4', '62', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('203', '4', '55', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('204', '4', '58', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('205', '4', '16', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('206', '4', '35', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('207', '4', '34', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('208', '4', '24', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('209', '4', '38', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('210', '4', '33', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('211', '4', '25', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('212', '4', '37', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('213', '4', '36', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('214', '4', '54', '', '2018-12-16 10:45:54', '2018-12-16 10:45:54', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('215', '5', '31', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('216', '5', '32', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('217', '5', '20', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('218', '5', '57', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('219', '5', '26', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('220', '5', '30', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('221', '5', '51', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('222', '5', '29', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('223', '5', '49', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('224', '5', '11', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('225', '5', '47', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('226', '5', '10', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('227', '5', '66', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('228', '5', '68', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('229', '5', '17', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('230', '5', '39', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('231', '5', '14', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('232', '5', '63', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('233', '5', '64', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('234', '5', '62', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('235', '5', '55', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('236', '5', '24', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('237', '5', '33', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('238', '5', '25', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('239', '5', '36', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('240', '5', '54', '', '2018-12-18 12:52:03', '2018-12-18 12:52:03', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_table_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_table_dict`;
CREATE TABLE `sys_table_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '组名',
  `table_name` varchar(30) NOT NULL DEFAULT '' COMMENT '表名',
  `field` varchar(30) NOT NULL DEFAULT '' COMMENT '属性',
  `comment` varchar(50) NOT NULL DEFAULT '' COMMENT '注释',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `sys_dbsource_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联数据源',
  `sys_namespace_id` int(11) NOT NULL DEFAULT '0' COMMENT '命名空间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新世界',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique` (`name`,`table_name`,`sys_dbsource_id`,`sys_namespace_id`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统表组字典';

-- ----------------------------
--  Records of `sys_table_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_table_dict` VALUES ('1', 'test', 'sys_dict', 'sysDict', '系统字典', 'test', '2', '1', '', '2018-12-16 11:49:57', '2018-12-16 13:26:09', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('2', 'test', 'sys_dict', 'sysDict', '数据字典', 'test', '2', '5', '', '2018-12-21 13:19:21', '2018-12-21 13:19:21', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef'), ('3', 'test', 'gen_field', 'genField', '生成参数', 'test', '2', '5', '', '2018-12-21 13:24:58', '2018-12-21 13:24:58', 'be50e868ce4841ebb63bb1694b2413ef', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '账户',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `sys_office_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联组织结构',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('231e7179e1494c25acc6396576096512', 'guest', 'bff7fe14d15571cbe9d02f91083613261777c3b5cec326846469ac35', 'guest', '1', '', '0', '2018-11-01 16:24:57', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 12:07:13', 'be50e868ce4841ebb63bb1694b2413ef'), ('be50e868ce4841ebb63bb1694b2413ef', 'admin', '86f1d53760ebe5cd32203a54927b11acfcc4d4df6c94bf62ec45ecb0', 'admin', '1', '', '0', '2018-08-09 23:42:07', '1b6a9a1d8b2b46819fd9d4d7cefd8ecd', '2018-09-02 12:58:19', 'be50e868ce4841ebb63bb1694b2413ef');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_namespace`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_namespace`;
CREATE TABLE `sys_user_namespace` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(64) NOT NULL DEFAULT '' COMMENT '用户id',
  `namespace_id` int(11) NOT NULL DEFAULT '0' COMMENT '命名空间id',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_namespace` (`user_id`,`namespace_id`) USING BTREE,
  KEY `idx_user` (`user_id`) USING BTREE,
  KEY `idx_namespace` (`namespace_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='用户命名空间 对应关系';

-- ----------------------------
--  Records of `sys_user_namespace`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_namespace` VALUES ('3', 'be50e868ce4841ebb63bb1694b2413ef', '1', '', '2018-09-27 13:57:46', 'be50e868ce4841ebb63bb1694b2413ef', '2018-11-21 19:23:21', 'be50e868ce4841ebb63bb1694b2413ef'), ('5', '231e7179e1494c25acc6396576096512', '5', '', '2018-11-30 19:32:02', '231e7179e1494c25acc6396576096512', '2018-11-30 19:32:02', '231e7179e1494c25acc6396576096512');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(255) NOT NULL DEFAULT '' COMMENT '关联系统用户',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联系统角色',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_by` varchar(255) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_role` (`user_id`,`role_id`) USING BTREE,
  KEY `idx_user` (`user_id`) USING BTREE,
  KEY `idx_role` (`role_id`) USING BTREE,
  KEY `idx_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色对应表';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '231e7179e1494c25acc6396576096512', '5', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 12:07:13', 'be50e868ce4841ebb63bb1694b2413ef', '2018-12-07 12:07:13'), ('5', 'be50e868ce4841ebb63bb1694b2413ef', '4', '', 'be50e868ce4841ebb63bb1694b2413ef', '2018-09-02 12:58:19', 'be50e868ce4841ebb63bb1694b2413ef', '2018-09-02 12:58:19');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
