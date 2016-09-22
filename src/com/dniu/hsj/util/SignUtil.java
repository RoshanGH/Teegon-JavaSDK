package com.dniu.hsj.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

public class SignUtil {

    private static String secret = "lutiiwptjs475jiqc664q7f4xal542a3"; // 后台公共参数---client_secret

    /**
     * 获取请求参数的加密串
     * 
     * @param params
     * @return
     */
    public static String createSign(Map<String, String> params) {
        String linkSign = secret + createLinkString(params) + secret;
        return DigestUtils.md5Hex(linkSign).toUpperCase();
    }

    /**
     * 将参数按照字母顺序进行排序
     * 
     * @param params
     * @return
     */
    private static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (key.equals("sign")) continue;
            String value = params.get(key);
            prestr = prestr + key + value;
        }

        return prestr;
    }

    /**
     * 签名校验
     * 
     * @param request
     * @return
     */
    public static boolean verifySign(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        Map<String, String> params = new HashMap<String, String>();
        for (Map.Entry<String, String[]> entry : param.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        String strMd5 = secret + createLinkString(params) + secret;
        String sign = DigestUtils.md5Hex(strMd5).toUpperCase();
        return sign.equals(params.get("sign"));
    }

}
