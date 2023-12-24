package com.mh.emaildemo.utils;

import com.mh.emaildemo.dto.MailDTO;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @Classname MaillSender
 * @Description
 * @Date 2023/12/24 下午8:29
 * @Created by joneelmo
 */
@Component
public class SendMail{

    @Resource
    private JavaMailSender javaMailSender;

    public void sendMail(MailDTO mailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setTo(mailDTO.getTo());
        helper.setText(mailDTO.getContent());
        helper.setSubject(mailDTO.getSubject());
        helper.setFrom(mailDTO.getFrom());
        javaMailSender.send(message);
    }
}
