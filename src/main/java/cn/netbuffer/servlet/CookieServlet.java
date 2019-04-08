package cn.netbuffer.servlet;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("success");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("true".equalsIgnoreCase(request.getParameter("delete"))) {
            printCookie(request);
//            删除cookie需要path,value匹配cookie信息才能将对应的删除掉
//            for (Cookie cookie : cookies) {
//                cookie.setValue("");
//                cookie.setPath("/");
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
        }
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    private void printCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(ToStringBuilder.reflectionToString(cookie));
        }
    }
}