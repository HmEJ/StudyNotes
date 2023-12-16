package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Classname TradeClient
 * @Description
 * @Date 2023/12/14 上午10:28
 * @Created by joneelmo
 */
@FeignClient("trade-service")
public interface TradeClient {
    @PutMapping("/orders")
    void markOrderPaySuccess(@PathVariable("orderId") Long orderId);
}
