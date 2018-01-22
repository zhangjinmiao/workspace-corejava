package com.jimzhang.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 会话监听器，在用户会话创建和销毁的时候根据情况修改onLineCount和maxOnLineCount的值
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-11 10:15
 */
@WebListener
public class MaxCountListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        int count = Integer.parseInt(ctx.getAttribute("onLineCount").toString());
        count++;
        ctx.setAttribute("onLineCount", count);
        int maxOnLineCount = Integer.parseInt(ctx.getAttribute("maxOnLineCount").toString());
        if (count > maxOnLineCount) {
            ctx.setAttribute("maxOnLineCount", count);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate date = LocalDate.now();
            ctx.setAttribute("date", date.format(formatter));
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext app = se.getSession().getServletContext();
        int onLineCount = Integer.parseInt(app.getAttribute("onLineCount").toString());
        onLineCount--;
        app.setAttribute("onLineCount", onLineCount);
    }
}
