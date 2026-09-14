package cn.netbuffer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("success");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("true".equalsIgnoreCase(request.getParameter("delete"))) {
            expireCookies(request, response);
        }
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    private void expireCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            System.out.println("expire cookie: " + cookie.getName() + "=" + cookie.getValue());
            Cookie expired = new Cookie(cookie.getName(), "");
            expired.setPath("/");
            expired.setMaxAge(0);
            response.addCookie(expired);
        }
    }
}
