package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname SetmealDishMapper
 * @Description
 * @Date 2024/1/29 下午7:59
 * @Created by joneelmo
 */
@Mapper
public interface SetMealDishMapper {
    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);
}
