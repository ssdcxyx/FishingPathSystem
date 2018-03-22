package edu.jxufe.tiamo.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import com.google.gson.Gson;
import com.oracle.tools.packager.Log;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OtherAPIRequestUtil {

    private static final String TAG = "OtherAPIRequestUtil";
    protected static final Logger LOG = LoggerFactory.getLogger(OtherAPIRequestUtil.class);

    // 极光推送返回信息
    private class MSG{
        private String msg_id;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }
    }

    // 发送短信验证码请求（极光推送）
    public static String sendPhoneVerificationCodeUseJiGuang(String telephone){
        SMSClient client = new SMSClient(Constant.jiguangMasterSecret,Constant.jiguangAppKey);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(telephone)
                .setTempId(Constant.jiguangTemplateId)
                .build();
        String result = null;
        try{
            SendSMSResult res = client.sendSMSCode(payload);
            System.out.println(TAG+" sendPhoneVerificationCodeUseJiGuang "+res);
            LOG.info(res.toString());
            result = res.toString();
        }catch (APIConnectionException ex){
            ex.printStackTrace();
            LOG.error("Connection error. Should retry later. ", ex);
        }catch (APIRequestException ex){
            ex.printStackTrace();
            LOG.error("Error response from JPush server. Should review and fix it. ", ex);
            LOG.info("HTTP Status: " + ex.getStatus());
            LOG.info("Error Message: " + ex.getMessage());
        }finally {
            return result; // 返回的是json{msg_id:"XXXXXX"}
        }
    }

    // 验证短信验证码（极光推送）
    public static Code.jiguangReturnResult verifySendValidSMSCode(String msgId, String validStr){
        Gson gson = new Gson();
        MSG msg = gson.fromJson(msgId,MSG.class);
        if(msg==null){
            return Code.jiguangReturnResult.NULL;
        }
        String msg_id = msg.getMsg_id();
        SMSClient client = new SMSClient(Constant.jiguangMasterSecret,Constant.jiguangAppKey);
        Boolean valid = false;
        try{
            ValidSMSResult res = client.sendValidSMSCode(msg_id,validStr);
            valid = res.getIsValid();
            System.out.println(TAG+" verifySendValidSMSCode "+res);
            LOG.info(res.toString());
            if(!valid){
                return Code.jiguangReturnResult.ERROR;
            }
            return Code.jiguangReturnResult.SUCCESS;
        }catch (APIConnectionException ex){
            System.out.println("Connection error. Should retry later. "+ ex.toString());
            LOG.error("Connection error. Should retry later. ", ex);
            return Code.jiguangReturnResult.ERROR;
        }catch (APIRequestException ex){
            System.out.println(ex.getStatus() + " errorCode: " + ex.getErrorCode() + " " + ex.getErrorMessage());
            LOG.error("Error response from JPush server. Should review and fix it. ", ex);
            LOG.info("HTTP Status: " + ex.getStatus());
            LOG.info("Error Message: " + ex.getMessage());
            if(ex.getErrorCode() == 50010){
                // 当验证码失效时
                return Code.jiguangReturnResult.ERROR;
            }else if(ex.getErrorCode() == 50011){
                return Code.jiguangReturnResult.LOSE;
            }else if(ex.getErrorCode()==50012){
                return Code.jiguangReturnResult.TRIED;
            }else{
                return null;
            }
        }
    }

    // 发送短信验证码请求(中国网建-已弃用)
    public static boolean sendPhoneVerificationCodeUseSMSS(String telephone,String verificationCode){
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(Constant.smsRequestUrl);
        // 在头文件中设置转码
        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data = {
                new NameValuePair("Uid",Constant.smsRequestUserName),
                new NameValuePair("Key",Constant.smsRequestKey),
                new NameValuePair("smsMob",telephone),
                new NameValuePair("smsText","您的验证码为："+verificationCode+Constant.smsSignature)
        };

        postMethod.setRequestBody(data);
        try{
            httpClient.executeMethod(postMethod);
            Header[] headers = postMethod.getResponseHeaders();
            int statusCode = postMethod.getStatusCode();
            System.out.println(TAG + " sendPhoneVerificationCodeUseSMSS " +" statusCode: "+statusCode);
            for (Header header : headers) {
                System.out.println(header.toString());
            }
            String result = new String(postMethod.getResponseBodyAsString().getBytes("gbk"));
            System.out.println(result); // 打印返回消息状态
            postMethod.releaseConnection();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
