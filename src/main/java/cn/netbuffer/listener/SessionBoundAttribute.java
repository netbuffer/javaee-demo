package cn.netbuffer.listener;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.io.Serial;
import java.io.Serializable;

/**
 * Session 属性级绑定监听示例。须作为 attribute value 放入 HttpSession 才会触发。
 */
public class SessionBoundAttribute implements HttpSessionBindingListener, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String label;

    public SessionBoundAttribute(String label) {
        this.label = label;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("SessionBoundAttribute.valueBound: name=" + event.getName() + ", label=" + label);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("SessionBoundAttribute.valueUnbound: name=" + event.getName() + ", label=" + label);
    }
}
