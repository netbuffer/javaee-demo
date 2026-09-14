# ☕ javaee-demo

![](https://img.shields.io/static/v1?label=java&message=25&color=blue)
![](https://img.shields.io/static/v1?label=jakartaee&message=11.0&color=blue)
![](https://img.shields.io/static/v1?label=tomcat&message=11&color=blue)
![](https://img.shields.io/static/v1?label=jackson&message=2.22.1&color=blue)
![](https://img.shields.io/static/v1?label=junit&message=5.13.4&color=black)

> 🎯 基于 **Jakarta EE 11** 的演示 Web 应用，用于练习 Servlet、Filter、Listener、JSP/JSTL 等常用能力。

📦 仓库镜像：
- [GitHub](https://github.com/netbuffer/javaee-demo)
- [Gitee](https://gitee.com/netbuffer/javaee-demo)

---

## ✨ 功能特性

| 能力 | 说明 |
|------|------|
| 🔌 Servlet | `/TestServlet`、`/route`、`/json`、`/cors` 等特性演示 |
| 👂 Listener | 通过 `/TestServlet` 观察各类监听器生命周期 |
| 🛡️ Filter | `EncodingFilter` 统一 UTF-8；`ModifyHttpResponseFilter` 改写 `/json` 响应 |
| 🌐 CORS | [Tomcat CorsFilter](https://tomcat.apache.org/tomcat-11.0-doc/config/filter.html#CORS_Filter) 跨域配置 |
| 📄 JSP / JSTL | `index`、`cors`、`cookie`、`session-timeout` 等页面示例 |

> 📍 应用上下文路径为 **`/javaee-demo`**，下文路径均相对于该前缀。  
> 完整示例：`http://localhost:8080/javaee-demo/TestServlet`

---

## 🔗 访问路径

### Servlet

| 路径 | 方法 | 说明 |
|------|------|------|
| `/dispatcher` | GET | `DispatcherServlet`：动态路由演示，回写请求 URI |
| `/TestServlet` | GET / POST | 监听器生命周期演示 |
| `/route?route={name}` | GET | 按参数转发到对应 JSP（如 `route=env` → `/env.jsp`） |
| `/json` | GET | 返回 JSON 示例 |
| `/cors` | GET | 转发到 CORS 演示页 |
| `/cookie` | GET | Cookie 演示（`?delete=true` 过期并清除 Cookie）；POST 返回 `success` |
| `/system` | GET | 请求信息转储并转发到 `system.jsp`；POST 返回 `success` |
| `/post/test` | GET | 转发到 POST 测试页；POST 接收表单参数并返回 `success` |

### JSP 页面

| 路径 | 说明 |
|------|------|
| `/` 或 `/index.jsp` | 欢迎页（`web.xml` welcome-file） |
| `/cors.jsp` | CORS 演示页 |
| `/cookie.jsp` | Cookie 相关页面 |
| `/system.jsp` | 系统 / 请求属性展示 |
| `/post-test.jsp` | POST 表单测试页 |
| `/session-timeout.jsp` | 查看 Session 超时时间（秒） |
| `/env.jsp` | 环境变量 / 系统属性转储 |
| `/escape.jsp` | JSTL `escapeXml` 转义演示 |
| `/trim.jsp` | JSP 空白行裁剪演示 |
| `/content.jsp` | 业务内容页（自动引入 `header.jsp` / `footer.jsp`） |
| `/header.jsp` | 页头片段（供 include） |
| `/footer.jsp` | 页脚片段（供 include） |

### Filter（拦截范围）

| Filter | 拦截路径 | 说明 |
|--------|----------|------|
| `EncodingFilter` | `/*` | 统一请求 / 响应 UTF-8 |
| `ModifyHttpResponseFilter` | `/json` | 包装并改写 JSON 响应中的 `msg` 字段 |
| `CorsFilter`（Tomcat） | `/*` | 允许跨域（`cors.allowed.origins=*`） |
| `RequestDumperFilter`（Tomcat） | `/*` | 请求详情转储到日志 |

---

## 🛠️ 技术栈

| 组件 | 版本 |
|------|------|
| Java | 25 |
| Jakarta EE | 11.0 |
| Servlet 容器 | Tomcat 11 |
| 构建工具 | Maven |
| 打包产物 | `target/javaee-demo.war` |

---

## 🚀 快速开始

### 1️⃣ Maven 本地构建

```bash
mvn clean package
# 产物: target/javaee-demo.war
```

将 WAR 部署到 Tomcat 11，或直接使用下方 Docker 方式运行。

### 2️⃣ Docker Compose（推荐）

> ⚠️ Dockerfile 仅复制已构建的 WAR，请先执行 `mvn clean package`。

```bash
mvn clean package
docker compose up --build

# 后台运行
docker compose up -d --build

# 验证
curl http://localhost:8080/javaee-demo/
```

浏览器访问：http://localhost:8080/javaee-demo/

### 3️⃣ 手动构建镜像

```bash
mvn clean package
docker build -t javawiki/javaee-demo:1.0.0 . --no-cache
docker run -it -p 8080:8080 --name javaee-demo -h javaee-demo --rm javawiki/javaee-demo:1.0.0
```

🐳 镜像地址：[Docker Hub · javawiki/javaee-demo](https://hub.docker.com/r/javawiki/javaee-demo)

---

## 📂 目录结构

```
src/main/java/cn/netbuffer/   # Servlet / Filter / Listener
src/main/java/cn/lcfms/utils/ # 工具类
src/main/webapp/              # JSP 与静态资源
src/main/webapp/WEB-INF/      # web.xml、util.tld
Dockerfile                    # 基于 Tomcat 11 运行 WAR
docker-compose.yml            # 本地容器编排
.github/workflows/            # CI / Docker 发布
```

---

## 🔄 CI/CD

| Workflow | 触发条件 | 用途 |
|----------|----------|------|
| `ci.yml` | push / PR → `master` | Maven 编译打包 |
| `docker.yml` | 打 `v*` 标签或手动触发 | 构建并推送 Docker 镜像 |

推送镜像需配置仓库 Secrets：`DOCKERHUB_USERNAME`、`DOCKERHUB_TOKEN`。
