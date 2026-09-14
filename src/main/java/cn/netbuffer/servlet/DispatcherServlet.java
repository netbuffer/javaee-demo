package cn.netbuffer.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 动态路由演示。不要映射 {@code /}，该路径留给容器 DefaultServlet / welcome-file。
 */
@WebServlet(name = "dispatcher", urlPatterns = "/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        System.out.println("dispatcher demo: uri=" + uri);
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write(uri);
    }
}
