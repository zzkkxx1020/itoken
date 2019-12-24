package com.funtl.itoken.service.admin.sercive.impl;

import com.funtl.itoken.service.admin.domain.TbSysUser;
import com.funtl.itoken.service.admin.mapper.TbSysUserMapper;
import com.funtl.itoken.service.admin.sercive.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;


    @Override
    public void register(TbSysUser tbSysUser) {
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));
        tbSysUserMapper.insert(tbSysUser);

    }

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode",loginCode);
        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
        String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
        if (password.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
        return null;
    }
}
