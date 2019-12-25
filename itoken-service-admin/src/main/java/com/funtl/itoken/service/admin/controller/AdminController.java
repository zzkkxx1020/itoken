package com.funtl.itoken.service.admin.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.StringUtils;
import com.funtl.itoken.service.admin.sercive.impl.AdminServiceImpl;
import com.funtl.itoken.common.dto.BaseResult;
import com.google.common.collect.Lists;
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
        //检查登录信息
        BaseResult baseResult = checkLogin(loginCode, password);
        if (baseResult != null){
            return baseResult;
        }
        //登录业务
        TbSysUser tbSysUser = adminService.login(loginCode, password);

        if (tbSysUser!=null){
            //成功
            return BaseResult.ok(tbSysUser);
        }else {
            //失败
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(
                    "","登录失败!!!"
            )));
        }
    }

    /**
     * 检查登录
     * @param loginCode
     * @param password
     * @return
     */
    private BaseResult checkLogin(String loginCode,String password){
        BaseResult baseResult = null;
        if (StringUtils.isBlank(loginCode) || StringUtils.isBlank(password)){
            baseResult = BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error("password","密码不能为空!!!"),
                    new BaseResult.Error("loginCode","登录账户不能为空!!!")
            ));
        }
        return baseResult;
    }
}
