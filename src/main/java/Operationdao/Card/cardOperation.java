package Operationdao.Card;
import cn.parking.DAO.Card;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;

public class cardOperation {
    Card card=new Card();
    //删除数据操作
    public void deleteEntity(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String path = request.getContextPath();//获取项目名称
        String card_id=request.getParameter("card_id");//获取前台通过get方式传过来的JId
        card.deleteEntity(card_id);//执行删除操作
        response.sendRedirect(path+"/CardHandle?type=4");//删除成功后跳转至管理页面
    }

    //更新数据操作
    public void updateEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        String path = request.getContextPath();//获取项目名称
        String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
        String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
        String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
        String user_gender=new String(request.getParameter("user_gender").getBytes("ISO8859_1"),"UTF-8");
        String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
        String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");

        if(card.updateEntity(card_id,seat_id,user_name,user_gender,user_addr,car_num)==1)
        {
            try {
                response.sendRedirect(path+"/CardHandle?type=4");//成功更新数据后跳转至CardMsg.jsp页面
            } catch (IOException e) {
                e.printStackTrace();//异常处理
            }
        }
    }

    //插入数据操作
    public void insertEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss");
        String card_id=dateFormat.format(new Date());
        String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
        String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
        String user_gender=new String(request.getParameter("user_gender").getBytes("ISO8859_1"),"UTF-8");
        String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
        String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");

        if(!card.checkExist(card_id))
        {
            if(card.insertEntity(card_id,seat_id,user_name,user_gender,user_addr,car_num)==1)
            {
                out.write("<script>alert('数据添加成功！'); location.href = '"+path+"/CardHandle?type=4';</script>");
            }
            else {
                out.write("<script>alert('数据添失败！'); location.href = '"+path+"/CardHandle?type=4';</script>");
            }
        }
        else {
            out.write("<script>alert('主键重复，数据添加失败！'); location.href = '"+path+"/CardHandle?type=4';</script>");
        }
    }

    //获取对象所有数据列表
    public void getEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
        int totalPage=Integer.parseInt(card.getPageCount().toString()) ;//获取分页总数
        List<Object> list=card.getEntity(page);//获取数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
        request.getRequestDispatcher("/card/CardMsg.jsp").forward(request, response);//请求转发
    }

    //根据查询条件获取对象所有数据列表
    public void getEntityByWhere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        String condition=request.getParameter("condition");//获取查询字段的名称
        //String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
        String value = request.getParameter("value");
        String where=condition+"=\""+value+"\"";//拼接查询字符串
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
        int wherePage=Integer.parseInt(card.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
        List<Object> list=card.getEntityByWhere(where, page);//获取查询后的数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("wherePage",wherePage );
        request.setAttribute("condition",condition);
        request.setAttribute("value",value);
        request.getRequestDispatcher("/card/CardMsg.jsp").forward(request, response);
    }
}
