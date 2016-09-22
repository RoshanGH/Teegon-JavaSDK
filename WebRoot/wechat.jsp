<%@page import="net.sf.json.JSONObject"%>
<%@page import="org.apache.http.message.BasicNameValuePair"%>
<%@page import="org.apache.http.NameValuePair"%>
<%@page import="com.dniu.hsj.util.HttpClientTools"%>
<%@page import="com.dniu.hsj.util.SignUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//接口请求地址
String payUri = "https://api.teegon.com/v1/charge/";
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//设置编码格式
request.setCharacterEncoding("UTF-8");
String client_id = request.getParameter("client_id");
List<NameValuePair> params = new ArrayList<NameValuePair>();
if(client_id != null && !client_id.equals("")){
    Map<String, String[]> requestParam = request.getParameterMap();
    for(Map.Entry<String,String[]> entry : requestParam.entrySet()){
        params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()[0]));
    }
    String resp = HttpClientTools.sendPost(payUri, params);
    System.out.print(resp);
    JSONObject obj = JSONObject.fromObject(resp);
    //根据返回参数，获取result --> action --> params 参数，进行页面跳转
    %>
    	<script>
    	<%=obj.getJSONObject("result").getJSONObject("action").getString("params")%>
    	</script>
    <%
}


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
  	<h1 style="color:red">请在微信浏览器中打开</h1>
	 <form action="wechat.jsp" method="post">
		    用户身份id：<input type="text" value="vk5pjf6hlpoefh4zgtyzb7xl" name="client_id"/><br/>
		    加密密钥：<input type="text" value="lutiiwptjs475jiqc664q7f4xal542a3" name="client_secret"/><br/>
		    接口异步通知地址：<input type="text" value="http://www.baidu.com" name="notify_url"/><br/>
		    页面跳转地址：<input type="text" value="http://www.baidu.com" name="return_url"/><br/>
		    订单编号：<input type="text" value="20160920202633232" name="order_no"/><br/>
		    支付渠道：<input type="text" value="wxpay_jsapi" name="channel"/><br/>
		    订单金额：<input type="text" value="0.01" name="amount"/><br/>
		    商品信息：<input type="text" value="iphone 7s plus" name="subject"/><br/>
		    附加信息：<input type="text" value="我是附加信息" name="metadata"/><br/>
		    客户端ip：<input type="text" value="127.0.0.1" name="client_ip"/><br/>
		 <input type="submit" value="立即支付"/>
	 </form>
  </body>
</html>
