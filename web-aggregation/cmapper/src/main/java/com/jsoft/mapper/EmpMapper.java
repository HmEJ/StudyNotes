package com.jsoft.mapper;

import com.jsoft.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
    /*查找*/
    List<Emp> selectAll();
    /*新增*/
    int insertEmp(Emp emp);
    /*删除*/
    int deleteEmp(int empno);
    /*更改*/
    int updateEmp(Emp emp);
}