package com.funtl.itoken.service.sso.service;

import com.funtl.itoken.common.domain.TbSysUser;

/**
 * 登录业务
 */
public interface LoginService {

    /**
     * 登录
     * @param loginCode
     * @param plantPassword
     * @return
     */
    public TbSysUser login(String loginCode,String plantPassword);
}
