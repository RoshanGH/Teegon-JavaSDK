package com.dniu.hsj.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientTools {

    public static String sendPost(String uri, List<NameValuePair> params) {
        return sendPost(uri, params, true);
    }

    public static String sendPost(String uri, List<NameValuePair> params, Boolean encode) {
        String str = null;// 返回的json数据
        HttpPost post = new HttpPost(uri);// HttpPost对象
        // setEntity
        try {
            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        // 发送post请求
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter("http.socket.timeout", 15000); // 为HttpClient设置参数
        post.getParams().setParameter("http.socket.timeout", 15000); // 为HttpMethod设置参数

        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String returnJson;
                if (encode) {
                    returnJson = new String(EntityUtils.toString(response.getEntity()).getBytes("ISO-8859-1"), "UTF-8"); // 鑾峰彇鎺ュ彛璋冪敤杩斿洖缁撴灉
                } else {
                    returnJson = EntityUtils.toString(response.getEntity());
                }
                return returnJson;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String sendPost(String uri, byte[] data) {
        return sendPost(uri, data, true);
    }

    public static String sendPost(String uri, byte[] data, boolean encode) {
        HttpPost post = new HttpPost(uri);// HttpPost对象
        post.setEntity(new ByteArrayEntity(data));
        // 发送post请求
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter("http.socket.timeout", 15000); // 为HttpClient设置参数
        post.getParams().setParameter("http.socket.timeout", 15000); // 为HttpMethod设置参数

        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String returnJson;
                if (encode) returnJson = new String(EntityUtils.toString(response.getEntity()).getBytes("ISO-8859-1"),
                                                    "UTF-8");
                else returnJson = EntityUtils.toString(response.getEntity());
                return returnJson;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String sendGet(String uri) {
        String str = null;// 返回的json数据
        HttpGet get = new HttpGet(uri);// HttpPost对象
        // 发送post请求
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter("http.socket.timeout", 15000); // 为HttpClient设置参数
        get.getParams().setParameter("http.socket.timeout", 15000); // 为HttpMethod设置参数

        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String returnJson = new String(EntityUtils.toString(response.getEntity()).getBytes("ISO-8859-1"),
                                               "UTF-8"); // 鑾峰彇鎺ュ彛璋冪敤杩斿洖缁撴灉
                return returnJson;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String sendPostContentBody(String uri, MultipartEntityBuilder m) {
        HttpPost post = new HttpPost(uri);// HttpPost对象
        post.setEntity(m.build());
        // 发送post请求
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter("http.socket.timeout", 15000); // 为HttpClient设置参数
        post.getParams().setParameter("http.socket.timeout", 15000); // 为HttpMethod设置参数

        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String returnJson = EntityUtils.toString(response.getEntity()); // 获取接口调用返回结果
                return returnJson;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
