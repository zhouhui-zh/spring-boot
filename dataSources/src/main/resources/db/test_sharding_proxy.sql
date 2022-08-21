CREATE DATABASE test_sharding_proxy ;



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_1
-- ----------------------------
DROP TABLE IF EXISTS `t_user_1`;
CREATE TABLE `t_user_1`  (
                             `id` bigint NOT NULL,
                             `user_id` bigint NOT NULL,
                             `create_date` datetime NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_1
-- ----------------------------
INSERT INTO `t_user_1` VALUES (4, 4, '2022-04-26 14:36:14');

SET FOREIGN_KEY_CHECKS = 1;
