package com.funtl.itoken.web.admin.interceptor;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.common.utils.StringUtils;
import com.funtl.itoken.web.admin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录时的拦截器
 */
public class WebAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //token 为空  没有登录
        if (StringUtils.isBlank(token)){
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser)session.getAttribute("tbSysUser");
        //已登录状态
        if (tbSysUser!=null && modelAndView !=null){
            modelAndView.addObject("tbSysUser",tbSysUser);
        }else {
            String token = CookieUtils.getCookieValue(request, "token");
            if (StringUtils.isNotBlank(token)) {
                String loginCode = redisService.get(token);
                if (StringUtils.isNotBlank(loginCode)){
                    String json = redisService.get(loginCode);
                    if (StringUtils.isNotBlank(json)){
                        //有登陆

                        tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if (modelAndView != null){
                            modelAndView.addObject("tbSysUser",tbSysUser);
                        }
                        request.getSession().setAttribute("tbSysUser",tbSysUser);
                    }
                }
            }
        }

        if (tbSysUser==null){
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
