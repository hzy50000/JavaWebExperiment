/*
 Navicat Premium Dump SQL

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : messageboard

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 27/03/2025 10:09:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for messageboard
-- ----------------------------
DROP TABLE IF EXISTS `messageboard`;
CREATE TABLE `messageboard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` text,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of messageboard
-- ----------------------------
BEGIN;
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (10, 'sdhaui\n', 1);
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (11, 'wqewqeweq', 1);
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (12, 'rewewr', 1);
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (13, 'trytrytry', 1);
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (14, '你好\n', 1);
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (15, 'opo', 1);
INSERT INTO `messageboard` (`id`, `message`, `user_id`) VALUES (16, 'jiojio', 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`) VALUES (1, 'user', 'user123');
INSERT INTO `user` (`id`, `username`, `password`) VALUES (2, 'hzy', 'hzy123');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
