package cc.hep;
import java.io.File;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
//测试发送邮箱验证码
public class mailTest {
    @Value("${mail.fromMail.sender}")
    private static String sender;// 发送者

    @Value("${mail.fromMail.receiver}")
    private static String receiver;// 接受者

    @Autowired
    private static JavaMailSender javaMailSender;

    public static void main(String[] args) {
        sendMail();
    }

   static  public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject("火电厂监控管理系统---验证码");// 标题
        message.setText("验证码是：" + randomCode());// 内容
       System.out.println(sender);
       System.out.println(receiver);
        try {
            javaMailSender.send(message);
            System.out.println(123);
            return "success";
        } catch (Exception e) {
            return "failure";
        }
    }

    private static String randomCode() {

        String str="0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
