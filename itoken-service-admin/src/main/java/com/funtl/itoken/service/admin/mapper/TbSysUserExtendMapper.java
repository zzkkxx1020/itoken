package com.funtl.itoken.service.admin.Mapper;

import com.funtl.itoken.common.domain.TbSysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
public interface TbSysUserExtendMapper extends MyMapper<TbSysUser> {
}