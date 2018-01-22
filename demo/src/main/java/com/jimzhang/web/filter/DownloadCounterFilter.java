package com.jimzhang.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 下载计数过滤器
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-11 9:34
 */
@WebFilter(urlPatterns = {"/*"})
public class DownloadCounterFilter implements Filter {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Properties downloadLog;
    private File logFile;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String appPath = filterConfig.getServletContext().getRealPath("/");
        logFile = new File(appPath, "downloadLog.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadLog = new Properties();
        try {
            downloadLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String uri = request.getRequestURI();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String value = downloadLog.getProperty(uri);
                if (value == null) {
                    downloadLog.setProperty(uri, "1");
                }else {
                    int count = Integer.parseInt(value);
                    downloadLog.setProperty(uri, String.valueOf(++count));
                }
                try {
                    downloadLog.store(new FileWriter(logFile), "");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
