/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : hep

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 19/06/2019 02:56:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (9, 0, '运行部', NULL, '2019-06-10 20:09:23');
INSERT INTO `t_dept` VALUES (10, 9, '发电部门', NULL, '2019-06-10 20:10:32');
INSERT INTO `t_dept` VALUES (11, 9, '生产部', NULL, '2019-06-10 20:11:01');
INSERT INTO `t_dept` VALUES (12, 0, '检修部', NULL, '2019-06-10 20:13:30');
INSERT INTO `t_dept` VALUES (13, 0, '计划部', NULL, '2019-06-10 20:13:41');
INSERT INTO `t_dept` VALUES (14, 0, '技术部', NULL, '2019-06-10 20:13:50');
INSERT INTO `t_dept` VALUES (16, 0, '燃料部', NULL, '2019-06-10 20:19:50');
INSERT INTO `t_dept` VALUES (17, 0, '人事部', NULL, '2019-06-10 20:20:58');
INSERT INTO `t_dept` VALUES (18, 0, '安监部', NULL, '2019-06-10 20:34:28');
INSERT INTO `t_dept` VALUES (19, 0, '财务部', NULL, '2019-06-10 20:34:37');
INSERT INTO `t_dept` VALUES (20, 0, '保卫部', NULL, '2019-06-10 20:34:50');

-- ----------------------------
-- Table structure for t_equipment
-- ----------------------------
DROP TABLE IF EXISTS `t_equipment`;
CREATE TABLE `t_equipment`  (
  `EQUIPMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `EQUIPMENT_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `MODEL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `SUPPLIER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `DESCRIBE0` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备描述',
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '负责人id',
  `REMARKS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备备注',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备状态1可以0维修-2报废',
  PRIMARY KEY (`EQUIPMENT_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_equipment
-- ----------------------------
INSERT INTO `t_equipment` VALUES (8, '给水泵', '汽机', '荏原机械淄博有限公司', '给水泵', 26, '', '0');
INSERT INTO `t_equipment` VALUES (9, '再热冷段蒸汽及附属', '汽机', '瓦卢瑞克', '再热冷段蒸汽及附属', 167, '', '0');
INSERT INTO `t_equipment` VALUES (10, '给水系统三通阀', '汽机', '德国KSB', '给水系统三通阀', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (11, '送风机', '锅炉', '成都凯电站风机公司', '送风机', 6, '', '1');
INSERT INTO `t_equipment` VALUES (12, '给煤机', '锅炉', '上海大和衡器有限公司', '给煤机', 24, '', '1');
INSERT INTO `t_equipment` VALUES (13, '低温省煤器', '锅炉', '上海发电设备研究院', '低温省煤器', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (14, '高厂变', ' 电气', '常州东芝变压器公司', '高厂变', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (15, '500KV线路保护', '电气', '国电南京有限公司', '500KV线路保护', 23, '', '1');
INSERT INTO `t_equipment` VALUES (16, '电动执行机构（进口）', '热控', '扬州电力设备修造厂', '电动执行机构（进口）', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (17, '静电除尘器', '除灰', '福建龙净环保公司', '静电除尘器', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (18, '皮带机胶带', '输煤', '青岛橡胶六厂', '皮带机胶带', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (19, '衬胶防腐材料', '脱硫', '龙泉防腐有限公司', '衬胶防腐材料', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (20, '隔膜计量泵', '化学', '美国海王星', '隔膜计量泵', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (21, '空调系统冷却塔', '暖通', '常州航丰冷却公司', '空调系统冷却塔', NULL, '', '1');
INSERT INTO `t_equipment` VALUES (22, '发动机', '电气', '马拉松Maratho', '发动机', NULL, '', '1');

-- ----------------------------
-- Table structure for t_in_out_storehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_in_out_storehouse`;
CREATE TABLE `t_in_out_storehouse`  (
  `in_out_storehouse_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出入库id',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0出库1入库',
  `resources_id` bigint(20) NOT NULL COMMENT '物资id',
  `num` double NOT NULL COMMENT '数量',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `REMARKS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`in_out_storehouse_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_in_out_storehouse
-- ----------------------------
INSERT INTO `t_in_out_storehouse` VALUES (1, '0', 5, 100, NULL, '123');
INSERT INTO `t_in_out_storehouse` VALUES (2, '1', 5, 12, NULL, '23');
INSERT INTO `t_in_out_storehouse` VALUES (3, '1', 5, 123, '2019-06-17 15:47:02', '123');
INSERT INTO `t_in_out_storehouse` VALUES (4, '1', 5, 1, '2019-06-17 15:47:22', '123');
INSERT INTO `t_in_out_storehouse` VALUES (5, '0', 5, 200, '2019-06-17 15:47:35', '213');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `OPERATION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
  `TIME` decimal(11, 0) NULL DEFAULT NULL COMMENT '耗时',
  `METHOD` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作方法',
  `PARAMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法参数',
  `IP` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者IP',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4446 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES (4556, 'fuhao', '获取入库信息', 1, 'cc.hep.system.controller.InOutStorehouseController.inStorehouseIndex()', '', '127.0.0.1', '2019-06-18 12:45:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4557, 'fuhao', '库存统计', 0, 'cc.hep.system.controller.ResourcesController.statistics()', '', '127.0.0.1', '2019-06-18 12:45:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4558, 'fuhao', '获取在线用户信息', 1, 'cc.hep.system.controller.SessionController.online()', '', '127.0.0.1', '2019-06-18 12:46:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4559, 'aaa', '访问系统', 0, 'cc.hep.system.controller.LoginController.index()', 'user: \"User{userId=172, username=\'aaa\', password=\'f13502d39f6e495a2bd5d012deeb0fe5\', deptId=null, deptName=\'null\', email=\'null\', mobile=\'null\', status=\'1\', crateTime=Tue Jun 18 20:48:43 CST 2019, modifyTime=null, lastLoginTime=null, ssex=\'2\', theme=\'green\', avatar=\'default.jpg\', description=\'null\', roleName=\'null\'}\"  org.springframework.validation.BindingResult.user: \"org.springframework.validation.BeanPropertyBindingResult: 0 errors\"', '127.0.0.1', '2019-06-18 12:48:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4560, '123', '访问系统', 2, 'cc.hep.system.controller.LoginController.index()', 'user: \"User{userId=167, username=\'123\', password=\'f898d2cfacab311cba4927173361eb2f\', deptId=17, deptName=\'null\', email=\'2679998262@qq.com\', mobile=\'15170091857\', status=\'1\', crateTime=Tue Jun 11 04:26:20 CST 2019, modifyTime=Tue Jun 18 13:38:58 CST 2019, lastLoginTime=Tue Jun 18 19:07:00 CST 2019, ssex=\'0\', theme=\'green\', avatar=\'default.jpg\', description=\'null\', roleName=\'null\'}\"  org.springframework.validation.BindingResult.user: \"org.springframework.validation.BeanPropertyBindingResult: 0 errors\"', '127.0.0.1', '2019-06-18 16:44:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4561, '123', '访问系统', 1, 'cc.hep.system.controller.LoginController.index()', 'user: \"User{userId=167, username=\'123\', password=\'f898d2cfacab311cba4927173361eb2f\', deptId=17, deptName=\'null\', email=\'2679998262@qq.com\', mobile=\'15170091857\', status=\'1\', crateTime=Tue Jun 11 04:26:20 CST 2019, modifyTime=Tue Jun 18 13:38:58 CST 2019, lastLoginTime=Tue Jun 18 19:07:00 CST 2019, ssex=\'0\', theme=\'green\', avatar=\'default.jpg\', description=\'null\', roleName=\'null\'}\"', '127.0.0.1', '2019-06-18 17:29:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4562, '123', '访问系统', 4, 'cc.hep.system.controller.LoginController$$EnhancerBySpringCGLIB$$a319ae4e.index()', 'user: \"User{userId=167, username=\'123\', password=\'f898d2cfacab311cba4927173361eb2f\', deptId=17, deptName=\'null\', email=\'2679998262@qq.com\', mobile=\'15170091857\', status=\'1\', crateTime=Tue Jun 11 04:26:20 CST 2019, modifyTime=Tue Jun 18 13:38:58 CST 2019, lastLoginTime=Tue Jun 18 19:07:00 CST 2019, ssex=\'0\', theme=\'green\', avatar=\'default.jpg\', description=\'null\', roleName=\'null\'}\"', '127.0.0.1', '2019-06-18 17:51:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4563, '123', '获取在线用户信息', 1, 'cc.hep.system.controller.SessionController.online()', '', '127.0.0.1', '2019-06-18 17:51:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4564, '123', '访问系统', 1, 'cc.hep.system.controller.LoginController$$EnhancerBySpringCGLIB$$42d0dc62.index()', 'user: \"User{userId=167, username=\'123\', password=\'f898d2cfacab311cba4927173361eb2f\', deptId=17, deptName=\'null\', email=\'2679998262@qq.com\', mobile=\'15170091857\', status=\'1\', crateTime=Tue Jun 11 04:26:20 CST 2019, modifyTime=Tue Jun 18 13:38:58 CST 2019, lastLoginTime=Wed Jun 19 00:44:40 CST 2019, ssex=\'0\', theme=\'green\', avatar=\'default.jpg\', description=\'null\', roleName=\'null\'}\"  org.springframework.validation.BindingResult.user: \"org.springframework.validation.BeanPropertyBindingResult: 0 errors\"', '127.0.0.1', '2019-06-18 18:32:58', '内网IP|0|0|内网IP|内网IP');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `PERMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1352 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, '信息管理', '', '', 'zmdi zmdi-settings', '0', 1, '2017-12-27 16:39:07', '2019-06-17 05:39:33');
INSERT INTO `t_menu` VALUES (2, 0, '系统监控', NULL, NULL, 'zmdi zmdi-shield-security', '0', 3, '2017-12-27 16:45:51', '2018-01-17 17:08:28');
INSERT INTO `t_menu` VALUES (3, 1, '人员管理', 'user', 'user:list', '', '0', NULL, '2017-12-27 16:47:13', '2019-06-10 20:23:06');
INSERT INTO `t_menu` VALUES (4, 1, '角色管理', 'role', 'role:list', '', '0', NULL, '2017-12-27 16:48:09', '2018-04-25 09:01:12');
INSERT INTO `t_menu` VALUES (5, 1, '菜单管理', 'menu', 'menu:list', '', '0', NULL, '2017-12-27 16:48:57', '2018-04-25 09:01:30');
INSERT INTO `t_menu` VALUES (6, 1, '部门管理', 'dept', 'dept:list', '', '0', NULL, '2017-12-27 16:57:33', '2018-04-25 09:01:40');
INSERT INTO `t_menu` VALUES (8, 2, '在线用户', 'session', 'session:list', '', '0', NULL, '2017-12-27 16:59:33', '2018-04-25 09:02:04');
INSERT INTO `t_menu` VALUES (10, 2, '系统日志', 'log', 'log:list', '', '0', NULL, '2017-12-27 17:00:50', '2018-04-25 09:02:18');
INSERT INTO `t_menu` VALUES (11, 3, '新增用户', NULL, 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `t_menu` VALUES (12, 3, '修改用户', NULL, 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `t_menu` VALUES (13, 3, '删除用户', NULL, 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `t_menu` VALUES (14, 4, '新增角色', NULL, 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (15, 4, '修改角色', NULL, 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (16, 4, '删除角色', NULL, 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (17, 5, '新增菜单', NULL, 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (18, 5, '修改菜单', NULL, 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (19, 5, '删除菜单', NULL, 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (20, 6, '新增部门', NULL, 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (21, 6, '修改部门', NULL, 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (22, 6, '删除部门', NULL, 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (23, 8, '踢出用户', NULL, 'user:kickout', NULL, '1', NULL, '2017-12-27 17:11:13', NULL);
INSERT INTO `t_menu` VALUES (24, 10, '删除日志', NULL, 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', NULL);
INSERT INTO `t_menu` VALUES (115, 0, '统计分析', '', '', 'zmdi zmdi-hospital-alt', '0', 4, '2019-06-10 19:10:55', '2019-06-10 19:12:19');
INSERT INTO `t_menu` VALUES (116, 115, '发电统计', 'generator/statistics', 'generator:statistics', '', '0', NULL, '2019-06-10 19:13:16', '2019-06-17 18:52:09');
INSERT INTO `t_menu` VALUES (119, 115, '库存统计', 'resources/statistics', 'resources:statistics', '', '0', NULL, '2019-06-10 19:49:08', '2019-06-17 19:31:49');
INSERT INTO `t_menu` VALUES (121, 1, '设备管理', 'equipment', 'equipment:list', '', '0', NULL, '2019-06-10 19:50:45', '2019-06-11 05:47:29');
INSERT INTO `t_menu` VALUES (122, 1, '物资管理', 'resources', 'resources:list', '', '0', NULL, '2019-06-10 19:50:57', '2019-06-16 09:14:09');
INSERT INTO `t_menu` VALUES (123, 1, '库房管理', 'storehouse', 'storehouse:list', '', '0', NULL, '2019-06-10 19:51:06', '2019-06-16 09:43:17');
INSERT INTO `t_menu` VALUES (124, 121, '新增设备', '', 'equipment:add', '', '1', NULL, '2019-06-11 05:42:36', '2019-06-11 05:44:26');
INSERT INTO `t_menu` VALUES (125, 121, '修改设备', '', 'equipment:update', '', '1', NULL, '2019-06-11 05:43:22', '2019-06-11 05:44:41');
INSERT INTO `t_menu` VALUES (126, 121, '删除设备', '', 'equipment:delete', '', '1', NULL, '2019-06-11 05:43:54', NULL);
INSERT INTO `t_menu` VALUES (127, 123, '新增仓库', '', 'storehouse:add', '', '1', NULL, '2019-06-16 07:50:33', NULL);
INSERT INTO `t_menu` VALUES (128, 122, '增加物资', '', 'resources:add', '', '1', NULL, '2019-06-16 07:51:17', '2019-06-16 08:03:33');
INSERT INTO `t_menu` VALUES (129, 122, '修改物资', '', 'resources:update', '', '1', NULL, '2019-06-16 07:53:18', '2019-06-16 08:03:50');
INSERT INTO `t_menu` VALUES (130, 122, '删除物资', '', 'resources:delete', '', '1', NULL, '2019-06-16 07:53:43', '2019-06-16 08:04:14');
INSERT INTO `t_menu` VALUES (131, 123, '修改仓库', '', 'storehouse:update', '', '1', NULL, '2019-06-16 07:54:16', NULL);
INSERT INTO `t_menu` VALUES (132, 123, '删除仓库', '', 'storehouse:delete', '', '1', NULL, '2019-06-16 07:54:44', NULL);
INSERT INTO `t_menu` VALUES (133, 122, '入库', '', 'resources:in', '', '1', NULL, '2019-06-16 17:07:01', NULL);
INSERT INTO `t_menu` VALUES (134, 122, '出库', '', 'resources:out', '', '1', NULL, '2019-06-16 17:07:19', NULL);
INSERT INTO `t_menu` VALUES (135, 0, '出库/入库', '', '', 'zmdi zmdi-truck', '0', 2, '2019-06-17 05:31:06', '2019-06-18 05:14:43');
INSERT INTO `t_menu` VALUES (136, 135, '入库', 'inStorehouse', 'inStorehouse:list', '', '0', NULL, '2019-06-17 05:32:51', '2019-06-17 07:48:51');
INSERT INTO `t_menu` VALUES (137, 135, '出库', 'outStorehouse', 'outStorehouse:list', '', '0', NULL, '2019-06-17 05:33:11', '2019-06-17 07:52:23');
INSERT INTO `t_menu` VALUES (138, 136, '库存入库', '', 'inStorehouse:add', '', '1', NULL, '2019-06-17 05:36:04', '2019-06-17 07:49:02');
INSERT INTO `t_menu` VALUES (139, 137, '库存出库', '', 'outStorehouse:add', '', '1', NULL, '2019-06-17 05:36:23', '2019-06-17 07:49:20');
INSERT INTO `t_menu` VALUES (1351, 2, '发电机监控', 'generator/info', 'generator:list', '', '0', NULL, '2019-06-17 16:32:59', '2019-06-17 17:03:18');

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources`  (
  `resources_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物资序号',
  `resources_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资编号',
  `resources_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资名',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种类',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '单价',
  `weight` double NULL DEFAULT NULL COMMENT '重量',
  `storehouse_id` bigint(20) NOT NULL COMMENT '所属仓库id',
  `REMARKS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`resources_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES (5, '11', '物资1', 'out', 0.00, 1012, 1, '312');
INSERT INTO `t_resources` VALUES (6, '123', '物资2', '五', 13.00, 123, 1, '');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', '管理员', '2019-06-10 20:17:53', '2019-06-17 16:33:19');
INSERT INTO `t_role` VALUES (2, '普通员工', '员工', '2019-06-10 20:17:53', '2019-06-18 05:38:35');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 22);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 23);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 24);
INSERT INTO `t_role_menu` VALUES (1, 116);
INSERT INTO `t_role_menu` VALUES (1, 123);
INSERT INTO `t_role_menu` VALUES (1, 122);
INSERT INTO `t_role_menu` VALUES (1, 119);
INSERT INTO `t_role_menu` VALUES (1, 124);
INSERT INTO `t_role_menu` VALUES (1, 125);
INSERT INTO `t_role_menu` VALUES (1, 126);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 128);
INSERT INTO `t_role_menu` VALUES (1, 129);
INSERT INTO `t_role_menu` VALUES (1, 130);
INSERT INTO `t_role_menu` VALUES (1, 131);
INSERT INTO `t_role_menu` VALUES (1, 132);
INSERT INTO `t_role_menu` VALUES (1, 133);
INSERT INTO `t_role_menu` VALUES (1, 134);
INSERT INTO `t_role_menu` VALUES (1, 135);
INSERT INTO `t_role_menu` VALUES (1, 136);
INSERT INTO `t_role_menu` VALUES (1, 115);
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (1, 137);
INSERT INTO `t_role_menu` VALUES (1, 139);
INSERT INTO `t_role_menu` VALUES (1, 138);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 121);
INSERT INTO `t_role_menu` VALUES (1, 127);
INSERT INTO `t_role_menu` VALUES (1, 1351);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (2, 8);
INSERT INTO `t_role_menu` VALUES (2, 10);
INSERT INTO `t_role_menu` VALUES (2, 127);
INSERT INTO `t_role_menu` VALUES (2, 123);
INSERT INTO `t_role_menu` VALUES (2, 128);
INSERT INTO `t_role_menu` VALUES (2, 133);
INSERT INTO `t_role_menu` VALUES (2, 134);
INSERT INTO `t_role_menu` VALUES (2, 129);
INSERT INTO `t_role_menu` VALUES (2, 124);
INSERT INTO `t_role_menu` VALUES (2, 125);
INSERT INTO `t_role_menu` VALUES (2, 20);
INSERT INTO `t_role_menu` VALUES (2, 21);
INSERT INTO `t_role_menu` VALUES (2, 11);
INSERT INTO `t_role_menu` VALUES (2, 12);
INSERT INTO `t_role_menu` VALUES (2, 115);
INSERT INTO `t_role_menu` VALUES (2, 116);
INSERT INTO `t_role_menu` VALUES (2, 119);
INSERT INTO `t_role_menu` VALUES (2, 135);
INSERT INTO `t_role_menu` VALUES (2, 136);
INSERT INTO `t_role_menu` VALUES (2, 137);
INSERT INTO `t_role_menu` VALUES (2, 138);
INSERT INTO `t_role_menu` VALUES (2, 139);

-- ----------------------------
-- Table structure for t_storehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_storehouse`;
CREATE TABLE `t_storehouse`  (
  `storehouse_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '仓库id',
  `storehouse_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '仓库名',
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '管理员用户id',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `REMARKS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '仓库状态',
  PRIMARY KEY (`storehouse_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_storehouse
-- ----------------------------
INSERT INTO `t_storehouse` VALUES (1, '零件表', 4, '1楼', '123', '1');
INSERT INTO `t_storehouse` VALUES (2, '1', 23, '123', '213', '1');
INSERT INTO `t_storehouse` VALUES (3, '1', 6, '2', '3', '0');
INSERT INTO `t_storehouse` VALUES (5, '111', 168, '222', '333', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `CRATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女',
  `THEME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 170 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (6, 'tester', 'f898d2cfacab311cba4927173361eb2f', 13, 'tester@qq.com', '13888888888', '1', '2019-06-10 20:17:53', '2019-06-10 20:17:43', '2019-06-10 20:17:53', '1', 'teal', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (23, 'scott', 'f898d2cfacab311cba4927173361eb2f', 13, 'scott@qq.com', '15134627380', '1', '2019-06-10 20:17:53', '2019-06-10 20:17:37', '2019-06-10 20:17:53', '0', 'blue-grey', 'default.jpg', '我是scott，嗯嗯');
INSERT INTO `t_user` VALUES (24, 'smith', 'f898d2cfacab311cba4927173361eb2f', 10, 'smith@qq.com', '13364754932', '1', '2019-06-10 20:17:53', '2019-06-10 20:19:14', '2019-06-10 20:17:53', '1', 'teal', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (25, 'allen', 'f898d2cfacab311cba4927173361eb2f', 10, 'allen@qq.com', '13427374857', '0', '2019-06-10 20:17:53', '2019-06-10 20:17:16', '2019-06-10 20:17:53', '1', 'indigo', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (26, 'martin', 'f898d2cfacab311cba4927173361eb2f', 11, 'martin@qq.com', '15562736678', '1', '2019-06-10 20:17:53', '2019-06-10 20:17:11', '2019-06-10 20:17:53', '1', 'teal', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (27, 'ford', 'f898d2cfacab311cba4927173361eb2f', 12, 'ford@qq.com', '15599998373', '0', '2019-06-10 20:17:53', '2019-06-10 20:17:03', '2019-06-10 20:17:53', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (91, '系统监控员', 'f898d2cfacab311cba4927173361eb2f', 13, 'xtjk@qq.com', '18088736652', '1', '2019-06-10 20:17:53', '2019-06-10 20:16:56', '2019-06-10 20:17:53', '0', 'cyan', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (167, '123', 'f898d2cfacab311cba4927173361eb2f', 17, '2679998262@qq.com', '15170091857', '1', '2019-06-10 20:26:20', '2019-06-18 05:38:58', '2019-06-18 18:32:58', '0', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (168, '1234', 'f898d2cfacab311cba4927173361eb2f', 10, '', '', '1', '2019-06-16 11:56:20', NULL, NULL, '2', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (169, 'fuhao', 'f0efca1acd3d52d935a073a175e2d8a6', 9, '123@qq.com', '15170098886', '1', '2019-06-18 05:35:23', NULL, '2019-06-18 12:19:52', '0', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (170, 'qwer', '37dc1cd87a9c3225ed421961c135b97c', 12, '', '', '1', '2019-06-18 11:09:27', NULL, NULL, '2', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (171, '16201320', '05a2f4bfe174f7e2d2066a87cd3f86e2', NULL, NULL, NULL, '1', '2019-06-18 12:48:25', NULL, NULL, '2', 'green', 'default.jpg', NULL);
INSERT INTO `t_user` VALUES (172, 'aaa', 'f13502d39f6e495a2bd5d012deeb0fe5', NULL, NULL, NULL, '1', '2019-06-18 12:48:43', NULL, '2019-06-18 12:48:49', '2', 'green', 'default.jpg', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (23, 2);
INSERT INTO `t_user_role` VALUES (6, 1);
INSERT INTO `t_user_role` VALUES (6, 2);
INSERT INTO `t_user_role` VALUES (168, 1);
INSERT INTO `t_user_role` VALUES (169, 1);
INSERT INTO `t_user_role` VALUES (167, 2);
INSERT INTO `t_user_role` VALUES (170, 1);
INSERT INTO `t_user_role` VALUES (171, 3);
INSERT INTO `t_user_role` VALUES (172, 3);

SET FOREIGN_KEY_CHECKS = 1;
