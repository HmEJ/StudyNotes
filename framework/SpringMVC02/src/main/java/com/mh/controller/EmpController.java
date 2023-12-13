package com.mh.controller;

import com.mh.entity.Dept;
import com.mh.entity.Emp;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 *  1. 如果类定义处添加了@RequestMapping注解,那么该类下的所有方法都会自动拼接一个一级路由
 *  2. 控制器里的方法, 在做页面跳转的时候, 全部都从一级路由开始跳
 * */
@RequestMapping("emp/")
@Controller
public class EmpController {

    /*
     * 1. 如果@RequestMapping中 只有一个value属性，并且该value只有一个值的时候，可以省略 ` value="" `，直接写值
     * 2. method 限制提交过来的请求方式，默认是都接受。
     * 3. params 可以指定请求中的参数要有哪些，还可以指定特定参数的值 ( params = {"ename=zhangsan"} => 指定ename值必须为zhangsan)
     * 4. headers 指定请求头中要有哪些参数
     * */
    @RequestMapping( value = {"login"},method = {RequestMethod.POST},params = {"ename=zhangsan"} )
    public ModelAndView login(Integer empno,String ename){
        ModelAndView modelAndView = new ModelAndView();
        if (empno==123 && "zhangsan".equals(ename)){
            modelAndView.addObject("info",ename);
            modelAndView.setViewName("ok");
        }else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @RequestMapping("ts1")
    public String test1(Integer empno, @RequestParam(value = "ename",defaultValue = "zhangsan") String name, Model model, HttpSession session){
//        model.addAttribute("info",ename);
        session.setAttribute("info", name);
        if (empno==123 && "zhangsan".equals(name)){
            return "/ok";
        }else {
            return "redirect:/";
        }
    }


    @RequestMapping("ts3")
    public ModelAndView test3(Emp emp){
        System.out.println("到达控制器ts3...");
        ModelAndView mv = new ModelAndView();
        mv.addObject("emp",emp);
//        mv.setViewName("ok");

        /*跳到另外一个处理方法中去*/
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping("show")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        List<Dept> list = new ArrayList<>();
        list.add(new Dept(123,"开发","北京"));
        list.add(new Dept(124,"测试","上海"));
        list.add(new Dept(126,"运维","深圳"));
        list.add(new Dept(127,"实施","广州"));
        modelAndView.addObject("list",list);
        modelAndView.setViewName("forward:/emp/mapShow");
        return modelAndView;
    }

    @RequestMapping("plupd")
    public ModelAndView plupd(Emp emp){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("emp",emp);
        modelAndView.setViewName("show");
        return modelAndView;
    }

    @RequestMapping("mapShow")
    public ModelAndView mapShow(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Map<Integer,Dept> map = new HashMap<>();
        Dept dept1 = new Dept(123,"开发1","北京1");
        Dept dept2 = new Dept(124,"开发2","北京2");
        Dept dept3 = new Dept(125,"开发3","北京3");
        Dept dept4 = new Dept(126,"开发4","北京4");

        map.put(dept1.getDeptno(), dept1);
        map.put(dept2.getDeptno(), dept2);
        map.put(dept3.getDeptno(), dept3);
        map.put(dept4.getDeptno(), dept4);

        modelAndView.addObject("map",map);
        modelAndView.setViewName("ok");
        return modelAndView;
    }

}
