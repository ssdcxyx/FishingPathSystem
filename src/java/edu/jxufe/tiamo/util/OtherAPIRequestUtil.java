package edu.jxufe.tiamo.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class OtherAPIRequestUtil {

    // 发送验证码请求
    public static void sendPhoneVerificationCode(String telephone,String verificationCode) throws Exception{
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(Constant.smsRequestUrl);
        // 在头文件中设置转码
        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data = {
                new NameValuePair("Uid",Constant.smsRequestUserName),
                new NameValuePair("Key",Constant.smsRequestKey),
                new NameValuePair("smsMob",telephone),
                new NameValuePair("smsText","您的验证码为"+verificationCode)
        };
        postMethod.setRequestBody(data);
        httpClient.executeMethod(postMethod);
        Header[] headers = postMethod.getResponseHeaders();
        int statusCode = postMethod.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for (Header header : headers) {
            System.out.println(header.toString());
        }
        String result = new String(postMethod.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); // 打印返回消息状态
        postMethod.releaseConnection();
    }
}
