package com.jimzhang.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 编码过滤器
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-11 9:26
 */
@WebFilter(urlPatterns = {"*"}, initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class CodingFilter implements Filter{

    private String defaultEncoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if (encoding != null) {
            defaultEncoding = encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(defaultEncoding);
        servletResponse.setCharacterEncoding(defaultEncoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
