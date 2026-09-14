package cn.netbuffer.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;

@WebListener
public class MySessionIdListener implements HttpSessionIdListener {

    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        System.out.println("MySessionIdListener.sessionIdChanged: new="
                + event.getSession().getId() + ", old=" + oldSessionId);
    }
}
