package com.myhd.service.impl;

import com.myhd.entity.User;
import com.myhd.mapper.UserMapper;
import com.myhd.service.IUserService;
import com.myhd.util.Code;
import com.myhd.util.Result;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JoneElmo
 * @since 2023-10-22
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    private static User nullUser = new User();

    @Override
    @Transactional
    public Result selectUserByPhone(String phone) {
        val user = userMapper.selectByPhone(phone);
        return user == null? Result.ok(Code.FAILURE_STATUS,nullUser,"查询失败"):Result.failure(Code.OK_STATUS,user,"查询成功");
    }
}
