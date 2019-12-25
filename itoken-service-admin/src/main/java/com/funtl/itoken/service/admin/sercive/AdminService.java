package com.funtl.itoken.service.admin.sercive;


import com.funtl.itoken.common.domain.TbSysUser;

public interface AdminService {
    /**
     * 注册
     *
     */
    public void register(TbSysUser tbSysUser);

    /**
     * 登录
     * @param loginCode
     * @param plantPassword
     */

    public TbSysUser login(String loginCode, String plantPassword);
}
