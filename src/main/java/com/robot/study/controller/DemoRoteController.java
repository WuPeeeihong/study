package com.robot.study.controller;


import com.robot.study.common.Result;
import com.robot.study.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wuph
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/study/demo")
@Api(tags = "测试路由")
public class DemoRoteController {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/demo")
    @ApiOperation(value = "测试自定义异常", notes = "测试自定义异常类和全局异常控制")
    public Result deTest(){
        throw new MyException(666, "出错误了");
    }


    @PostMapping("/email")
    @ApiOperation(value = "发送简单邮件")
    public Result deTest2() {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo("1574489152@qq.com");
        //邮件标题
        message.setSubject("测试");
        //邮件内容
        message.setText("这个是测试内容www.baidu.com");
        try {
            mailSender.send(message);
            return Result.success( "发送普通邮件成功");
        } catch (MailException e) {
            e.printStackTrace();
            return Result.error(new MyException(999, "普通邮件方失败"));
        }
    }

    @PostMapping("/email2")
    @ApiOperation(value = "发送邮件",notes = "发送带图片和附件的邮件")
    public Result deTest3() {
        //创建带图片和附件邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo("1574489152@qq.com");
            mimeMessageHelper.setSubject("主题");
            mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\ami\\Desktop\\党政专题门户\\5.jfif"));
            //加入图片
            mimeMessageHelper.addInline("picture", file);
            //加入图片附件
            mimeMessageHelper.addAttachment("picture", file);
            mailSender.send(message);
            return Result.success( "发送普通邮件成功");
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
            return Result.error(new MyException(999, "普通邮件方失败"));
        }
    }



}
