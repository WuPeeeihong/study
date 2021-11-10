package com.robot.study.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Wuph
 * @Date: create in 2021/11/10/ 16:05
 * @Description http请求工具类
 */
public class HttpUtils {

    /**
     * get 请求， 获取返回结构体
     * @param url
     * @param param
     * @return
     * @throws ParseException
     */
    public static JSONObject doGet(String url, String param) throws ParseException {
        //此处将要发送的数据转换为json格式字符串
        return doPost(url, param);
    }

    /**
     * Do-Post请求， 获取返回结构体
     * @param url
     * @param param
     * @return
     */
    public static JSONObject doPost(String url, String param) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        HttpPost post = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            StringEntity s = new StringEntity(param);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式:
                String result = EntityUtils.toString(res.getEntity());
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

}
