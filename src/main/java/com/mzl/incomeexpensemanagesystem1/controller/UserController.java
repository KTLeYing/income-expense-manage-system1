package com.mzl.incomeexpensemanagesystem1.controller;

import com.mzl.incomeexpensemanagesystem1.entity.User;
import com.mzl.incomeexpensemanagesystem1.service.UserService;
import com.mzl.incomeexpensemanagesystem1.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName :   UserController
 * @Description: 用户控制器
 * @Author: 21989
 * @CreateDate: 2020/6/5 16:24
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //去登录页面
    @RequestMapping("/toLogin")
    public String login(){
        return "/index";
    }

    //用户登录
    @RequestMapping("login.action")
    public String login(User user, HttpServletRequest request, String checkCode){
        //路径测试
//        System.out.println(System.getProperty("catalina.home") + "\\webapps" + request.getContextPath().replace("/", "\\"));
//        String path = request.getSession().getServletContext().getRealPath("/");
//        System.out.println("Tomcat的webapps的目录路径：" + path);
//        String path = System.getProperty("catalina.home");
//        System.out.println(path);
//        String realPath = path + "\\webapps" + request.getContextPath().replace("/", "\\") + "\\file\\memorandum\\";
//        System.out.println(realPath);

        //获取checkcode会话
        String checkCode1 = (String) request.getSession().getAttribute("checkCode");

        //判断验证码是否正确(忽略大小写)
        if (checkCode.equalsIgnoreCase(checkCode1)){//验证码相同
            //获得用户名和密码，判断是否存在
            User findUser = userService.queryUserByUser(user);
            //1.存在，保存到session中  ； 然后   跳转到主页面
            if (findUser != null){
                HttpSession session = request.getSession();
                session.setAttribute("user", findUser);
                request.getSession().removeAttribute("checkCode");
                //通过父类型，从而查询出该父类型的所有子类型，从而进行显示
                //List<String> son=shouzhiCategoryService.findSonCategoryByParent(parent_category);
                //查询账单明细

                if(!Md5Util.getSaltverifyMD5(user.getPassword(), findUser.getPassword())){
                    //2.不存在，返回登录失败信息
                    String msg="用户名或者密码输入错误，请重新输入";
                    request.setAttribute("msg", msg);
                    request.getSession().removeAttribute("checkCode");
                    //跳转到登录页面
                    return "/index";
                }

                return "redirect:/shouzhiRecord/findShouzhiRecord.action";
                //return "/jsp/main";//跳转到主页
            }else {
                //2.不存在，返回登录失败信息
                String msg="用户名或者密码输入错误，请重新输入";
                request.setAttribute("msg", msg);
                request.getSession().removeAttribute("checkCode");
                //跳转到登录页面
                return "/index";
            }
        }else {//验证码不同，输入验证码错误
            String msg = "验证码输入错误，请重新输入";
            request.setAttribute("msg", msg);
            request.getSession().removeAttribute("checkCode");
            //跳转到到登录页面
            return "/index";
        }
    }

    //用户注册（添加用户）
    @RequestMapping("regist.action")
    public String regist(User user, String repassword, String emailCode, HttpServletRequest request){
        //获取验证码session（真正正确生成的验证码）
        String emailCode1 = (String) request.getSession().getAttribute("emailCode");

        //md5加密密码
        user.setPassword(Md5Util.getSaltMD5(user.getPassword()));

        //先判断验证码是否正确（忽略大小写）
        if(emailCode.equalsIgnoreCase(emailCode1)){//验证码正确
            //判断是否存在
            //通过用户名查询用户是否存在
            User findUser = userService.queryUserByUsername(user.getUsername());

            if (findUser != null){
                //存在
                request.setAttribute("msg", "当前用户已经存在，请重新输入用户名");
                request.setAttribute("user", user);//保存原来的输入数据
                request.setAttribute("repassword", repassword);

                //移除原来的验证码session，重新获取,发送一次验证码只能用一次
                request.getSession().removeAttribute("emailCode");

                //跳转返回到注册页面
                return "/regist";
            }else {
                //插入时，添加自增主键，所以此时的User中是有主键id的，即：有完整的user表的信息
                userService.insertUser(user);
                //直接跳转到主页面【自动登录】
                //注册完，之后保存登录信息
                HttpSession session = request.getSession();
                session.setAttribute("user", user);//保存登录信息

                //return "/jsp/main";//用户主页

                //跳转到去登录页面
//            return "/index";

                //移除原来的验证码session，重新获取,发送一次验证码只能用一次
                request.getSession().removeAttribute("emailCode");

                //跳转到去查询查询账单明细
                return "redirect:/shouzhiRecord/findShouzhiRecord.action";
            }
        }else {  //验证码错误
            request.setAttribute("msg", "邮箱验证码输入错误，请重新输入");
            request.setAttribute("user", user);//保存原来的输入数据
            request.setAttribute("repassword", repassword);

//            //移除原来的验证码session，重新获取,发送一次验证码只能用一次
//            request.getSession().removeAttribute("emailCode");

            //跳转返回到注册页面
            return "/regist";
        }
    }

    //通过用户名判断用户是否存在
    @RequestMapping(value="findUserByNameAndAjax.action",method= RequestMethod.POST)
    public @ResponseBody
    String findUserByNameAndAjax(@RequestBody User user){
        //@ResponseBody 返回将java对象转为json格式的数据
        //@RequestBody 获得json格式的数据转为java对象

        //通过用户名查询用户是否存在
        User findUser = userService.queryUserByUsername(user.getUsername());
        if(findUser != null){
            //存在
            return "{\"name\":\"exit\"}";//json格式
        }
        else{
            return "{\"name\":\"notexit\"}";//不存在
        }
    }

    //修改密码（找回密码）
    @RequestMapping("updatePasswordByUsername.action")
    public String updatePasswordByUsername(User user,HttpServletRequest request){
        //md5加密密码
        user.setPassword(Md5Util.getSaltMD5(user.getPassword()));
        userService.updatePasswordByUsername(user);
        request.setAttribute("msg", "密码修改成功，请登录");
        return "/index";
    }

    /**
     * 找回密码(修改密码，未登录)
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/findPassword.action")
    public void findPassword(User user, String msgCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int realCode = (int) request.getSession().getAttribute("msgCode");
//        int realCode = 1234;
        if (StringUtils.isEmpty(realCode) || !String.valueOf(realCode).equals(msgCode)){  //先判断验证码是否正确
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert('手机验证码错误！'); window.location='" + request.getContextPath() + "/';" +  "window.close();</script>");
//            response.getWriter().write("<script>alert('手机验证码错误！'); </script>");
//            return "/index";
        }else {
            //md5加密密码
            user.setPassword(Md5Util.getSaltMD5(user.getPassword()));
            userService.updatePasswordByUsername(user);
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert('找回密码成功！请重新登录！'); window.location='" + request.getContextPath() + "/';" +  "window.close();</script>");
//            response.getWriter().write("<script>alert('找回密码成功！请重新登录！'); </script>");
//            return "/index";
        }
    }

    //用户退出
    @RequestMapping("logout.action")
    public String logout(HttpServletRequest request){
        //移除会话
        request.getSession().removeAttribute("user");
        //返回到登录页面
        return "/index";
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @RequestMapping("delUser")
    public void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.delUser(request);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //返回到登录页面
        response.getWriter().write("<script>alert('用户注销成功！'); window.location='" + request.getContextPath() + "/';" +  "window.close();</script>");
//        return "/index";
    }

    //去用户设置页面
    @RequestMapping("/toUserSetting.action")
    public String toUserSetting(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //设置user，用于回显用户信息
        request.setAttribute("user", user);
        request.setAttribute("oldusername", user.getUsername());
        return "/jsp/userSetting";
    }

    //编辑用户信息
    @RequestMapping("/editUser.action")
    public String editUser(User user, HttpServletRequest request, String oldusername){
        //判断修改后的用户号名是否存在
        String username = user.getUsername();
        //md5加密密码
        user.setPassword(Md5Util.getSaltMD5(user.getPassword()));

        //在页面设置页面已经通过js的ajax实现了用户名判空和判是否已存在，在此次再一次判空和判是否存在，保证数据的正确性
        //用户名不为空
        if (username != "" && username != null){
            if(!username.equals(oldusername)){//新用户名和旧用户名不一样
                //普安用户名是否已经存在
                User findUser = userService.queryUserByUsername(username);
                if (findUser == null){//用户名不存在
                    //进行修改用户信息
                    userService.editUser(user);
                    //重新设置user会话
                    request.getSession().setAttribute("user", user);
                    //重定向到系统的首页，收支记录页面,user的会话信息是已经更新了
                    return "redirect:/shouzhiRecord/findShouzhiRecord.action";
                }else {  //用户名已经存在
                    user.setUsername(findUser.getUsername());
                    //用户回显信息，没有修改成功，返回到用户信息页面
                    request.setAttribute("user", user);
                    request.setAttribute("oldusername", oldusername);
                    request.setAttribute("msg", "该用户已存在，请修改当前用户名");

                    //返回到用户设置页面
                    return "/jsp/userSetting";
                }
            }else {//新用户名和旧用户名一样
                //直接进行修改
                userService.editUser(user);
                //修改成功后，重新设置user会话
                request.getSession().setAttribute("user", user);
                return "redirect:/shouzhiRecord/findShouzhiRecord.action";
            }
        }else {  //用户名为空
            request.setAttribute("msg", "用户名不能为空");
            request.setAttribute("oldusername", oldusername);
            request.setAttribute("user", user);
            //返回到用户设置页面
            return "/jsp/userSetting";
        }

    }



}
