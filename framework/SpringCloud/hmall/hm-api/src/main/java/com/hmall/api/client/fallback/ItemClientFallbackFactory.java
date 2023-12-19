package com.hmall.api.client.fallback;

import com.hmall.api.client.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Classname UserClientFallbackFactory
 * @Description
 * @Date 2023/12/17 下午8:50
 * @Created by joneelmo
 */
@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        return new ItemClient() {
            @Override
            public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
                log.error("查询商品异常, 异常原因：",cause);
                return Collections.emptyList();
            }
            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                throw new RuntimeException(cause);
            }
        };
    }
}
