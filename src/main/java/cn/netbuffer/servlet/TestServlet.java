package cn.netbuffer.servlet;

import cn.netbuffer.listener.SessionBoundAttribute;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 触发各类 Listener 的演示入口，结果输出到容器日志。
 */
@WebServlet(urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Servlet Event Listeners</title></head><body>");
        out.println("<h1>Servlet Event Listeners</h1>");

        out.println("<h2>ServletContext attributes</h2>");
        request.getServletContext().setAttribute("attribute1", "attribute-value1");
        request.getServletContext().setAttribute("attribute1", "attribute-updated-value1");
        request.getServletContext().removeAttribute("attribute1");
        out.println("done");

        out.println("<h2>HttpSession attributes</h2>");
        request.getSession(true).setAttribute("attribute1", "attribute-value1");
        request.getSession().setAttribute("attribute1", "attribute-updated-value1");
        request.getSession().removeAttribute("attribute1");
        out.println("done");

        out.println("<h2>HttpSessionBindingListener attribute</h2>");
        request.getSession().setAttribute("bound", new SessionBoundAttribute("demo"));
        request.getSession().removeAttribute("bound");
        out.println("done");

        out.println("<h2>ServletRequest attributes</h2>");
        request.setAttribute("attribute1", "attribute-value1");
        request.setAttribute("attribute1", "attribute-updated-value1");
        request.removeAttribute("attribute1");
        out.println("done");

        out.println("<h2>Invalidate session</h2>");
        request.getSession().invalidate();
        out.println("done");

        out.println("<p>Check output in server log</p>");
        out.println("</body></html>");
    }
}
