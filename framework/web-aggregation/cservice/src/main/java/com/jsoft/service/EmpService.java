package com.jsoft.service;

import com.jsoft.pojo.Emp;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface EmpService {

    List<Emp> getAll();

    boolean addEmp(Emp emp);

    boolean removeEmp(int empno);

    boolean changeEmp(Emp emp);
}
