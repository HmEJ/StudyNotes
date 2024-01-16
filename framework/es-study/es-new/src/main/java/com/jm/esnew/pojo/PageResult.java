package com.jm.esnew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname PageResult
 * @Description
 * @Date 2024/1/12 下午6:39
 * @Created by joneelmo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;
}
