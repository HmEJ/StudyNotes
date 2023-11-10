package com.mh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Dept {
    @Value("1651")
    private Integer deptno;
    @Value("test")
    private String dname;
    @Value("loctest")
    private String loc;
}
