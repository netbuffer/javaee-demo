# AGENTS.md

AI agent guidance for the **javaee-demo** repository.

## Project overview

Jakarta EE 11 demo web application packaged as a WAR and deployed on Tomcat 11. Used to exercise servlets, filters, listeners, JSP/JSTL, and related Java EE features.

| Item | Value |
|------|-------|
| Group / artifact | `cn.netbuffer` / `javaee-demo` |
| Packaging | WAR (`target/javaee-demo.war`) |
| Java | 25 |
| Jakarta EE | 11.0 |
| Servlet container | Tomcat 11 |
| Build tool | Maven |

## Repository layout

```
src/main/java/cn/netbuffer/   # Servlets, filters, listeners
src/main/java/cn/netbuffer/utils/ # Shared utilities (e.g. RequestDump)
src/main/webapp/              # JSP pages and static assets
src/main/webapp/WEB-INF/      # web.xml, util.tld
pom.xml                       # Dependencies and build config
Dockerfile                    # Copies local WAR into Tomcat runtime
docker-compose.yml            # Local container orchestration
```

## Build and run

### Maven (local)

```bash
mvn clean package
# WAR output: target/javaee-demo.war
```

Deploy the WAR to a Tomcat 11 instance, or use Docker below.

### Docker

Dockerfile only copies the pre-built `target/javaee-demo.war` — package locally first:

```bash
mvn clean package
docker compose up --build

# Background
docker compose up -d --build

# Verify
curl http://localhost:8080/javaee-demo/
```

Published image: `javawiki/javaee-demo` on Docker Hub.

## Coding conventions

- **Package**: `cn.netbuffer` for application code; keep new classes in the matching subpackage (`servlet`, `filter`, `listener`, etc.).
- **Servlet registration**: Prefer `@WebServlet` / `@WebFilter` annotations where applicable; `web.xml` is used for Tomcat built-in filters (CORS, RequestDumper) and JSP config.
- **Dependencies**: Jakarta APIs are `provided` scope (supplied by Tomcat). Do not bundle servlet container libraries into the WAR.
- **Encoding**: Use UTF-8; `EncodingFilter` handles request/response encoding.
- **Minimal diffs**: Match existing style (Oracle/GlassFish license headers on some legacy files, straightforward servlet code elsewhere).

## Key endpoints and features

- `/TestServlet` — listener lifecycle demo
- `/dispatcher`, `/route`, `/json`, `/cors`, etc. — feature-specific demos
- JSP pages under `src/main/webapp/` (index, cors, cookie, session-timeout, …)
- `ModifyHttpResponseFilter` — custom response modification via wrapper (`/json`)

## CI/CD

GitHub Actions workflows in `.github/workflows/`:

| Workflow | Trigger | Purpose |
|----------|---------|---------|
| `ci.yml` | push / PR to `master` | Maven compile and package |
| `docker.yml` | push tag `v*` or manual | Build and push Docker image |

Docker publish requires repository secrets:

- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`

## Agent guidelines

1. Read surrounding code before editing; follow existing patterns in the same package.
2. Do not upgrade Java, Jakarta EE, or Tomcat versions unless explicitly requested.
3. Do not commit secrets, credentials, or local IDE config (`.idea/` is gitignored).
4. Prefer focused changes — this is a demo/test project, not a production service.
5. After Java or dependency changes, ensure `mvn clean package` still succeeds.
6. After Docker-related changes, verify `docker compose up --build` still starts and serves `/javaee-demo/`.
