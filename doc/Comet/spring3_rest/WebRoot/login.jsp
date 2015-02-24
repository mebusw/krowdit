<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
  // 表单一
    <form name="form1" action="<%=request.getContextPath()%>/login/pengo" method="get">
    
    <input type="submit" value="提交" />
    <br /> <br />
    <a href="<%=request.getContextPath()%>/login/pengo">超链接提交</a>
    </form>
    
    // 表单二
    <form name="form2" action="<%=request.getContextPath()%>/welcome" method="get">
    
    <input type="submit" value="进入欢迎界面" />
    <br /> <br />
    <a href="<%=request.getContextPath()%>/welcome">进入欢迎界面</a>
    </form>
    
    <form name="form3" action="<%=request.getContextPath()%>/krowd/ryan" method="get">
    <input type="submit" value="get krowd/user" />
    <br /> <br />
    </form>
    
    <form name="form4" action="<%=request.getContextPath()%>/krowd/11/22" method="post">
    <input type="submit" value="post krowd/lid/kid" />
    <br /> <br />
    </form>
    
    <form name="form5" action="<%=request.getContextPath()%>/blog/cindy" method="post">
    <input type="submit" value="put blog/user" />
    <input type="hidden" name="_method" value="put"/>
    <br /> <br />
    </form>
            
  </body>
</html>
