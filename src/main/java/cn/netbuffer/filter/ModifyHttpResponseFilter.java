package cn.netbuffer.filter;

import cn.netbuffer.filter.wrapper.ModifyHttpResponseWrapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ModifyHttpResponseFilter", urlPatterns = {"/json/*"})
public class ModifyHttpResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ModifyHttpResponseWrapper modifyHttpResponseWrapper = new ModifyHttpResponseWrapper(httpServletResponse);
        System.out.println("-----------------before ModifyHttpResponseFilter------------------");
        filterChain.doFilter(servletRequest, modifyHttpResponseWrapper);
        System.out.println("-----------------after ModifyHttpResponseFilter------------------");
        JSONObject jsonObject = JSON.parseObject(StringUtils.isBlank(modifyHttpResponseWrapper.getResult()) ? modifyHttpResponseWrapper.getContent() : modifyHttpResponseWrapper.getResult());
        jsonObject.put("msg", "wrapper[" + jsonObject.getString("msg") + "]");
        HttpServletResponse response = (HttpServletResponse) modifyHttpResponseWrapper.getResponse();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toJSONString());
        printWriter.flush();
        printWriter.close();
    }

    @Override
    public void destroy() {
    }

}