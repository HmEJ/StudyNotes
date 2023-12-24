package com.mh.emaildemo;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.mh.emaildemo.config.SMSConfig;
import com.mh.emaildemo.dto.MailDTO;

import com.mh.emaildemo.dto.MessageDTO;
import com.mh.emaildemo.utils.SendMail;
import com.mh.emaildemo.utils.SendMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@SpringBootTest
class EmailDemoApplicationTests {
//    @Autowired
//    JavaMailSender javaMailSender;

    @Autowired
    SendMail sendMail;

    @Autowired
    SendMessage sender;

    @Autowired
    SMSConfig smsConfig;

    @Test
    public void messageTest() throws ClientException {
        MessageDTO msDTO = new MessageDTO();
        msDTO.setPhone("17552654906");
        msDTO.setCode("2022");
        //发短信
        SendSmsResponse response = sender.sendSms(smsConfig,msDTO);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
    }


    @Test
    public void test2() throws MessagingException {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setFrom("2549936127@qq.com");
        mailDTO.setPersonal("mohang");
        mailDTO.setSubject("nihao");
        mailDTO.setContent("nihao");
        mailDTO.setTo("joneelmo@163.com");
        sendMail.sendMail(mailDTO);
    }

//    @Test
//    public void test() throws MessagingException, UnsupportedEncodingException {
//        // 创建一个邮件消息
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        // 创建 MimeMessageHelper
//        MimeMessageHelper helper = new MimeMessageHelper(message, false);
//
//        // 发件人邮箱和名称
//        helper.setFrom("mhangggggg@qq.com", "mohang");
//        // 收件人邮箱
//        helper.setTo("joneelmo@163.com");
//        // 邮件标题
//        helper.setSubject("Hello");
//        // 邮件正文，第二个参数表示是否是HTML正文
//        helper.setText("Hello <strong> World</strong>！", true);
//
//        // 发送
//        javaMailSender.send(message);
//    }

}
