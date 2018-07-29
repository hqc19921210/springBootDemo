-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.23-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;




-- 导出  表 ljxt.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父客户id',
  `account` varchar(32) NOT NULL DEFAULT '0' COMMENT '账号',
  `password` varchar(32) NOT NULL DEFAULT '0' COMMENT '密码',
  `company` varchar(100) NOT NULL DEFAULT '0' COMMENT '公司名称',
  `contact` varchar(20) NOT NULL DEFAULT '0' COMMENT '联系人',
  `phone` varchar(20) NOT NULL DEFAULT '0' COMMENT '联系电话',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `site` varchar(32) DEFAULT NULL COMMENT '所属站点',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态,，Y有效;N停用',
  `competence` int(2) NOT NULL DEFAULT '0' COMMENT '权限，2最高权限；3客户权限；4普通用户。',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_uid` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `u_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- 正在导出表  ljxt.user 的数据：~18 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `parent_id`, `account`, `password`, `company`, `contact`, `phone`, `fax`, `email`, `site`, `remark`, `status`, `competence`, `create_time`, `update_time`, `update_uid`) VALUES
	(1, NULL, 'admin', 'c4ca4238a0b923820dcc509a6f75849b', '管理员', '管理员', '134', NULL, NULL, NULL, NULL, 'Y', 2, NULL, '2018-07-22 16:01:01', 1),
	(2, 1, '!guest!', 'c4ca4238a0b923820dcc509a6f75849b', '这个是测试', '是是4', '11111111111', NULL, NULL, '1234ssssa', NULL, 'Y', 3, NULL, '2018-07-22 21:35:49', 2),
	(3, 1, 'user001', '202cb962ac59075b964b07152d234b70', '这个是用户', '是是是是', '33333333333', NULL, NULL, NULL, NULL, 'N', 4, NULL, '2018-07-20 11:06:22', 1),
	(4, 1, 'user002', '4eae18cf9e54a0f62b44176d074cbe2f', '这个是用户2', '是是是是', '22222222222', NULL, NULL, NULL, NULL, 'N', 4, NULL, '2018-07-20 11:17:56', 1),
	(5, 1, 'user003', '202cb962ac59075b964b07152d234b70', '这个是用户3', '是是是是', '33333333333', '', '', '', '', 'N', 4, NULL, '2018-07-20 11:14:54', 1),
	(6, 1, 'user001', '202cb962ac59075b964b07152d234b70', '这个是测试1', '是是是是', '11111111111', NULL, NULL, NULL, NULL, 'N', 4, '2018-07-22 15:16:25', '2018-07-22 15:28:47', 1),
	(7, 1, 'user002', '202cb962ac59075b964b07152d234b70', '这个是测试2', '13213', '22222222222', NULL, NULL, NULL, NULL, 'Y', 4, '2018-07-22 15:19:06', NULL, 1),
	(8, 2, 'user001', '202cb962ac59075b964b07152d234b70', '这个是测试1', '13213', '11111111111', NULL, NULL, NULL, NULL, 'Y', 4, '2018-07-22 15:38:30', NULL, 1),
	(9, 1, 'cust001', '202cb962ac59075b964b07152d234b70', '这个是客户1', 'sadasddas', '33333333333', NULL, NULL, NULL, NULL, 'Y', 3, '2018-07-22 16:14:48', NULL, 1),
	(10, 1, 'user003', '202cb962ac59075b964b07152d234b70', '这个是测试3', '13213', '33333333333', '', '', '', '', 'Y', 4, '2018-07-22 16:17:34', NULL, 1),
	(11, 1, 'cust002', 'e10adc3949ba59abbe56e057f20f883e', '这个是客户2', '13213', '22222222222', NULL, NULL, NULL, NULL, 'Y', 3, '2018-07-23 14:14:41', NULL, 1),
	(12, 1, 'cust003', 'e10adc3949ba59abbe56e057f20f883e', '这个是客户3', 'sadasddas', '22222222222', '', '', '', '', 'Y', 3, '2018-07-23 14:16:15', NULL, 1),
	(13, 11, 'user004', 'e10adc3949ba59abbe56e057f20f883e', '这个是测试4', '是是是是', '33333333333', '', '', '', '', 'Y', 4, '2018-07-23 14:17:29', NULL, 1),
	(14, 12, 'user005', 'e10adc3949ba59abbe56e057f20f883e', '这个是测试5', '是是是是', '33333333333', '', '', '', '', 'Y', 4, '2018-07-23 14:19:39', NULL, 1),
	(15, 12, 'user006', 'e10adc3949ba59abbe56e057f20f883e', '这个是测试6', '是是是是', '33333333333', NULL, NULL, NULL, 'cc@##@！！@！', 'Y', 4, '2018-07-23 14:28:31', NULL, 1),
	(16, 1, 'cust004', 'e10adc3949ba59abbe56e057f20f883e', '这个是客户4', '13213', '22222222222', '', '', '', '', 'Y', 3, '2018-07-23 14:29:26', NULL, 1),
	(17, 1, 'cust005', 'e10adc3949ba59abbe56e057f20f883e', '这个是客户5', '13213', '22222222222', '', '', '', '', 'Y', 3, '2018-07-23 14:31:41', NULL, 1),
	(18, 1, 'cust006', 'e10adc3949ba59abbe56e057f20f883e', '这个是客户6', '13213', '22222222222', NULL, NULL, NULL, NULL, 'Y', 3, '2018-07-23 14:33:15', NULL, 1),
	(19, 17, 'user007', 'e10adc3949ba59abbe56e057f20f883e', '这个是测试7', '是是是是', '33333333333', NULL, NULL, NULL, NULL, 'Y', 4, '2018-07-23 14:48:16', NULL, 17);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
