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

}
