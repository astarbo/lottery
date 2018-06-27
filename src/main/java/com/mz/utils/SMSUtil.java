package com.mz.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 22:15
 */
public class SMSUtil {

    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAIgeYL5872mhRh";
    static final String accessKeySecret = "GaxgaEc34PYHa14eiMAfRfh3nqYaZ4";
    static final String register = "SMS_137915030";
    static final String reset = "SMS_137930044";

    public static String sendMsg(String phone,int state) throws Exception {
        String mode = "";

        switch (state) {
            case 0:
                mode = register;
                break;
            case 1:
                mode = reset;
                break;
            default:
                break;
        }
        //设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);

        /**
         * 设置电话号码
         */
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        /**
         * 设置签名名称
         */
        request.setSignName("wustmz");
        //必填:短信模板-可在短信控制台中找到
        /**
         * 设置模板CODE
         * 注册：SMS_137915030
         * 重置：SMS_137930044
         */
        request.setTemplateCode(mode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        final String smscode = (long) (Math.random() * 1000000) + "";
        System.out.println("短信验证码：" + smscode);
        request.setTemplateParam("{\"code\":\"" + smscode + "\"}");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println("发送成功！");
        }else {
            System.out.println("发送失败！");
        }
        return smscode;
    }
}
