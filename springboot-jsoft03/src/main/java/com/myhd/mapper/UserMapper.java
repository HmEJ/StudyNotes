package com.myhd.mapper;

import com.myhd.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JoneElmo
 * @since 2023-10-22
 */
public interface UserMapper{
    User selectByPhone(String number);
}
