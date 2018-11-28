package com.sxt.sso.filter;

import com.sxt.sso.commons.JWTResponseData;
import com.sxt.sso.commons.JWTResult;
import com.sxt.sso.commons.JWTUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/28 13:44
 * @desc:
 */
public class SSOFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String uri = httpServletRequest.getRequestURI();
        if (uri.endsWith("/login.do") || uri.endsWith("/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = httpServletRequest.getHeader("Authorization");
            JWTResult result = JWTUtils.validateJWT(token);
            if (result.isSuccess()) {
//                //对response做处理
//                String newToken = JWTUtils.createJWT(result.getClaims().getId(),
//                        result.getClaims().getIssuer(), result.getClaims().getSubject(),
//                        10*60*1000);
//                //把新token传给request？？？
//                httpServletRequest.setAttribute("Authorization",token);
//
                ResponseWrapper wrapper = new ResponseWrapper(httpServletResponse);
                filterChain.doFilter(servletRequest, wrapper);
                //对response做处理
                String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                        result.getClaims().getIssuer(), result.getClaims().getSubject(),
                        10 * 60 * 1000);
                //TODO  怎么把返回写到response

                byte[] content = wrapper.getContent();//获取返回值
                String s = new String(content);
                System.out.println("filter:" + s);
                //TODO  写不到前台
                servletResponse.getOutputStream().write((s + " " + newToken).getBytes());
                // httpServletResponse.setHeader("Authorization",newToken);
            } else {
                httpServletResponse.sendRedirect("/");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
