package com.myhd.service;


import com.myhd.entity.User;
import com.myhd.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JoneElmo
 * @since 2023-10-22
 */
public interface IUserService{
    Result selectUserByPhone(String phone);
}
