/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : financialmanage

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2022-04-06 11:06:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `adminname` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'liming', '932e3833d93fd60b5599961407723433ae8f16792f89f39b');
INSERT INTO `admin` VALUES ('2', 'mzl', '932e3833d93fd60b5599961407723433ae8f16792f89f39b');

-- ----------------------------
-- Table structure for budget
-- ----------------------------
DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget` (
  `wid` int NOT NULL AUTO_INCREMENT,
  `wtime` varchar(200) DEFAULT NULL,
  `wnum` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`wid`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `budget_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of budget
-- ----------------------------
INSERT INTO `budget` VALUES ('3', '2020-07', '16000', '1');
INSERT INTO `budget` VALUES ('5', '2021-03', '500', '1');
INSERT INTO `budget` VALUES ('6', '2022-03', '5000', '1');
INSERT INTO `budget` VALUES ('7', '2022-04', '5000', '1');

-- ----------------------------
-- Table structure for memorandum
-- ----------------------------
DROP TABLE IF EXISTS `memorandum`;
CREATE TABLE `memorandum` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `recordTime` date DEFAULT NULL,
  `thingPath` varchar(200) DEFAULT NULL,
  `topFont` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`mid`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `memorandum_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of memorandum
-- ----------------------------
INSERT INTO `memorandum` VALUES ('3', '2020-07-07', 'd5bfcddb-eD:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\08a-4c3a-a833-ae1291eeb5aa.txt', '我要努力学习，天天向上，追求进步，不断提升自己的专业技术，为以后好好赚钱奠定基础！\r\n', '1');
INSERT INTO `memorandum` VALUES ('7', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\f5cdecc8-2ec5-45b1-bd3a-78613348d585.txt', '追求！\r\n', '1');
INSERT INTO `memorandum` VALUES ('8', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\eb744969-0220-4465-a2fe-76103bc76737.txt', '冲冲冲！\r\n', '1');
INSERT INTO `memorandum` VALUES ('9', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\6533cbee-5dce-46c9-8833-4918f43f2d49.txt', '努力赚钱！冲冲冲！\r\n', '1');
INSERT INTO `memorandum` VALUES ('10', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\c82e82d5-a3b1-4dde-8097-3572d49b96ff.txt', '好好学习！天天向上！不断提升自己的专业技术水平！不断学习新的知识！马振乐，加油！\r\n', '1');
INSERT INTO `memorandum` VALUES ('11', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\e874fc66-3ae0-49c0-9c6f-b79b978b0849.txt', '马振乐会好好学习，以后努力赚钱养宝贝郭倩盈的！臭猪猪！美女！hhhh......哼哼哼\r\n', '1');
INSERT INTO `memorandum` VALUES ('12', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\be84d999-d63d-49c1-bc0b-ad37f79951bc.txt', '没有最好，只有更好！加油，加油！马振乐！\r\n', '1');
INSERT INTO `memorandum` VALUES ('13', '2020-07-07', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\e00eacb9-c98f-4718-b500-dcf5017687bb.txt', '马振乐，加油！加油！加油！努力！\r\n', '1');
INSERT INTO `memorandum` VALUES ('14', '2020-07-11', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\memorandum\\\\9469615f-036d-4412-a02a-808a712fe284.txt', '加油！冲冲冲！对了！\r\n', '1');
INSERT INTO `memorandum` VALUES ('16', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\memorandu\\61f2be2f-fe3c-4206-a24e-756dc166b1de.txt', 'nihao!呵呵呵\r\n', '1');
INSERT INTO `memorandum` VALUES ('17', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\memorandu\\a5f5031a-a8e3-45d9-bc42-a90da95d19b6.txt', 'ddddd哈哈哈哈\r\n', '1');
INSERT INTO `memorandum` VALUES ('18', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\memorandum\\8787621e-3e9b-4d8b-ab3e-5c7fd192e0da.txt', '冲冲冲！！！啊啊啊!\r\n', '1');
INSERT INTO `memorandum` VALUES ('21', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\memorandum\\b00d052c-df6c-46a5-9f00-f198ccdf1469.txt', '加油！冲冲冲！\r\n', '1');
INSERT INTO `memorandum` VALUES ('22', '2021-04-10', 'D:\\Program Files\\MyFilesPractice(own)\\memorandum\\0de940df-e57a-43b8-9753-0f4bb7d6e7a5.txt', '冲冲冲！\r\n', '1');
INSERT INTO `memorandum` VALUES ('23', '2021-04-10', 'D:/Program Files/MyFilesPractice(own)/memorandum\\b264647e-456f-42bb-9cc2-adf322a91350.txt', '5555\r\n', '1');
INSERT INTO `memorandum` VALUES ('24', '2021-04-10', 'D:\\Program Files\\MyFilesPractice(own)\\memorandum/c620c077-97d2-46b6-b0a3-c082f264cc19.txt', '888\r\n', '1');
INSERT INTO `memorandum` VALUES ('25', '2021-04-10', 'D:/Program Files/MyFilesPractice(own)/memorandum/84c5a862-a6d9-4902-8d1f-1446c88a81a0.txt', 'ttt\r\n', '1');
INSERT INTO `memorandum` VALUES ('26', '2021-04-10', 'D:/Program Files/MyFilesPractice(own)/memorandum/a45cd218-ec39-4abc-b56b-c83104f316be.txt', 'rrrrt哈哈哈哈\r\n', '1');
INSERT INTO `memorandum` VALUES ('27', '2022-04-03', 'D:/Program Files/MyFilesPractice(own)/memorandum/42721bb8-993b-4e78-942f-e8ca468cfbd1.txt', '加油\r\n', '1');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `nid` int NOT NULL AUTO_INCREMENT,
  `nTitle` varchar(200) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `keyword` varchar(200) DEFAULT NULL,
  `visitCount` int DEFAULT NULL,
  `recordTime` date DEFAULT NULL,
  `nContent` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '想理财又不懂理财，不妨看看这篇文章', '李民', '理财', '76', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f2.txt');
INSERT INTO `news` VALUES ('2', '想理财又不懂理财，不妨看看这篇文章', '李四', '理财', '60', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f1.txt');
INSERT INTO `news` VALUES ('3', '想理财又不懂理财，不妨看看这篇文章', '赵器', '理财', '42', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f1.txt');
INSERT INTO `news` VALUES ('4', '想理财又不懂理财，不妨看看这篇文章', '锡洪说', '赚钱', '29', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f2.txt');
INSERT INTO `news` VALUES ('5', '想理财又不懂理财，不妨看看这篇文章', '锡洪说', '理财', '23', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f1.txt');
INSERT INTO `news` VALUES ('6', '想理财又不懂理财，不妨看看这篇文章', '锡洪说', '理财', '24', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f1.txt');
INSERT INTO `news` VALUES ('7', '想理财又不懂理财，不妨看看这篇文章', '锡洪说', '理财', '24', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f2.txt');
INSERT INTO `news` VALUES ('8', '想理财又不懂理财，不妨看看这篇文章', '锡洪说', '理财', '21', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f1.txt');
INSERT INTO `news` VALUES ('9', '想理财又不懂理财，不妨看看这篇文章', '锡洪说', '理财', '21', '2020-06-05', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\f1.txt');
INSERT INTO `news` VALUES ('29', '理财好方法', '李四', '智慧', '1', '2020-07-09', 'D:\\\\Program Files\\\\MyFilesPractice(own)\\\\news\\\\acad75bb-7315-4109-81a2-2e5085ff51dc.txt');
INSERT INTO `news` VALUES ('41', '理财', '赵器', '智慧', '0', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\news\\54856a1c-649e-47fa-9c53-2f9bd11efd02.txt');
INSERT INTO `news` VALUES ('42', '理财', '李四', '赚钱', '0', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\news\\606441ae-4647-4c97-85c2-75337303a1a8.txt');
INSERT INTO `news` VALUES ('43', '理财', '赵六', '赚钱', '0', '2020-07-29', 'D:\\Program Files\\MyFilesPractice(own)\\new\\232f069f-e40d-4b0c-a2b1-bb44137adf57.txt');
INSERT INTO `news` VALUES ('44', '理财', '李四', '赚钱', '1', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\news\\e16d77da-fc3c-4efd-937f-86060b47b50c.txt');
INSERT INTO `news` VALUES ('45', '理财好方法', '马振乐', '智慧', '1', '2020-07-12', 'D:\\Program Files\\MyFilesPractice(own)\\news\\e7baa515-4b00-4e75-9a18-5b926df332e5.txt');

-- ----------------------------
-- Table structure for shouzhi_category
-- ----------------------------
DROP TABLE IF EXISTS `shouzhi_category`;
CREATE TABLE `shouzhi_category` (
  `szcid` int NOT NULL AUTO_INCREMENT,
  `parent_category` varchar(200) DEFAULT NULL,
  `son_category` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`szcid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shouzhi_category
-- ----------------------------
INSERT INTO `shouzhi_category` VALUES ('1', '收入', '工资');
INSERT INTO `shouzhi_category` VALUES ('2', '收入', '奖学金');
INSERT INTO `shouzhi_category` VALUES ('3', '支出', '伙食费');
INSERT INTO `shouzhi_category` VALUES ('4', '收入', '比赛奖励');
INSERT INTO `shouzhi_category` VALUES ('5', '支出', '书杂费');
INSERT INTO `shouzhi_category` VALUES ('8', '收入', '奖励');
INSERT INTO `shouzhi_category` VALUES ('11', '支出', '零花钱');
INSERT INTO `shouzhi_category` VALUES ('12', '支出', '公费');
INSERT INTO `shouzhi_category` VALUES ('13', '支出', '其他杂费');
INSERT INTO `shouzhi_category` VALUES ('40', '收入', '兼职');
INSERT INTO `shouzhi_category` VALUES ('41', '支出', '捐款');
INSERT INTO `shouzhi_category` VALUES ('42', '收入', '学费');
INSERT INTO `shouzhi_category` VALUES ('43', '收入', '伙食费1');
INSERT INTO `shouzhi_category` VALUES ('44', '支出', '房租');

-- ----------------------------
-- Table structure for shouzhi_record
-- ----------------------------
DROP TABLE IF EXISTS `shouzhi_record`;
CREATE TABLE `shouzhi_record` (
  `szrid` int NOT NULL AUTO_INCREMENT,
  `szr_num` int DEFAULT NULL,
  `szr_date` date DEFAULT NULL,
  `szr_comment` varchar(200) DEFAULT NULL,
  `shouzhi_category_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`szrid`),
  KEY `user_id` (`user_id`),
  KEY `shouzhi_category_id` (`shouzhi_category_id`),
  CONSTRAINT `shouzhi_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `shouzhi_record_ibfk_2` FOREIGN KEY (`shouzhi_category_id`) REFERENCES `shouzhi_category` (`szcid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shouzhi_record
-- ----------------------------
INSERT INTO `shouzhi_record` VALUES ('1', '-80', '2022-04-03', '早餐', '3', '1');
INSERT INTO `shouzhi_record` VALUES ('3', '1000', '2022-03-05', '大三校奖学金', '2', '1');
INSERT INTO `shouzhi_record` VALUES ('34', '-20', '2022-04-03', '买水果', '3', '1');
INSERT INTO `shouzhi_record` VALUES ('35', '5000', '2022-01-03', '国家励志奖学金(大三)', '1', '1');
INSERT INTO `shouzhi_record` VALUES ('38', '-1700', '2022-03-08', '2022.2房租', '44', '1');
INSERT INTO `shouzhi_record` VALUES ('39', '-700', '2022-01-12', '护肤品', '13', '1');
INSERT INTO `shouzhi_record` VALUES ('40', '9000', '2020-07-03', '月工资', '1', '1');
INSERT INTO `shouzhi_record` VALUES ('41', '800', '2020-07-05', '互联网+比赛', '8', '1');
INSERT INTO `shouzhi_record` VALUES ('42', '1000', '2022-03-16', '校奖学金(大二)', '2', '1');
INSERT INTO `shouzhi_record` VALUES ('43', '1000', '2021-03-04', '软件应用与开发国奖奖金', '4', '1');
INSERT INTO `shouzhi_record` VALUES ('44', '-8000', '2021-02-10', '捐款', '13', '1');
INSERT INTO `shouzhi_record` VALUES ('45', '1000', '2022-04-03', '大二上学期实习工资', '1', '1');
INSERT INTO `shouzhi_record` VALUES ('46', '-300', '2022-03-16', '疫情捐款', '41', '1');
INSERT INTO `shouzhi_record` VALUES ('48', '2000', '2021-03-10', '学费', '2', '24');
INSERT INTO `shouzhi_record` VALUES ('49', '2000', '2021-04-10', '学费', '1', '1');
INSERT INTO `shouzhi_record` VALUES ('50', '-50', '2021-04-07', '淘宝买Java书', '5', '24');
INSERT INTO `shouzhi_record` VALUES ('51', '7000', '2022-02-23', '2022.2工资', '1', '1');
INSERT INTO `shouzhi_record` VALUES ('52', '400', '2022-03-31', '这个月的工资', '1', '27');
INSERT INTO `shouzhi_record` VALUES ('53', '-1000', '2022-03-15', '今天的伙食', '11', '27');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lisi', '562d25f64e7491353ba59f39457c8a40b991b34d5443d04d', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('3', 'mazhenle', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('18', 'wangxi', '41898692308e955f1c253f6892095950047ae9f97602ba71', '女', '2198902814@qq.com', '13675489891');
INSERT INTO `user` VALUES ('19', 'lili', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13675489891');
INSERT INTO `user` VALUES ('24', 'limi', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('27', 'liwu', '795713a0c988d5a204044a1a05188e06a64e526559521b76', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('29', 'liw1', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('30', 'liw2', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('31', 'liw3', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('32', 'liw4', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('33', 'liw5', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');
INSERT INTO `user` VALUES ('34', 'liw6', '41898692308e955f1c253f6892095950047ae9f97602ba71', '男', '2198902814@qq.com', '13652707142');

-- ----------------------------
-- Table structure for wishlist
-- ----------------------------
DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `wid` varchar(200) DEFAULT NULL,
  `wish` varchar(200) DEFAULT NULL,
  `wnum` int DEFAULT NULL,
  `wdate` date DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES ('2', '心愿单2022-03-15-01', '继续赚钱', '6000', '2022-03-15', '已完成', '1');
INSERT INTO `wishlist` VALUES ('3', '心愿单2020-06-16-01', '收多出少', '10000', '2020-06-16', '未完成', '1');
INSERT INTO `wishlist` VALUES ('4', '心愿单2020-07-01-01', '加油！', '2000', '2020-07-01', '已完成', '1');
INSERT INTO `wishlist` VALUES ('5', '心愿单2020-07-01-02', '努力奋斗！', '2000', '2020-07-01', '未完成', '1');
INSERT INTO `wishlist` VALUES ('7', '心愿单2020-07-02-01', '向高处走', '8888', '2020-07-02', '未完成', '1');
INSERT INTO `wishlist` VALUES ('8', '心愿单2022-01-03-01', '努力向前走！', '1000', '2022-01-03', '未完成', '1');
INSERT INTO `wishlist` VALUES ('9', '心愿单2021-07-07-01', '我一定能行的！努力赚钱去！加油！向年薪超30万出发！', '20000', '2021-07-07', '未完成', '1');
INSERT INTO `wishlist` VALUES ('11', '心愿单2022-07-12-03', '努力！', '6000', '2022-07-12', '已完成', '1');
INSERT INTO `wishlist` VALUES ('12', '心愿单2022-07-12-03', '加油！', '5000', '2022-07-12', '已完成', '1');
INSERT INTO `wishlist` VALUES ('13', '心愿单2022-02-07-01', '加油！！！', '4000', '2022-02-07', '未完成', '1');
