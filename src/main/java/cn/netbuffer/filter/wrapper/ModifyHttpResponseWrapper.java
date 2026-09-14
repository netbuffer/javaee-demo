package cn.netbuffer.filter.wrapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ModifyHttpResponseWrapper extends HttpServletResponseWrapper {

    private CharArrayWriter bufferWriter;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private HttpServletResponse httpServletResponse;

    public ModifyHttpResponseWrapper(HttpServletResponse response) {
        super(response);
        bufferWriter = new CharArrayWriter();
        httpServletResponse = response;
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStreamWrapper(this.byteArrayOutputStream, this.httpServletResponse);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(bufferWriter);
    }

    public String getResult() {
        return bufferWriter.toString();
    }

    public String getContent() {
        return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    }

    private class ServletOutputStreamWrapper extends ServletOutputStream {

        private ByteArrayOutputStream outputStream;
        private HttpServletResponse response;

        public ServletOutputStreamWrapper(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response) {
            this.outputStream = byteArrayOutputStream;
            this.response = response;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }

        @Override
        public void write(int b) throws IOException {
            this.outputStream.write(b);
        }

        @Override
        public void flush() throws IOException {
            if (!this.response.isCommitted()) {
                byte[] body = this.outputStream.toByteArray();
                ServletOutputStream outputStream = this.response.getOutputStream();
                outputStream.write(body);
                outputStream.flush();
            }
        }
    }
}