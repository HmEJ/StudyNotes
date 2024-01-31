package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ShopController
 * @Description
 * @Date 2024/1/29 下午11:16
 * @Created by joneelmo
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 设置营业状态 : 0-打烊  1-营业
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result updateStatus(@PathVariable Integer status){
        log.info("设置营业状态为:{}", status==1 ? "营业中":"打烊");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    /**
     * 获取营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        Integer result = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取到店铺营业状态为:{}",result == 1 ? "营业中":"打烊");
        return Result.success(result);
    }

}
