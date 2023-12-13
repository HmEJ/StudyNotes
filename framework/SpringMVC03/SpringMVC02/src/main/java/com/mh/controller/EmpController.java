package com.mh.controller;

import com.google.gson.Gson;
import com.mh.entity.Dept;
import com.mh.entity.Emp;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.file.Files;
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

    //produces设置返回值类型和编码类型
    @RequestMapping(value = "getdepts", produces = "application/json;charset=utf-8")
    public @ResponseBody String getdepts(){
        List<Dept> list = new ArrayList<>();
        list.add(new Dept(123, "开发部", "北京"));
        list.add(new Dept(124, "测试部", "北京"));
        list.add(new Dept(125, "运维部", "北京"));
        Gson gson = new Gson();
        String s = gson.toJson(list);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "getInfoByName",produces = "application/json;charset=utf-8")
    public @ResponseBody String getInfoByName(String uname){
        List<Dept> list = new ArrayList<>();
        list.add(new Dept(123, "开发部"+uname, "北京"));
        list.add(new Dept(124, "测试部"+uname, "北京"));
        list.add(new Dept(125, "运维部"+uname, "北京"));
        Gson gson = new Gson();
        String s = gson.toJson(list);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "upload")
    public String upload(String uname, HttpServletRequest request, MultipartFile upfile) throws IOException {
        /* 1.获得上传时文件名 */
        String filename = upfile.getOriginalFilename();
        System.out.println(filename);
        /* 2.获得文件发布的位置 （服务器地址）*/
        String url = request.getSession().getServletContext().getRealPath("/imgs/");
        System.out.println(url);
        System.out.println(url+filename);

        File file = new File(url+filename);

        upfile.transferTo(file);

        request.getSession().setAttribute("url", filename);

        return "redirect:/upload.jsp";
    }

    @RequestMapping("download")
    public String downLoad(HttpSession session){
        String url = session.getServletContext().getRealPath("/imgs/");
        File file = new File(url);
        /* 获取当前目录下的所有文件和目录 */
        File[] files = file.listFiles();

        List<String> list = new ArrayList<>();

        for (File f : files) {
            list.add(f.getName());
        }

        session.setAttribute("list",list);
        return "redirect:/filedownload.jsp";

    }

    @RequestMapping("fdown")
    public void downLoadFiles(String fname, HttpServletResponse response, HttpSession session) throws IOException {
        String url = session.getServletContext().getRealPath("/imgs/");
        File file = new File(url+fname);

        // 使用URL编码来处理文件名
        String encodedFileName = URLEncoder.encode(fname, "UTF-8").replaceAll("\\+", "%20");
        //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
        response.setContentType("application/x-msdownload");
        //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
        Files.copy(file.toPath(), response.getOutputStream());
    }


}
