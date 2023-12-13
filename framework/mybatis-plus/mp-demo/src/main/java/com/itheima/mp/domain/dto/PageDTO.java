package com.itheima.mp.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Classname PageDTO
 * @Description
 * @Date 2023/12/10 下午3:47
 * @Created by joneelmo
 */
@Data
@ApiModel(description = "分页结果")
public class PageDTO<T> {
    @ApiModelProperty("总条数")
    private Long total;
    @ApiModelProperty("总页数")
    private Long pages;
    @ApiModelProperty("结果集")
    private List<T> list;

    public static <PO, VO> PageDTO<VO> of(Page<PO> p,Function<PO, VO> convertor) {  //泛型方法
        PageDTO<VO> dto = new PageDTO<>();
        // 总条数
        dto.setTotal(p.getTotal());
        // 总页数
        dto.setPages(p.getPages());
        // 获取分页结果集
        List<PO> records = p.getRecords();
        if (CollUtil.isEmpty(records)) {
            dto.setList(Collections.emptyList());
            return dto;
        }
        // copy  user  到 userVo
        // 放置结果集
        dto.setList(records.stream().map(convertor).collect(Collectors.toList()));
        //4. 返回
        return dto;
    }
}
