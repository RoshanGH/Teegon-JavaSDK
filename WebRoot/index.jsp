<%@page import="com.dniu.hsj.util.SignUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
String client_id = request.getParameter("client_id");
String sign = "";
if(client_id != null && !client_id.equals("")){
    Map<String, String> param = new HashMap<String, String>();
    Map<String, String[]> requestParam = request.getParameterMap();
    for(Map.Entry<String,String[]> entry : requestParam.entrySet()){
        if(entry.getValue() != null && entry.getValue().length > 0)
        param.put(entry.getKey(), entry.getValue()[0]);
    }
    sign = SignUtil.createSign(param);
}

String payUri = "https://api.teegon.com/charge/pay";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>测试页面</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
  <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>

  <body>
	 <a href="yinlian.jsp">银联支付</a>
	 <a href="wechat.jsp">微信jsapi支付</a>
	 <a href="alipay.jsp">支付宝支付</a>
	 <a href="wxpay.jsp">微信支付</a>
  </body>
</html>
