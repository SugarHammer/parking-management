package Operationdao.message;
import cn.parking.DAO.Message;

import cn.parking.DAO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.*;

public class messageOperation {
    static Message message=new Message();
    static User user=new User();
    //更改密码
    public void chagePwd(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String userId=session.getAttribute("user_id").toString();
        String oldPwd=new String(request.getParameter("OldPwd").getBytes("ISO8859_1"),"UTF-8");
        String newPwd=new String(request.getParameter("NewPwd").getBytes("ISO8859_1"),"UTF-8");
        if(user.checkPwd(userId, oldPwd))
        {
            if(user.updataPwd(userId, newPwd))
            {
                out.write("<script>alert('密码更改成功~~~');location.href='"+path+"/Common/UserInfo.jsp'</script>");
            }
            else {
                out.write("<script>alert('密码更改失败~~~');location.href='"+basePath+"Common/ChagePwd.jsp'</script>");
            }
        }
        else {
            out.write("<script>alert('原始密码错误~~~');location.href='"+path+"/Common/ChagePwd.jsp'</script>");
        }
    }

    //用户注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        String UserId=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
        String RoleId=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
        String UserName=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
        String RealName=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
        String UserPwd=new String(request.getParameter("user_pwd1").getBytes("ISO8859_1"),"UTF-8");
        String UserPhone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");

        if(!user.checkExist(UserId))
        {
            if(user.insertEntity(UserId,RoleId,UserName,RealName,UserPwd,UserPhone)==1)
            {
                SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss");
                String AId=dateFormat.format(new Date());
                //Account account=new Account();
                //account.insertEntity(AId, UserId, "0","2015-12-30");
                out.write("<script>alert('恭喜你，注册成功~'); location.href = '"+path+"/login.jsp';</script>");
            }
        }
        else {
            out.write("<script>alert('您注册的登陆账号已存在，请重新注册！'); location.href = '"+path+"/login.jsp';</script>");
        }
    }

    //删除数据操作
    public static void deleteEntity(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String message_id=request.getParameter("message_id");//获取前台通过get方式传过来的JId
        message.deletebyid(Integer.parseInt(message_id));//执行删除操作
        response.sendRedirect(basePath+"MessageHandle?type=4");//删除成功后跳转至管理页面
    }

    //更新数据操作
    public static void updateEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String message_id=request.getParameter("message_id");
        String text=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");

        if(message.updatemessage(Integer.parseInt(message_id),text)==1)
        {
            try {
                if(request.getSession().getAttribute("role_id").toString().equals("r001"))
                {
                    response.sendRedirect(basePath+"MessageHandle?type=4");//成功更新数据后跳转至UserInfo.jsp页面
                }
                else {
                    response.sendRedirect(basePath+"MessageHandle?type=4");//成功更新数据后跳转至UserInfo.jsp页面
                }
            } catch (IOException e) {
                e.printStackTrace();//异常处理
            }
        }
    }

    //插入数据操作
    public static void insertEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        HttpSession session = request.getSession();
        String user_id= (String) session.getAttribute("user_id");
        String text=new String(request.getParameter("text").getBytes("ISO8859_1"),"UTF-8");
            if(1 == message.addmessage(user_id, text))
                out.write("<script>alert('数据添加成功！'); location.href = '"+basePath+"MessageHandle?type=4';</script>");
            else {
                out.write("<script>alert('数据添失败！'); location.href = '"+basePath+"MessageHandle?type=4';</script>");
            }
        }

    //获取对象所有数据列表
    public static void getEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
        List<Object> list=message.queryall();
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("totalPage",1 );//将totalPage存放到request对象中，用于转发给前台页面使用
        request.getRequestDispatcher("/Message/MessageMsg.jsp").forward(request, response);//请求转发
    }

    //根据查询条件获取对象所有数据列表
    public static void getEntityByWhere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        String condition=request.getParameter("condition");//获取查询字段的名称
        //String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
        String value = request.getParameter("value");
        String where=condition+"=\""+value+"\"";//拼接查询字符串
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
        List<Object> list=message.queryallbyid(Integer.valueOf(value));//获取查询后的数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("condition",condition);
        request.setAttribute("value",value);
        request.getRequestDispatcher("/Message/MessageMsg.jsp").forward(request, response);
    }
}
