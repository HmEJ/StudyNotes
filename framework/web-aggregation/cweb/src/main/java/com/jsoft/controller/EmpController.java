package com.jsoft.controller;

import com.jsoft.pojo.Emp;
import com.jsoft.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("emp/")
@Controller
@Slf4j
public class EmpController {
    @Autowired
    @Qualifier("empServiceImpl")
    private EmpServiceImpl empService;

    @RequestMapping(value = "getall",produces = "application/json;charset=utf-8")
    public @ResponseBody List<Emp> getAll(){
        List<Emp> list = empService.getAll();
        return list;
    }

    @RequestMapping( value = "addEmp",method = {RequestMethod.PUT})
    public @ResponseBody String addEmp(@RequestBody Emp emp){
        log.info(String.valueOf(emp));
        boolean b = empService.addEmp(emp);
        log.info(String.valueOf(b));
        return String.valueOf(b);
    }

    @RequestMapping(value = "removeEmp")
    public @ResponseBody String removeEmp(@RequestBody String empno){
        log.info(empno);
        String[] split = empno.split("=");
        String id = split[1];
        boolean b = empService.removeEmp(Integer.parseInt(id));
        log.info(String.valueOf(b));
        return String.valueOf(b);
    }

    @RequestMapping("changeEmp")
    public @ResponseBody String changeEmp(@RequestBody Emp emp){
        log.info(String.valueOf(emp));
        String job = emp.getJob();
        String ename = emp.getEname();

        if (job==""){
            emp.setJob(null);
        }
        if (ename==""){
            emp.setEname(null);
        }

        boolean b = empService.changeEmp(emp);
        log.info(String.valueOf(b));
        return String.valueOf(b);
    }
}
