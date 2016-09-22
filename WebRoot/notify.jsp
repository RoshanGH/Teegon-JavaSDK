<%@page import="com.dniu.hsj.util.SignUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8"); 
boolean isPaySuccess = request.getParameter("is_success")==null?false:request.getParameter("is_success").equals("true");
if(isPaySuccess){ //回调支付成功
    if(SignUtil.verifySign(request)){//签名验证通过
        %>success<%
    }
}
%>
