package cn.netbuffer.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.netbuffer.constant.Constant.JSP_SUFFIX;

@WebServlet(name = "route", urlPatterns = {"/route"})
public class RouteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        if(StringUtils.isBlank(route)){
            throw new IllegalArgumentException("路由不存在");
        }
        System.out.println("goto " + route + JSP_SUFFIX);
        request.getRequestDispatcher("/" + route + JSP_SUFFIX).forward(request, response);
    }
}