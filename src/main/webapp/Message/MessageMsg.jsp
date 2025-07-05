<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: myy
  Date: 2020/12/26

  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.*" errorPage="/Admin/_Error.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();//获取项目名称
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=path %>/Style/MsgStyle.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
    <title>Insert title here</title>
    <script>
        $(function()
        {
            $(".btnGo").click(function()
            {
                if($("[name=htype]").val()=="4")
                {
                    location.href="<%=basePath%>MessageHandle?type=4&page="+($(".txtPage").val()==""?1:$(".txtPage").val());
                }
                else
                {
                    location.href="<%=basePath%>MessageHandle?type=5&page="+($(".txtPage").val()==""?1:$(".txtPage").val())+"&condition="+$("[name=hcondition]").val()+"&value="+$("[name=hvalue]").val();
                }
            });
        });
    </script>
</head>
<body>
<div class="div_container">
    <div class="searchDiv">
        <form action="<%=basePath%>MessageHandle" method="get">
            <span>查询条件：</span>
            <select name="condition">
                <option value='message_id'>留言编号</option>
            </select>

            <span>查询值：</span>
            <input type="hidden" name="type" value="5" />
            <input type="text" name="value" />
            <input type="submit" value="查询 "/>
        </form>
    </div>
    <!-- searchDiv End -->

    <table>
        <thead>
        <tr>
            <th>留言编号</th>
            <th>用户昵称</th>
            <th>留言内容</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <%
            response.setCharacterEncoding("UTF-8");//设置输出数据的编码格式
            request.setCharacterEncoding("UTF-8");//设置获取数据的编码格式

            List<Object> list=(List<Object>)request.getAttribute("list");//获取servlet端转发的list数据列表
            out.write("<input type='hidden' name='hcondition' value='"+(request.getAttribute("condition")!=null?request.getAttribute("condition").toString():"")+"' />");
            out.write("<input type='hidden' name='hvalue' value='"+(request.getAttribute("value")!=null?request.getAttribute("value").toString():"")+"' />");
            if(list!=null) //循环数据列表，生成表格行
            {
                for(int i=0;i<list.size();i++)
                {
                    Object[] obj;
                    obj=(Object[])list.get(i);
                    out.print("<tr><td>"+obj[0]+"</td><td>"+obj[1]+"</td><td>"+obj[2]+"</td><td><a href='"+path+"/Message/MessageEdit.jsp?message_id="+obj[0]+"' class='a_edit'>编辑</a><a href='"+path+"/MessageHandle?type=1&message_id="+obj[0]+"' class='a_del' onclick='return confirm(\"是否确认删除？\")'>删除</a></td></tr>");
                }
            }

        %>
        </tbody>
    </table>
</div>
</body>
</html>

