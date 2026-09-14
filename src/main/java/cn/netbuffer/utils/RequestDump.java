package cn.netbuffer.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 将请求关键信息打印到容器日志，供演示与排查使用。
 */
public final class RequestDump {

    private RequestDump() {
    }

    public static void print(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("request{\n");
        appendSection(sb, "parameters", formatParameters(request.getParameterMap()));
        appendSection(sb, "cookies", formatCookies(request.getCookies()));
        appendSection(sb, "session", formatSession(request.getSession(false)));
        appendSection(sb, "headers", formatHeaders(request));
        sb.append('}');
        System.out.println(sb);
    }

    private static void appendSection(StringBuilder sb, String name, String body) {
        sb.append("  ").append(name).append('=').append(body).append('\n');
    }

    private static String formatParameters(Map<String, String[]> params) {
        if (params == null || params.isEmpty()) {
            return "{}";
        }
        return params.entrySet().stream()
                .map(e -> e.getKey() + '=' + Arrays.toString(e.getValue()))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    private static String formatCookies(Cookie[] cookies) {
        if (cookies == null || cookies.length == 0) {
            return "[]";
        }
        return Arrays.stream(cookies)
                .map(c -> c.getName() + '=' + c.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    private static String formatSession(HttpSession session) {
        if (session == null) {
            return "null";
        }
        String attrs = Collections.list(session.getAttributeNames()).stream()
                .map(name -> name + '=' + session.getAttribute(name))
                .collect(Collectors.joining(", "));
        return "{id=" + session.getId() + (attrs.isEmpty() ? "" : ", " + attrs) + '}';
    }

    private static String formatHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(name -> name + '=' + request.getHeader(name))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
