package com.funtl.itoken.service.sso.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.common.utils.StringUtils;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LogonController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login( @RequestParam(required = false)String url,Model model,HttpServletRequest request, HttpServletResponse response){
        String loginCode = CookieUtils.getCookieValue(request, "token");
        //token 不为空
        if (StringUtils.isNotBlank(loginCode)){
            String json = redisService.get(loginCode);
            if (StringUtils.isNotBlank(json)){
                try {
                    TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                    //以登录
                    if (tbSysUser!=null && StringUtils.isNotBlank(url)){
                        return "redirect:"+url;
                    }
                    model.addAttribute("tbSysUser",tbSysUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "login";
    }

    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(RedirectAttributes redirectAttributes, @RequestParam(required = false) String loginCode,
                        @RequestParam(required = false) String password,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request, HttpServletResponse response){
        TbSysUser tbSysUser = loginService.login(loginCode, password);
        if (tbSysUser == null){
            //登录失败
            redirectAttributes.addFlashAttribute("massage","用户名或密码错误请从新输入");
        }else {
            //登录成功
            String token = UUID.randomUUID().toString();
            String put = redisService.put(token, loginCode, 60 * 60 * 24);
            if (StringUtils.isNotBlank(put) && "ok".equals(put)) {
                //将token放入缓存
                CookieUtils.setCookie(request, response, "token", token);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }
            }else {
                //熔断处理
                redirectAttributes.addFlashAttribute("massage","服务器异常");
            }
        }
        return "redirect:/login ";
    }
}
