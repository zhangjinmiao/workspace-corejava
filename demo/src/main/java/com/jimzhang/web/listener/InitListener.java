package com.jimzhang.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 上下文监听器，在服务器启动时初始化onLineCount和maxOnLineCount两个变量  并将其置于服务器上下文（ServletContext）中，其初始值都是0
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-11 10:04
 */
@WebListener
public class InitListener implements ServletContextListener{
    @Override
    public void contextDestroyed(ServletContextEvent evt) {
    }

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        evt.getServletContext().setAttribute("onLineCount", 0);
        evt.getServletContext().setAttribute("maxOnLineCount", 0);
    }

}
