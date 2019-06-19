package cc.hep;

import cc.hep.common.util.vcode.Captcha;
import cc.hep.common.util.vcode.GifCaptcha;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

//测试发送短信验证码
import javax.servlet.http.HttpSession;
import java.util.Random;

//生成验证码
public class smsTest {
    public static void main(String[] args) {
       DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIJ7kKvhbBf5i5", "2iwnJX7kqvQH1kQWIVRzergQZXXWgd");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("TemplateCode", "SMS_168311446");
        request.putQueryParameter("SignName", "火电厂监控管理系统");
        request.putQueryParameter("PhoneNumbers", "15170091857");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+randomCode()+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
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
