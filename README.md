# 社区医疗预约管理系统

## 项目简介

社区医疗预约管理系统是一个基于Spring Boot的Web应用，用于管理社区医院的预约、用户、医生和科室信息。系统分为管理员后台和用户前台，提供完整的预约管理功能。

## 技术栈

- **后端**：Spring Boot 2.7.15、MyBatis 2.3.1、MySQL 8.0.31
- **前端**：HTML5、Bootstrap 5.3.0、Axios 1.6.2、ECharts 5.4.3
- **开发工具**：IntelliJ IDEA、Maven

## 项目结构

```
community-medical/
├── src/
│   ├── main/
│   │   ├── java/com/medical/
│   │   │   ├── controller/     # 控制器
│   │   │   ├── entity/          # 实体类
│   │   │   ├── mapper/          # 数据访问层
│   │   │   ├── service/         # 业务逻辑层
│   │   │   └── Application.java # 应用主类
│   │   └── resources/
│   │       ├── mapper/          # MyBatis XML文件
│   │       ├── static/          # 静态资源
│   │       ├── application.properties # 应用配置
│   │       └── medical_db.sql   # 数据库脚本
│   └── test/
├── target/                      # 编译输出目录
└── pom.xml                      # Maven配置文件
```

## 核心功能

### 管理员功能
- 登录/登出
- 用户管理（查看、禁用、启用、删除）
- 科室管理（添加、编辑、删除）
- 医生管理（添加、编辑、删除）
- 预约管理（查看、确认）
- 数据统计（预约趋势、科室预约分布）

### 用户功能
- 注册/登录
- 个人信息管理
- 预约医生
- 查看和取消预约

## 快速开始

### 1. 环境准备
- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+

### 2. 数据库配置
1. 创建数据库：`medical_db`
2. 执行SQL脚本：`src/main/resources/medical_db.sql`
3. 修改配置文件：`src/main/resources/application.properties`中的数据库连接信息

### 3. 构建和运行
```bash
# 构建项目
mvn clean package

# 运行项目
java -jar target/community-medical-1.0-SNAPSHOT.jar
```

### 4. 访问地址
- 系统首页：http://localhost:8080/medical
- 管理员登录：http://localhost:8080/medical/admin/login.html
  - 默认账号：admin/123456

## 数据库设计

### 主要表结构
- `user` - 用户表
- `admin` - 管理员表
- `department` - 科室表
- `doctor` - 医生表
- `appointment` - 预约表

## 开发指南

### 代码规范
- 遵循Java编码规范
- 使用Lombok简化实体类代码
- 控制器返回统一的JSON格式

### 接口设计
- 采用RESTful风格
- 统一响应格式：`{"code": 200, "message": "成功", "data": {...}}`

## 注意事项
- 确保数据库连接正确配置
- 登录状态通过Session管理，前端需设置`axios.defaults.withCredentials = true`
- 生产环境需修改默认管理员密码

## 许可证

MIT License
