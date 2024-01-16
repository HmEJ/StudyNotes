package com.jm.esnew.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.esnew.pojo.Hotel;
import com.jm.esnew.pojo.PageResult;
import com.jm.esnew.pojo.RequestParams;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname IHotelService
 * @Description
 * @Date 2024/1/12 下午3:28
 * @Created by joneelmo
 */
public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params) throws IOException;

    Map<String, List<String>> filters(RequestParams params) throws IOException;

    List<List<String>> getSuggestions(String key);

    void insertById(Long id);

    void deleteById(Long id);
}
