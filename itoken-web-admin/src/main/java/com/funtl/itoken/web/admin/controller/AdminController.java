package com.funtl.itoken.web.admin.controller;

import com.funtl.itoken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = {"","login"}, method = RequestMethod.GET)
    public String login(){
        return "index";
    }
}
