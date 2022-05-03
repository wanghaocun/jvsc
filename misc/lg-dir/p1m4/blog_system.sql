/*
 Navicat Premium Data Transfer

 Source Server         : Localhost-Docker-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : blog_system

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 03/11/2020 17:40:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章具体内容',
  `created` date NOT NULL COMMENT '发表时间',
  `modified` date NULL DEFAULT NULL COMMENT '修改时间',
  `categories` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '默认分类' COMMENT '文章分类',
  `tags` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '⽂文章标签',
  `allow_comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论',
  `thumbnail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '⽂文章缩略略图',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1, '2019新版Java学习路路线图', 'Java学习路路线图具体内容\r\n具体内容具体内容具体内容具体内容具体内容具体内容', '2019-10-10', NULL, '默认分类', '‘2019,Java,学习路路线图', 1, NULL);
INSERT INTO `t_article` VALUES (2, '2019新版Python学习线路路图', '据悉，Python已经⼊入驻\r\n⼩小学⽣生教材，未来不不学Python不不仅知识会脱节，可能与⼩小朋友都没有了了共同话题~~所以，从今天起不不要再\r\n找借⼝口，不不要再说想学Python却没有资源，赶快⾏行行动起来，Python等你来探索', '2019-10-10', NULL, '默认分类', '‘2019,Java,学习路路线图', 1, NULL);
INSERT INTO `t_article` VALUES (3, 'JDK 8——Lambda表达式介绍', ' Lambda表达式是JDK\r\n8中⼀一个重要的新特性，它使⽤用⼀一个清晰简洁的表达式来表达⼀一个接⼝口，同时Lambda表达式也简化了了对集合\r\n以及数组数据的遍历、过滤和提取等操作。下⾯面，本篇⽂文章就对Lambda表达式进⾏行行简要介绍，并进⾏行行演示\r\n说明', '2019-10-10', NULL, '默认分类', '‘2019,Java,学习路路线图', 1, NULL);
INSERT INTO `t_article` VALUES (4, '函数式接⼝口', '虽然Lambda表达式可以实现匿匿名内部类的\r\n功能，但在使⽤用时却有⼀一个局限，即接⼝口中有且只有⼀一个抽象⽅方法时才能使⽤用Lamdba表达式代替匿匿名内部\r\n类。这是因为Lamdba表达式是基于函数式接⼝口实现的，所谓函数式接⼝口是指有且仅有⼀一个抽象⽅方法的接\r\n⼝口，Lambda表达式就是Java中函数式编程的体现，只有确保接⼝口中有且仅有⼀一个抽象⽅方法，Lambda表达\r\n式才能顺利利地推导出所实现的这个接⼝口中的⽅方法', '2019-10-10', NULL, '默认分类', '‘2019,Java,学习路路线图', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
