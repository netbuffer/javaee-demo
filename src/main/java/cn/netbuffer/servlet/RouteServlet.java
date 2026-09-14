package cn.netbuffer.servlet;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.netbuffer.constant.Constant.JSP_SUFFIX;

@WebServlet(name = "route", urlPatterns = "/route")
public class RouteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String route = request.getParameter("route");
        if (StringUtils.isBlank(route)) {
            throw new IllegalArgumentException("route parameter is required");
        }
        System.out.println("goto " + route + JSP_SUFFIX);
        request.getRequestDispatcher("/" + route + JSP_SUFFIX).forward(request, response);
    }
}
