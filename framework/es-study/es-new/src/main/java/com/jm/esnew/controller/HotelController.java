package com.jm.esnew.controller;

import com.jm.esnew.pojo.PageResult;
import com.jm.esnew.pojo.RequestParams;
import com.jm.esnew.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname HotelController
 * @Description
 * @Date 2024/1/12 下午6:38
 * @Created by joneelmo
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @PostMapping("/list")
    public PageResult search(@RequestBody RequestParams params) throws IOException {
        return hotelService.search(params);
    }

    @PostMapping("/filters")
    public Map<String, List<String>> getFilters(@RequestBody RequestParams params) throws IOException {
        return hotelService.filters(params);
    }

    @GetMapping("/suggestion")
    public List<List<String>> getSuggestions(@RequestParam String key){
        return hotelService.getSuggestions(key);
    }


}
