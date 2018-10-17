<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*, java.text.SimpleDateFormat" %>

<html>
<body>
<h3>Hello World! 你好世界</h3>
<%
    out.println("测试中文2");

    Date date = new Date();
    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    out.println(time.format(date));
%>

<form action="/demoServlet" method="post">
    <input type="text" name="a" value="anything测试"/>
    <button type="submit">提交</button>
</form>

</body>
</html>