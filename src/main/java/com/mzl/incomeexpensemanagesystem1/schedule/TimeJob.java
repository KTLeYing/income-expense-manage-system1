package com.mzl.incomeexpensemanagesystem1.schedule;

import com.mzl.incomeexpensemanagesystem1.entity.User;
import com.mzl.incomeexpensemanagesystem1.mapper.UserMapper;
import com.mzl.incomeexpensemanagesystem1.utils.EmailCodeUtil;
import com.mzl.incomeexpensemanagesystem1.vo.UserRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName :   TimeJob
 * @Description: 定时任务
 * @Author: v_ktlema
 * @CreateDate: 2022/3/31 11:04
 * @Version: 1.0
 */
@Component
@Slf4j
public class TimeJob {

    @Autowired
    private UserMapper userMapper;

    /**
     * 每天23:59:59秒发邮件给用户，告知他们今天的消费情况
     */
    @Scheduled(cron = "59 59 23 * * ?")
    public void noticeDayExpense() {
        log.info("定时任务：每天23:59:59秒发邮件给用户，告知他们今天的消费情况...");
        //先查询所有用户
        List<UserRecordVo> UserRecordVoList = userMapper.selectAllUser();
        //获取今天日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(date);
        UserRecordVoList.forEach(userRecordVo -> {
            //查询当前用户的当天的收入
            Integer incomeNum = userMapper.countIncome(userRecordVo.getUid(), today);
            if(incomeNum == null){
                incomeNum = 0;
            }
            //查询当前用户的当天的支出
            Integer expenseNum = userMapper.countExpense(userRecordVo.getUid(), today);
            if(expenseNum == null){
                expenseNum = 0;
            }
            //当前用户的本月的总收支
            Integer totalNum = incomeNum + expenseNum;

            //构造发送邮件的信息
            String htmlMessage = "<h3 style='color: red;'>智能收支管理平台-用户今天收支情况</h3><br/>" +
                    userRecordVo.getUsername() + "&nbsp;您好！您今天的收支情况如下：<br/>" +
                    "&nbsp;&nbsp;&nbsp;今天总收入金额: " + incomeNum + "<br/>" +
                    "&nbsp;&nbsp;&nbsp;今天总支出金额: " + expenseNum  + "<br/>" +
                    "&nbsp;&nbsp;&nbsp;今天总金额(净收入): " + totalNum + "<br/>"   +
                    "智能收支管理平台陪伴你每一天！祝你明天生活愉快鸭！<br/><br/>";

            //开启一个线程发送邮件
            try {
                sendEmail(userRecordVo.getEmail(), htmlMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 每个月1号早上6点会发邮件给用户，告知他们上个月的消费情况
     */
    @Scheduled(cron = "0 0 6 * * ?")
    public void noticeMonthExpense() {
        log.info("定时任务：每个月1号早上6点会发邮件给用户，告知他们上个月的消费情况...");
        //先查询所有用户
        List<UserRecordVo> UserRecordVoList = userMapper.selectAllUser();
        //获取今天日期
        Date date = new Date();
        //创建日历对象
        Calendar cal = Calendar.getInstance();
        //将时间日期数据传入日历对象
        cal.setTime(date);
        //设置天数减10（保证上个月）
        cal.add(Calendar.DAY_OF_MONTH, -10);
        Date now1 = cal.getTime();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM");
        String thisMonth = simpleDateFormat1.format(now1);

        UserRecordVoList.forEach(userRecordVo -> {
            //查询当前用户的本月的收入
            Integer incomeNum = userMapper.countMonthIncome(userRecordVo.getUid(), thisMonth);
            if(incomeNum == null){
                incomeNum = 0;
            }
            //查询当前用户的本月的支出
            Integer expenseNum = userMapper.countMonthExpense(userRecordVo.getUid(), thisMonth);
            if(expenseNum == null){
                expenseNum = 0;
            }
            //当前用户的本月的总收支
            Integer totalNum = incomeNum + expenseNum;

            //构造发送邮件的信息
            String htmlMessage = "<h3 style='color: red;'>智能收支管理平台-用户月收支情况</h3><br/>" +
                    userRecordVo.getUsername() + "&nbsp;您好！您"+ "2020-04" + "月的收支情况如下：<br/>" +
                    "&nbsp;&nbsp;&nbsp;月总收入金额: " + incomeNum + "<br/>" +
                    "&nbsp;&nbsp;&nbsp;月总支出金额: " + expenseNum  + "<br/>" +
                    "&nbsp;&nbsp;&nbsp;月总金额(净收入): " + totalNum + "<br/>"   +
                    "智能收支管理平台陪伴你每一天！祝您每天生活愉快！<br/><br/>";

            //开启一个线程发送邮件
            try {
                sendEmail(userRecordVo.getEmail(), htmlMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 每年1月1号早上6点会发邮件给用户，告知他们上一年的消费情况
     */
    @Scheduled(cron = "0 0 6 1 1 ?")
    public void noticeYearExpense() {
        log.info("定时任务：每年1月1号早上6点会发邮件给用户，告知他们上一年的消费情况...");
        //先查询所有用户
        List<UserRecordVo> UserRecordVoList = userMapper.selectAllUser();
        //获取今天日期
        Date date = new Date();
        //创建日历对象
        Calendar cal = Calendar.getInstance();
        //将时间日期数据传入日历对象
        cal.setTime(date);
        //设置天数减10（保证上一年）
        cal.add(Calendar.DAY_OF_MONTH, -10);
        Date now1 = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String thisYear = simpleDateFormat.format(now1);

        UserRecordVoList.forEach(userRecordVo -> {
            //查询当前用户的本年的收入
            Integer incomeNum = userMapper.countYearIncome(userRecordVo.getUid(), thisYear);
            if(incomeNum == null){
                incomeNum = 0;
            }
            //查询当前用户本年的支出
            Integer expenseNum = userMapper.countYearExpense(userRecordVo.getUid(), thisYear);
            if(expenseNum == null){
                expenseNum = 0;
            }
            //当前用户的本月的总收支
            Integer totalNum = incomeNum + expenseNum;

            //构造发送邮件的信息
            String htmlMessage = "<h3 style='color: red;'>智能收支管理平台-用户年收支情况</h3><br/>" +
                    userRecordVo.getUsername() + "&nbsp;您好！您"+ thisYear + "年的收支情况如下：<br/>" +
                    "&nbsp;&nbsp;&nbsp;年总收入金额: " + incomeNum + "<br/>" +
                    "&nbsp;&nbsp;&nbsp;年总支出金额: " + expenseNum  + "<br/>" +
                    "&nbsp;&nbsp;&nbsp;年总金额(净收入): " + totalNum + "<br/>"   +
                    "智能收支管理平台陪伴你每一天！祝您每天生活愉快！<br/><br/>";

            //开启一个线程发送邮件
            try {
                sendEmail(userRecordVo.getEmail(), htmlMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 每天1号10点会发邮件给用户，告知他们记得记录今天的消费情况
     */
    @Scheduled(cron = "0 0 10 1 * ?")
    public void noticeUserToRecord() {
        log.info("定时任务：每天1号10点会发邮件给用户，告知他们记得记录今天的消费情况...");
        //先查询所有用户
        List<UserRecordVo> UserRecordVoList = userMapper.selectAllUser();
        UserRecordVoList.forEach(userRecordVo -> {

            //构造发送邮件的信息
            String htmlMessage = "<h3 style='color: red;'>智能收支管理平台-用户今天收支情况</h3><br/>" +
                    userRecordVo.getUsername() + "&nbsp;您好！<br/>" +
                    "&nbsp;&nbsp;&nbsp;今天也要记得和智能收支管理平台交流噢！<br/>" +
                    "&nbsp;&nbsp;&nbsp;智能收支管理平台智能消费更理智！<br/>" +
                    "&nbsp;&nbsp;&nbsp;赶紧登录智能收支管理平台记录今天的个人收支情况鸭鸭~<br/>" +
                    "&nbsp;&nbsp;&nbsp;登录地址：http://localhost:9999/incomeExpense<br/>" +
                    "智能收支管理平台陪伴你每一天！祝您每天生活愉快！<br/><br/>";

            //开启一个线程发送邮件
            try {
                sendEmail(userRecordVo.getEmail(), htmlMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 发送邮件异步任务
     * @param
     * @param message
     */
    @Async("sendEmailTaskExecutor")
    public void sendEmail(String email, String message) throws IOException, MessagingException {
        EmailCodeUtil emailCodeUtil = new EmailCodeUtil();
        emailCodeUtil.sendSettingEmail(email, message);
    }



  public static void main(String[] args) throws ParseException {
      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
      //获取今天日期
      Date date = simpleDateFormat1.parse("2022-01-01");
//      //创建日历对象
//      Calendar cal = Calendar.getInstance();
//      //将时间日期数据传入日历对象
//      cal.setTime(date);
//      //设置天数减10（保证上个月）
//      cal.add(Calendar.DAY_OF_MONTH, -10);
//      Date now1 = cal.getTime();
//      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//      String thisMonth = simpleDateFormat.format(now1);
//      System.out.println(thisMonth);

      //创建日历对象
      Calendar cal = Calendar.getInstance();
      //将时间日期数据传入日历对象
      cal.setTime(date);
      //设置天数减10（保证上一年）
      cal.add(Calendar.DAY_OF_MONTH, -10);
      Date now1 = cal.getTime();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
      String thisYear = simpleDateFormat.format(now1);
      System.out.println(thisYear);
  }

}
