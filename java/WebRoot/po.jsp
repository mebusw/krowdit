<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tadosoft.krowdit.po.*" %>
<%@ page import="org.springframework.transaction.annotation.Transactional" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'po.jsp' starting page</title>
    
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
    POJO test <%=basePath %><br>
    
    <%
        TableUser u = null;
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        out.println(session.getServletContext());
        TableUserDAO dao = (TableUserDAO) ctx.getBean("TableUserDAO");
        out.println(dao);
        u = dao.findById(1L);
        out.println(u.getEmail());
        
        u = new TableUser("sam", "123", "sam@tado", new Date(), 1L);
        dao.save(u);
        
        
        
     %>
  </body>
</html>
