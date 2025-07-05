<%--
  Created by IntelliJ IDEA.
  User: myy
  Date: 2020/12/23
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<%
    try{
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/parking?useUnicode=true&characterEncoding=utf8";
        String user="root";
        String password="root";
        Connection conn=DriverManager.getConnection(url,user,password);
        // Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");
        // Statement stmt = conn.createStatement();
        //String sql="select * from student";
        Statement stmt = conn.createStatement();               //注意Statement与PreparedStatement的区别
        String sql="select role_id,role_name from role";
        ResultSet rs=stmt.executeQuery(sql);
%>
<table>
    <thead>
    <tr>
        <td>role_id</td>
        <td>role_name</td>

    </tr>
    </thead>
    <tbody>
    <% while(rs.next()) {
    %>
    <tr>
        <td><%=rs.getString("role_id")%> </td>
        <td><%=rs.getString("role_name")%> </td>

    </tr>


    <%
        }
    %>
    </tbody>

</table>
<%
        rs.close();
        stmt.close();
        conn.close();
    }
    catch (Exception e){
        e.printStackTrace();
    }
%>

</body>
</html>

