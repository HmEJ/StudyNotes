package com.jsoft.service.impl;

import com.jsoft.mapper.EmpMapper;
import com.jsoft.pojo.Emp;
import com.jsoft.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmpServiceImpl implements EmpService {
    @Autowired
    @Qualifier("empMapper")
    private EmpMapper empMapper;


    @Override
    public List<Emp> getAll() {
        return empMapper.selectAll();
    }

    @Override
    public boolean addEmp(Emp emp) {
        return empMapper.insertEmp(emp)==1;
    }

    @Override
    public boolean removeEmp(int empno) {
        return empMapper.deleteEmp(empno)==1;
    }

    @Override
    public boolean changeEmp(Emp emp) {
        return empMapper.updateEmp(emp)==1;
    }
}
