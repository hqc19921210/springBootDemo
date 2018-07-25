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

-- 导出 ljxt 的数据库结构
CREATE DATABASE IF NOT EXISTS `ljxt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ljxt`;


-- 导出  表 ljxt.equipment 结构
CREATE TABLE IF NOT EXISTS `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` varchar(32) NOT NULL DEFAULT '0' COMMENT '杆塔ID',
  `e_type` varchar(50) DEFAULT NULL COMMENT '设备种类',
  `amount` int(11) DEFAULT '0' COMMENT '设备数量',
  `e_range` varchar(50) DEFAULT NULL COMMENT '测量范围',
  `total` int(11) DEFAULT '0' COMMENT '累计总数',
  `alarms` int(11) DEFAULT '0' COMMENT '报警次数',
  `e_status` varchar(2) DEFAULT NULL COMMENT '状态 N:nomaul',
  `online_time` datetime DEFAULT NULL COMMENT '上线时间',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `own_id` int(11) NOT NULL DEFAULT '0',
  `status` varchar(2) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `eid` (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='设备表';

-- 正在导出表  ljxt.equipment 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` (`id`, `eid`, `e_type`, `amount`, `e_range`, `total`, `alarms`, `e_status`, `online_time`, `remark`, `own_id`, `status`, `create_time`, `update_time`, `update_uid`) VALUES
	(1, '1807001000000001', NULL, 1, '100KA', 0, 0, 'N', '2018-07-24 22:31:16', NULL, 17, 'Y', '2018-07-24 22:28:13', NULL, NULL),
	(2, '1807001000000002', '无', NULL, '1000KA', NULL, NULL, 'N', '2018-07-25 17:17:47', NULL, 18, 'Y', '2018-07-25 17:17:47', NULL, 1),
	(3, '1807001000000003', '无', 11, '10KA', NULL, NULL, 'N', '2018-07-25 17:24:01', NULL, 16, 'N', '2018-07-25 17:24:01', '2018-07-25 17:44:35', 1);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
