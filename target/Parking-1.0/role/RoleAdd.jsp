<%@page import="java.util.*" errorPage="../Admin/_Error.jsp"%>
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
	<form action="<%=path %>/RoleHandle" method="post" class="Form">
		<table style=" margin:50px auto;"> 
	            <tbody>
				<input type="hidden" name="type" value="3">
			<tr><td>角色编号：</td><td><input type="text" name="role_id"  required /></td></tr>
			<tr><td>角色名称：</td><td><input type="text" name="role_name"  required /></td></tr>

	            </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="reset" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
       </form>
</body>
</html>
