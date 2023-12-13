package com.mh.Service.impl;

import com.mh.Service.DeptService;
import com.mh.mapper.DeptMapper;
import com.mh.pojo.Dept;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> queryAllInfo() {
        return deptMapper.getAll();
    }
}
