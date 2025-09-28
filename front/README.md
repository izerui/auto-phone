# 云手机 WebSDK Quick Start

本项目是火山引擎云手机 Web SDK 的 Quick Start Example，借助 Web SDK 实现了以下功能，方便用户快速接入云手机 Web SDK。

1. 启动云手机和停止云手机
2. 旋转屏幕
3. 屏幕截图
4. 屏幕录制
5. 服务回收设置
6. 剪贴板同步
7. 摄像头注入
8. 操控权限配置等

> 注意：本项目中有较多链接会跳转到云手机火山引擎文档中心，如果跳转到火山引擎文档中心没有显示 SDK 下载链接或 SDK 接口文档，需要先使用已经开通云手机服务的账号登录火山引擎官网

## 目录结构

```js
src
├── config.js // 导出 Web SDK 需要的配置
├── index.html // 页面布局
├── libs // Web SDK 资源包所在的目录
├── features // Web SDK 的一些功能代码片段
├── .env // Web SDK 启动需要的配置
├── main.js // 主要的实现逻辑
└── utils.js // 工具函数
```

## 运行示例程序

> 首先需要确保拥有 `node` 环境，如果没有，请前往 [nodejs 官网](https://nodejs.org/zh-cn/download)下载并安装 `nodejs`

1. 下载项目到本地

```bash
git clone https://github.com/volcengine/vePhone.git
```

2. 进入 Web Quick Start 目录

```bash
cd vePhone/Quick Start/Web
```

3. 前往[云手机火山官网文档中心](!https://www.volcengine.com/docs/6394/1274174)下载 sdk 最新版本，将 sdk 文件重命名为 `vephone-sdk.min.js` 放到 `src/libs` 目录下。
注：如果点击上面链接没有看到下载 sdk 文件的位置，请确保登录火山账号，且账号已经加白云手机


4. 安装依赖

```bash
npm install
```

5. 在 src 目录的`.env`文件，填写启动云手机需要的配置，配置如下：

```bash
# init config
VEPHONE_ACCOUNT_ID="2105954233" # 火山引擎用户账号，可通过火山引擎官网页面右上角 用户 > 账号管理 > 主账号信息 获取

# start config
VEPHONE_PRODUCT_ID="1965298718945054720" # 云手机业务 ID，可通过火山引擎云手机控制台『业务管理』页面获取
VEPHONE_POD_ID="7547992861502544679" # 实例 ID，可通过火山引擎云手机控制台『实例管理』页面获取

# start token 启动云手机的令牌（通过调用服务端 STSToken 接口获取），有关服务端 STSToken 接口的详细信息，参考 [签发临时 Token](https://www.volcengine.com/docs/6394/75752)
 # Token 创建时间
VEPHONE_TOKEN_CURRENT_TIME="2025-09-11T09:14:00+08:00"
 #Token 过期时间
VEPHONE_TOKEN_EXPIRED_TIME="2025-09-12T09:14:00+08:00"
# 用于鉴权的临时 Token
VEPHONE_TOKEN_SESSION_TOKEN="STS2eyJMVEFjY2Vzc0tleUlEIjoiQUtMVFl6Sm1OVGxqWkRCaE4ySXpORE5oWkRnNU1tUTJOemc0T1dRNVltUXpZemMiLCJBY2Nlc3NLZXlJRCI6IkFLVFBORGRpTmpGa1pEWmhZbVExTkdObU1EZ3paRE14Tm1Jek9UVmpORGd3WkdVIiwiU2lnbmVkU2VjcmV0QWNjZXNzS2V5IjoiaFA3RmdHN09NeEpMM1VtU0d1WFJnZ3Z6ZmVUYU1Xa05KY3IydXgxSWRQUGduWXo1RDVmeUkzSFRQQUJwMU5YeWhhYzcvMVZCbVBzQS9WdSt6Wm5SUm1rZHdGd3k3cEg1R2twRVdyeWg3NVU9IiwiRXhwaXJlZFRpbWUiOjE3NTc2Mzk2NDAsIlBvbGljeVN0cmluZyI6IntcIlN0YXRlbWVudFwiOlt7XCJFZmZlY3RcIjpcIkFsbG93XCIsXCJBY3Rpb25cIjpbXCJBQ0VQOipcIl0sXCJSZXNvdXJjZVwiOltcIipcIl0sXCJDb25kaXRpb25cIjpcIm51bGxcIn1dfSIsIlNpZ25hdHVyZSI6ImI0OWQ3OGEyMjM1YTY1Mzc4YzI0YTU2N2JjNjQzMjljN2ZjOGUyZWQxNmVmNTllNGYyYjJlN2YxOGU2ZjAxZGMifQ=="
# 用于鉴权的临时 Access Key
VEPHONE_TOKEN_ACCESS_KEY_ID="xxx"
# 用于鉴权的临时 Secret Key
VEPHONE_TOKEN_SECRET_ACCESS_KEY="xxxx+ZV3Am6AKSZr+R2EL8u"

```

6. 执行启动命令

```bash
npm run dev
```

7. 打开浏览器，访问 `localhost:8080` 即可

