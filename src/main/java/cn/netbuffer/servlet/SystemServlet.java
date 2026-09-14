package cn.netbuffer.servlet;

import cn.netbuffer.utils.RequestDump;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "system", urlPatterns = "/system")
public class SystemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("success");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDump.print(request);
        request.setAttribute("str", "hello ' \"<h1>world</h1>");
        request.getRequestDispatcher("/system.jsp").forward(request, response);
    }
}
