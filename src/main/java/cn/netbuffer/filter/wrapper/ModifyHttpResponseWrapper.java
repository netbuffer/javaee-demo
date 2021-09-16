package cn.netbuffer.filter.wrapper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifyHttpResponseWrapper extends HttpServletResponseWrapper {

    private PrintWriter cacheWriter;
    private CharArrayWriter bufferWriter;

    public ModifyHttpResponseWrapper(HttpServletResponse response) {
        super(response);
        bufferWriter = new CharArrayWriter();
        cacheWriter = new PrintWriter(bufferWriter);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return cacheWriter;
    }

    /**
     * 获取原始的HTML页面内容。
     *
     * @return
     */
    public String getResult() {
        return bufferWriter.toString();
    }

    @Override
    public void setContentType(String type) {
        super.setContentType(type);
    }

}