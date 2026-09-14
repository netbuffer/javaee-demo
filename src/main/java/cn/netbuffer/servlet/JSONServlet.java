package cn.netbuffer.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jsonServlet", urlPatterns = "/json")
public class JSONServlet extends HttpServlet {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectNode data = OBJECT_MAPPER.createObjectNode();
        data.put("success", true);
        data.put("msg", "invoke success for " + getClass().getName());
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(OBJECT_MAPPER.writeValueAsString(data));
    }
}
