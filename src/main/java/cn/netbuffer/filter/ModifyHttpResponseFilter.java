package cn.netbuffer.filter;

import cn.netbuffer.filter.wrapper.ModifyHttpResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ModifyHttpResponseFilter", urlPatterns = "/json")
public class ModifyHttpResponseFilter implements Filter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ModifyHttpResponseWrapper wrapper = new ModifyHttpResponseWrapper(httpServletResponse);
        filterChain.doFilter(servletRequest, wrapper);

        String payload = StringUtils.isBlank(wrapper.getResult()) ? wrapper.getContent() : wrapper.getResult();
        ObjectNode jsonObject = (ObjectNode) OBJECT_MAPPER.readTree(payload);
        JsonNode msgNode = jsonObject.get("msg");
        String msg = msgNode == null || msgNode.isNull() ? "" : msgNode.asText();
        jsonObject.put("msg", "wrapper[" + msg + "]");

        HttpServletResponse response = (HttpServletResponse) wrapper.getResponse();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(OBJECT_MAPPER.writeValueAsString(jsonObject));
        printWriter.flush();
    }
}
