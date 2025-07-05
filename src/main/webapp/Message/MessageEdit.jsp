<%--
  Created by IntelliJ IDEA.
  User: myy
  Date: 2020/12/26
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.*" errorPage="/Admin/_Error.jsp"%>
<%@ page import="cn.parking.DAO.User" %>
<%@ page import="cn.parking.DAO.Message" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../Style/EditStyle.css"/>
    <script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
    <script type="text/javascript">
        $(function()
        {
            //取消按钮点击事件
            $("#btnCancel").click(function()
            {
                location.href="<%=path %>/MessageHandle?type=4";//点击后跳转至UserHandle Servlet页面
            });
        });
    </script>

</head>
<body>
<%
    response.setCharacterEncoding("UTF-8");//设置输出编码格式
    String message_id=request.getParameter("message_id").toString();//获取url传过来的user_id
    Message message=new Message();//实例化User对象
    List<Object> messageList=message.queryallbyid(Integer.parseInt(message_id));//根据ID获取User数据
    Object[] obj=(Object[])messageList.get(0);//将List数据转换成Object[]

%>
<form action="<%=path %>/MessageHandle?type=2" method="post">
    <table style=" margin:50px auto;">
        <tbody>
        <tr><td>留言编号：</td><td><input type="text" name="message_id" value="<%=obj[0] %>" readonly="readonly" /></td></tr>
        <tr><td>用户名：</td><td><input type="text" name="user_name" value="<%=obj[1] %>" required /></td></tr>
        <tr><td>留言内容：</td><td><input type="text" name="real_name" value="<%=obj[2] %>" required /></td></tr>
        </tbody>

        <tfoot>
        <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
        </tfoot>
    </table>
</form>
</body>
</html>

