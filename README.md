# Auto Phone 云手机管理系统

一个基于 Spring Boot 3 和火山引擎云手机服务的自动化手机管理系统，主要用于抖音等应用的云手机实例管理。

## 📋 项目简介

Auto Phone 是一个集成了火山引擎云手机服务 SDK 的 Spring Boot 应用程序，提供了云手机实例的查询和管理功能。该项目特别针对抖音等移动应用的自动化操作场景设计。

## 🛠️ 技术栈

- **Java 17** - 编程语言
- **Spring Boot 3.5.6** - 主框架
- **Maven** - 依赖管理和构建工具
- **火山引擎 SDK** - 云手机服务集成
- **SpringDoc OpenAPI** - API 文档生成
- **Lombok** - 代码简化工具

## 🚀 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6+ 
- 火山引擎云手机服务账号

### 1. 克隆项目

```shell
git clone <repository-url>
cd auto-phone
```

### 2. 配置环境变量

在项目根目录创建 `.env` 文件：

```env
VOLC_SDK_ACCESS_KEY=your_access_key
VOLC_SDK_SECRET_KEY=your_secret_key
```

### 3. _编译和运行_

```shell
# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

### 4. 访问应用

启动成功后，可以通过以下地址访问：

- **应用首页**: http://localhost:8080
- **API 文档**: http://localhost:8080/swagger-ui.html

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来改进这个项目。

## 📄 许可证

本项目采用 [MIT 许可证](LICENSE)。

## 📞 联系我们

如果您有任何问题或建议，请通过以下方式联系我们：

- 提交 Issue
- 发送邮件至项目维护者

---

**注意**: 请确保不要将敏感的 API 密钥提交到版本控制系统中。使用环境变量或配置文件来管理这些敏感信息。