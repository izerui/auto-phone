# Auto Phone 云手机管理系统

一个基于 Spring Boot 3 和火山引擎云手机服务的自动化手机管理系统，主要用于抖音等应用的云手机实例管理。

## 📋 项目简介

Auto Phone 是一个集成了火山引擎云手机服务 SDK 的 Spring Boot 应用程序，提供了云手机实例的查询和管理功能。该项目特别针对抖音等移动应用的自动化操作场景设计。

## 🚀 快速开始

运行项目:
```
mvn spring-boot:run
# 打开浏览器 http://localhost:8080/swagger-ui.html
```

实时查看云手机屏幕,进入front目录,通过h5启动webrtc视频流查看(token配置参考readme)
```
cd front
npm install
npm run dev
```

下来通过adb连接云手机,并查看dom层次结构,方便开发自动化测试代码
安装adb
```
brew install android-platform-tools
# 测试
adb version
```

创建密钥对并开启adb连接
参考地址: https://www.volcengine.com/docs/6394/1126585

通过adb连接云手机
```
adb connect 101.47.96.169:10010
adb devices
```

查看app的层次结构
```
# 安装 uiautodev
pip3 install -U uiautodev
# 通过adb连接设备后运行
uiauto.dev
```

### 接下来就可以根据uiautodev查看层次结构,编写自动化代码并运行,然后通过h5-webrtc查看实时屏幕

**注意**: 请确保不要将敏感的 API 密钥提交到版本控制系统中。使用环境变量或配置文件来管理这些敏感信息。