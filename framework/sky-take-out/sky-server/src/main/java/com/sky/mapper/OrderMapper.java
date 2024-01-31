package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Classname OrderMapper
 * @Description
 * @Date 2024/1/30 下午10:44
 * @Created by joneelmo
 */
@Mapper
public interface OrderMapper {

    public Double sumByMap(Map map);
}
