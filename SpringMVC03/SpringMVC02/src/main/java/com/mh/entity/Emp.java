package com.mh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Emp {
    /*基本类型*/
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Double sal;
    private Double comm;
    private Date hiredate;
    private Integer deptno;
    private Boolean isgraduate;
    /* 包装类型 */
    private Dept dept;
    /* 集合，数组类型 */
    private String[] likes;
    /* 登录 login 跳到show处理器 生成集合 跳到show页面 批量添加/修改 */
    private List<Dept> depts;
    /* Map类型 */
    private Map<Integer,Dept> deptMap;

}
