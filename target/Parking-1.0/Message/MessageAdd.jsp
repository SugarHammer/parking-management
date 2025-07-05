<%--
  Created by IntelliJ IDEA.
  User: myy
  Date: 2020/12/26
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.*" errorPage="../Admin/_Error.jsp"%>
<%@ page import="cn.parking.DAO.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/Style/AddStyle.css"/>
    <script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
</head>
<body>
<form action="<%=path %>/MessageHandle?type=3" method="post" class="Form">
    <table style=" margin:50px auto;">
        <tbody>
        <tr>
            <td>
                <label>输入您的留言：</label>
            </td>
        </tr>
        <tr>
            <td>
                <textarea cols="50" rows="10" name="text">在这里输入您的内容.</textarea>
            </td>
        </tr>
        </tbody>

        <tfoot>
        <tr>
            <td>
                <input type="submit" value="确定" id="btnSure"/>
                &nbsp;
                <input type="reset" value="取消" id="btnCancel"/>
            </td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
