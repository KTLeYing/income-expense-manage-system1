package com.mzl.incomeexpensemanagesystem1.controller;

import com.alibaba.fastjson.JSON;
import com.mzl.incomeexpensemanagesystem1.entity.Admin;
import com.mzl.incomeexpensemanagesystem1.entity.PageBean;
import com.mzl.incomeexpensemanagesystem1.entity.User;
import com.mzl.incomeexpensemanagesystem1.service.AdminService;
import com.mzl.incomeexpensemanagesystem1.service.UserService;
import com.mzl.incomeexpensemanagesystem1.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName :   UserManageController
 * @Description: 用户管理控制器（管理员）
 * @Author: 21989
 * @CreateDate: 2020/7/7 17:13
 * @Version: 1.0
 */
@Controller
@RequestMapping("/userManage")
@CrossOrigin(origins = "*")
public class UserManageController {

    //注入依赖
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    //管理员登录
    @RequestMapping("/login.action")
    public String login(Admin admin, HttpServletRequest request){

        //用用户名和密码查找用户
        Admin findAdmin = adminService.findAdmin(admin);

        if (findAdmin == null){
            request.setAttribute("msg", "登录失败，用户名或密码错误");
            return "/admin/index";
        }

        if(!Md5Util.getSaltverifyMD5(admin.getPassword(), findAdmin.getPassword())){
            //2.不存在，返回登录失败信息
            String msg="登录失败，用户名或密码错误";
            request.setAttribute("msg", msg);
            //跳转到登录页面
            return "/admin/index";
        }

        //存储user会话
        request.getSession().setAttribute("admin", findAdmin);

        //重定向到分页查询用户列表的controller控制器接口
        return "redirect:/userManage/findUsers.action";
    }

    //退出登录
    @RequestMapping("/logout.action")
    public String logout(HttpSession session){
        //移除会话
        session.removeAttribute("admin");
        return "/admin/index";
    }

    //分页查询用户
    @RequestMapping("/findUsers.action")
    public String findUsers(User user, Integer currentPage, Model model){
//        System.out.println(user);
        //查询条件：用户名+邮箱+设手机+当前页
        //当前页，默认下为0， 提交过来是第1页

        //保存用户条件，用户回显模糊查询条件
        model.addAttribute("findUser", user);

        //分页查询用户列表
        PageBean<User> pageBean = userService.findUsers(user, currentPage);

        //设置pagebean分页查询结果数据
        model.addAttribute("pageBean", pageBean);

        //返回到主页面
        return "/admin/main";
    }

    //添加用户
    @RequestMapping("/addUser.action")
    public String addUser(User user, HttpServletRequest request){
        //md5加密密码
        user.setPassword(Md5Util.getSaltMD5(user.getPassword()));
        //当前用户不存在时才可以进行添加用户（在前端进行校验）
        //添加用户
        userService.insertUser(user);
        //重定向到用户分页查询列表
        return "redirect:/userManage/findUsers.action";
    }

    //编辑用户信息页面，回显信息
    @RequestMapping("/toEditPage.action")
    @ResponseBody
    public String toEditPage(Integer uid){

        //查询要修改的用户原来信息
        User user = userService.queryUserById(uid);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        //用于保留原来的用户名
        map.put("old_username", user.getUsername());

        //转为json数据格式
        String jsonString = JSON.toJSONString(map);
//        System.out.println(jsonString);

        return jsonString;
    }

    //编辑用户信息
    @RequestMapping("/editUser.action")
    public String editUser(User user, Integer currentPage2){
        //md5加密密码
        user.setPassword(Md5Util.getSaltMD5(user.getPassword()));
        //编辑全部用户信息
        userService.editUserAll(user);
        //重定向到用户分页列表
        return "redirect:/userManage/findUsers.action?currentPage=" + currentPage2;
    }

    //ajax判断该用户是否可以删除
    @RequestMapping("/ajaxConfirmDeleteUser.action")
    @ResponseBody
    public String ajaxConfirmDeleteUser(int uid){
        //当用户存在外键时，不能删除该用户
        //包括 预算表 心愿表 备忘录表 收支记录表， 任何一个表存在数据都不能删

        //用户的收支记录表记录数
        int count1 = adminService.countShouzhiRecord(uid);
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;

//        System.out.println(count1);
        if (count1 == 0){
            //用户的预算表记录
            count2 = adminService.countBudget(uid);
//            System.out.println(count2);
            if (count2 == 0){
                //用户的心愿表记录
                count3 = adminService.countWishList(uid);
//                System.out.println(count3);
                if (count3 == 0){
                    //用户的备忘录
                    count4 = adminService.countMemorandum(uid);
//                    System.out.println(count4);
                    //count4等于0，证明其他表没数据，可以删除该用户
                    if (count4 == 0){
                        return "{\"name\":\"yes\"}";  //json格式
                    }
                }
            }
        }
        //不能删除
        return "{\"name\":\"no\"}";  //json格式
    }

    //删除用户
    @RequestMapping("/deleteUser.action")
    public String deleteUser(int uid, Integer currentPage2){
        //删除用户
        adminService.deleteUser(uid);

        //查询当前有多少条用户信息记录，防止原来页是最后一页，且只有一条数据，删除后没数据了，导致处理分页失败
        //每页记录数
        int pageRecord = 10;
        //查询用户总记录数
        int allRecord = adminService.countUsers();
//        System.out.println(allRecord);

        //总页数
        int allPage = 0;

        //处理总页数
        if (allRecord % pageRecord == 0){
            allPage = allRecord / pageRecord;
        }else {
            allPage = allRecord / pageRecord + 1;
        }

        //处理当前页主要是原来页是最后一页时)
        if(currentPage2 > allPage - 1){
            currentPage2 = currentPage2 - 1;  //原来是最后一页，没数据了，则返回分页列表上一页
        }

        //重定向返回到原来的用户分页页面
        return "redirect:/userManage/findUsers.action?currentPage=" + currentPage2;
    }

    /**
     * 找回密码(修改密码，未登录)
     * @param admin
     * @param request
     * @return
     */
    @RequestMapping("/findPassword")
    public void findPassword(Admin admin, String msgCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int realCode = (int) request.getSession().getAttribute("msgCode");
//        int realCode = 1234;
        if (StringUtils.isEmpty(realCode) || !String.valueOf(realCode).equals(msgCode)){  //先判断验证码是否正确
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert('手机验证码错误！'); window.location='" + request.getContextPath() + "/userManage/toLogin';" +  "window.close();</script>");
//            response.getWriter().write("<script>alert('手机验证码错误！'); </script>");
//            return "/index";
        }else {
            //md5加密密码
            admin.setPassword(Md5Util.getSaltMD5(admin.getPassword()));
            adminService.updatePasswordByUsername(admin);
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert('找回密码成功！请重新登录！'); window.location='" + request.getContextPath() + "/userManage/toLogin';" +  "window.close();</script>");
//            response.getWriter().write("<script>alert('找回密码成功！请重新登录！'); </script>");
//            return "/index";
        }
    }

    //通过用户名判断用户是否存在
    @RequestMapping(value="findUserByNameAndAjax.action",method= RequestMethod.POST)
    public @ResponseBody
    String findUserByNameAndAjax(@RequestBody User user){
        //@ResponseBody 返回将java对象转为json格式的数据
        //@RequestBody 获得json格式的数据转为java对象

        //通过用户名查询用户是否存在
        Admin findUser = adminService.queryUserByUsername(user.getUsername());
        if(findUser != null){
            //存在
            return "{\"name\":\"exit\"}";//json格式
        }
        else{
            return "{\"name\":\"notexit\"}";//不存在
        }
    }

    //去管理员登录页面
    @RequestMapping("/toLogin")
    public String login(){
        return "/admin/index";
    }






}
