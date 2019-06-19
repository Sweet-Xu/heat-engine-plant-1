package cc.hep.system.controller;

import cc.hep.common.annotation.Log;
import cc.hep.common.config.HepProperties;
import cc.hep.common.controller.BaseController;
import cc.hep.common.domain.ResponseBo;
import cc.hep.common.util.MD5Utils;
import cc.hep.common.util.vcode.Captcha;
import cc.hep.common.util.vcode.GifCaptcha;
import cc.hep.system.domain.User;
import cc.hep.system.service.UserService;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Configuration
@Controller
public class LoginController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CODE_KEY = "_code";

    @Autowired
    private HepProperties hepProperties;

    @Autowired
    private UserService userService;

    @Value("${mail.fromMail.sender}")
    private String sender;// 发送者

    @Autowired
    private JavaMailSender javaMailSender;


    //登录页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //登录表单post请求
    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username, String password, String code, Boolean rememberMe) {
        if (!StringUtils.isNotBlank(code)) {
            return ResponseBo.warn("验证码不能为空！");
        }
        //从session获取正确验证码与输入验证码比较
        Session session = super.getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            return ResponseBo.warn("验证码错误！");
        }
        // 密码 MD5 加密
        password = MD5Utils.encrypt(username.toLowerCase(), password);
        //使用username,password,rememberMe创建token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            Subject subject = getSubject();
            //如果subject不为空，即已经登录，登出
            if (subject != null)
                subject.logout();
            //使用token登录
            super.login(token);
            //登陆成功记录日志
            this.userService.updateLoginTime(username);
            return ResponseBo.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    //验证码图片
    @GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            //设置响应信息格式
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            //获取配置类里的验证码长、宽、字符个数信息，生产gif验证码类
            Captcha captcha = new GifCaptcha(
                    hepProperties.getValidateCode().getWidth(),
                    hepProperties.getValidateCode().getHeight(),
                    hepProperties.getValidateCode().getLength());
            HttpSession session = request.getSession(true);
            //输出验证码图片
            captcha.out(response.getOutputStream());
            //更新session里的验证码信息
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }

    //重定向到主界面
    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    //403界面
    @GetMapping("/403")
    public String forbid() {
        return "403";
    }

    //主界面
    @Log("访问系统")
    @RequestMapping("/index")
    public String index(Model model) {
        //获取登录的用户信息
        User user = super.getCurrentUser();
        //把已登录用户信息传递过去
        model.addAttribute("user", user);
        return "index";
    }

    //短信验证码
    @RequestMapping("/shortMessageCode")
    @ResponseBody
    public ResponseBo ShortMessageCode(String phoneNumber) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIJ7kKvhbBf5i5", "2iwnJX7kqvQH1kQWIVRzergQZXXWgd");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("TemplateCode", "SMS_168311446");
        request.putQueryParameter("SignName", "火电厂监控管理系统");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        String randomCode  = randomCode();
        request.putQueryParameter("TemplateParam", "{\"code\":\""+ randomCode +"\"}");
        try {
            System.out.println("尝试发送 " + randomCode +" 到 "+ phoneNumber);
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("发送 " + randomCode +" 到 "+ phoneNumber+" 成功\n返回信息是：");
            System.out.println(response.getData());
            return ResponseBo.ok();
        } catch (ServerException e) {
            e.printStackTrace();
            return ResponseBo.error();
        } catch (ClientException e) {
            e.printStackTrace();
            return ResponseBo.error();
        }
    }


    //邮件验证码
    @RequestMapping("/mailMessageCode")
    @ResponseBody
    public ResponseBo mailMessageCode(String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(sender);
                message.setTo(email);
                message.setSubject("火电厂监控管理系统---验证码");// 标题
                String randomCode = randomCode();
                message.setText("验证码是：" + randomCode);// 内容
                try {
                    System.out.println("尝试发送 " + randomCode +" 到 "+ email);
                    javaMailSender.send(message);
                    System.out.println("发送 " + randomCode +" 到 "+ email + " 成功");
                } catch (Exception e) {
                }
            }
        }).start();
        return ResponseBo.ok();
    }


    //产生随机验证码
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
