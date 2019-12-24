package com.funtl.itoken.service.admin.controller;

import com.funtl.itoken.common.utils.StringUtils;
import com.funtl.itoken.service.admin.domain.TbSysUser;
import com.funtl.itoken.service.admin.sercive.impl.AdminServiceImpl;
import com.funtl.itoken.common.dto.BaseResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public BaseResult login(String loginCode,String password){
        TbSysUser tbSysUser = adminService.login(loginCode, password);
        //成功
        if (tbSysUser!=null){
            return BaseResult.ok(tbSysUser);
        }else {
            //return BaseResult.notOk()
        }
        //失败

        return null;
    }

    private BaseResult checkLogin(String loginCode,String password){
        BaseResult baseResult = null;
        ArrayList<BaseResult.Error> errors = new ArrayList<>();
        if (StringUtils.isBlank(loginCode)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("loginCode");
            error.setMessage("登录账户不能为空!!!");
            errors.add(error);
        }
        if (StringUtils.isBlank(password)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("password");
            error.setMessage("密码不能为空!!!");
            errors.add(error);
        }
        return baseResult;
    }
}
