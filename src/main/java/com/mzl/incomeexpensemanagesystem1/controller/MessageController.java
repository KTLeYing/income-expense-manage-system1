package com.mzl.incomeexpensemanagesystem1.controller;

import com.mzl.incomeexpensemanagesystem1.utils.SendSMSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName :   MailController
 * @Description: 短信控制器
 * @Author: mzl
 * @CreateDate: 2021/4/8 2:13
 * @Version: 1.0
 */
@Controller
public class MessageController {

    /**
     * 发送短信
     */
    @RequestMapping("/sendMsg.action")
    public void sendMsg(String phone, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 发送短信
        SendSMSUtil sendSMS = new SendSMSUtil();
        String result = sendSMS.senSMSUtil(phone);

        //验证码存入session
        int code = sendSMS.getCode();
//        int code = 345211;
        request.getSession().setAttribute("msgCode", code);
        request.getSession().setAttribute("createTime", System.currentTimeMillis());

        // 将验证码生成时间存入SESSION，若超过五分钟则不通过校验
        final HttpSession session=request.getSession();
        try {
            // Timer 和 TimerTask都是java.util包下
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    session.removeAttribute("msgCode");
//                    System.err.println("code删除成功");
                    timer.cancel();
                }
            }, 5 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("utf-8");
//        response.getWriter().write("<script>alert('找回密码成功！请重新登录！');</script>");

        //此接口必须有返回值才能调用jQuery的post的成功回调函数，不然不能回调
//        return "";
    }


}
