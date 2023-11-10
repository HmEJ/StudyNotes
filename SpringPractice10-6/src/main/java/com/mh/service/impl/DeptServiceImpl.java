package com.mh.service.impl;

import com.mh.entity.Dept;
import com.mh.mapper.DeptMapper;
import com.mh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)   //配置事务传播策略
public class DeptServiceImpl implements DeptService {

    private final DeptMapper mapper;

    @Autowired        //通过构造函数来 注入mapper对象
    public DeptServiceImpl(DeptMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Dept> queryAll() {
        return mapper.selectAllDept();
    }
}
