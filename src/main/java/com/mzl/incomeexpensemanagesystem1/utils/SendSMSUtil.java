package com.mzl.incomeexpensemanagesystem1.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @ClassName :   SendSMSUtil
 * @Description: 发送短信的工具类（阿里云短信）
 * @Author: mzl
 * @CreateDate: 2020/9/16 2:12
 * @Version: 1.0
 */
public class SendSMSUtil {

    // 你的accessKeyId
    private static final String accessKeyId = "LTAI4G5T7a47zr5TeVRkqdc4";
    // 你的accessKeySecret
    private static final String accessKeySecret = "HgB9XGiy73Veh7dC1YSXUDRQ4zIE9J";
    // 上面设置的签名
    private static final String signName = "MZL管理系统";
    // 短信模板，模板code
    private static final String templateCode = "SMS_202810051";
    //生成的验证码
    private static int code;

    /**
     *  发送验证码，phoneNumber必须当做参数传入
     * @param phoneNumber
     * @return
     */
    public String senSMSUtil(String phoneNumber) {
        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化ascClient需要的几个参数
        final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）

        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();

        // 使用post提交
        request.setMethod(MethodType.POST);

        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,
        // 验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phoneNumber);

        request.setSignName(signName);

        // 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateCode);

        //随机生成六位验证码
        code = (int) ((Math.random() * 9 + 1) * 100000);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{code:" + code + "}");
//        request.setTemplateParam("{ \"code\":\"" + code + "\"}");

        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;

        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            return "请求失败";
        }

        assert sendSmsResponse.getCode() != null;
        // 获取错误码，如果你账户没钱会返回isv.AMOUNT_NOT_ENOUGH，表示账户没钱，充点钱就行了
        System.out.println(sendSmsResponse.getCode());
        if (sendSmsResponse.getCode() == null || !sendSmsResponse.getCode().equals("OK")) {// 发送不成功
            return sendSmsResponse.getMessage();
        }

        // 请求成功
        return "OK";
    }

    /**
     * 获取验证码
     * @return
     */
    public int getCode() {
        return code;
    }


}

