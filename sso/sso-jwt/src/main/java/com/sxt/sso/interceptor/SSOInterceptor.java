package com.sxt.sso.interceptor;

import com.sxt.sso.commons.JWTResult;
import com.sxt.sso.commons.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/5 09:46
 * @desc:
 */
public class SSOInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("Authorization");
        JWTResult result = JWTUtils.validateJWT(token);
        if (result.isSuccess()) {
            String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                    result.getClaims().getIssuer(), result.getClaims().getSubject(),
                    10 * 60 * 1000);
            //写回token
            httpServletResponse.setHeader("token",newToken);
            return true;
        }
        dealErrorReturn(httpServletRequest,httpServletResponse,"token失效");
        return false;
    }

    // 检测到没有token，直接返回不验证
    public void dealErrorReturn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object obj){
        String json = (String)obj;
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);

        } catch (IOException ex) {
            System.err.println("response error"+ex);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
