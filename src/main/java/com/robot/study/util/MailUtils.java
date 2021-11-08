package com.robot.study.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * @description: 发送邮件工具类
 * @author: wph
 * @time: 2021/11/8
 */
public class MailUtils {

    //发送者邮箱地址
    @Value("${spring.mail.username}")
    private static String from;

    /**
     * 简单文本邮件发送
     */
    public static void doSendMail(JavaMailSender mailSender, String toMail, String toSubject, String toText){
        doSendMail(mailSender, toMail, toSubject, toText, null);
    }

    /**
     * 带附件邮件发送
     */
    public static void doSendMail(JavaMailSender mailSender, String toMail, String toSubject, String toText, File file) {
        //创建带图片和附件邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setSubject(toSubject);
            mimeMessageHelper.setText(toText);
            if (file != null){
                //文件路径
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                //加入图片附件
                mimeMessageHelper.addAttachment("附件", fileSystemResource);
            }
            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单文本群发邮件
     */
    public static void doSendMails(JavaMailSender mailSender, List<String> toMails, String toSubject, String toText){
        doSendMails(mailSender, toMails, toSubject, toText, null);
    }

    /**
     * 带附件群发邮件
     */
    public static void doSendMails(JavaMailSender mailSender, List<String> toMails, String toSubject, String toText, File file) {
        //创建带图片和附件邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(toSubject);
            mimeMessageHelper.setText(toText);
            if (file != null){
                //文件路径
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                //加入图片附件
                mimeMessageHelper.addAttachment("附件", fileSystemResource);
            }
            for (String toMail : toMails){
                mimeMessageHelper.setTo(toMail);
                mailSender.send(message);
            }
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }
}
