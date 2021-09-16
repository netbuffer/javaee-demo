package cn.netbuffer.servlet;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jsonServlet", urlPatterns = {"/json"})
public class JSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject data = new JSONObject();
        data.put("success", true);
        data.put("msg", "invoke success for " + this.getClass().getName());
        System.out.println("get json=" + data);
        response.getWriter().write(data.toString());
    }

}
