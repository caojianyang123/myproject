-- 创建数据库
CREATE DATABASE IF NOT EXISTS medical_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE medical_db;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `id_card` VARCHAR(20) NOT NULL,
  `status` INT(1) DEFAULT 1 COMMENT '1-正常 0-禁用',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 科室表
CREATE TABLE IF NOT EXISTS `department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 医生表
CREATE TABLE IF NOT EXISTS `doctor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `age` INT(3) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `department_id` INT(11) NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 预约表
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `doctor_id` INT(11) NOT NULL,
  `appointment_date` DATE NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `status` INT(1) DEFAULT 0 COMMENT '0-待确认 1-已确认 2-已取消',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 管理员表
CREATE TABLE IF NOT EXISTS `admin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认数据
INSERT INTO `admin` (`username`, `password`, `name`) VALUES ('admin', '123456', '管理员');

INSERT INTO `department` (`name`, `description`) VALUES 
('内科', '负责治疗内脏疾病'),
('外科', '负责手术治疗'),
('儿科', '负责儿童疾病治疗'),
('妇科', '负责女性疾病治疗'),
('眼科', '负责眼部疾病治疗');

INSERT INTO `doctor` (`name`, `gender`, `age`, `title`, `department_id`, `description`) VALUES 
('张医生', '男', 45, '主任医师', 1, '擅长心血管疾病'),
('李医生', '女', 38, '副主任医师', 1, '擅长消化系统疾病'),
('王医生', '男', 50, '主任医师', 2, '擅长骨科手术'),
('赵医生', '女', 35, '主治医师', 3, '擅长儿童常见病'),
('钱医生', '女', 42, '副主任医师', 4, '擅长妇科疾病');
