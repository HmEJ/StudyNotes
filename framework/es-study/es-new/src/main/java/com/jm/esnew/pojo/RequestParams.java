package com.jm.esnew.pojo;

import lombok.Data;

/**
 * @Classname RequestParams
 * @Description
 * @Date 2024/1/12 下午6:38
 * @Created by joneelmo
 */
@Data
public class RequestParams {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String city;
    private String brand;
    private String starName;
    private Integer minPrice;
    private Integer maxPrice;
    private String location;
}
