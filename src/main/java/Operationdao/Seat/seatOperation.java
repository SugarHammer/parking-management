package Operationdao.Seat;
import cn.parking.DAO.Seat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;
public class seatOperation {

    static Seat seat=new Seat();
    //删除数据操作
    public static void deleteEntity(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String path = request.getContextPath();//获取项目名称
        String seat_id=request.getParameter("seat_id");//获取前台通过get方式传过来的JId
        seat.deleteEntity(seat_id);//执行删除操作
        response.sendRedirect(path+"/SeatHandle?type=4");//删除成功后跳转至管理页面
    }

    //更新数据操作
    public static void updateEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        String path = request.getContextPath();//获取项目名称
        String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
        String seat_num=new String(request.getParameter("seat_num").getBytes("ISO8859_1"),"UTF-8");
        String seat_section=new String(request.getParameter("seat_section").getBytes("ISO8859_1"),"UTF-8");
        String seat_state=new String(request.getParameter("seat_state").getBytes("ISO8859_1"),"UTF-8");
        String seat_tag=new String(request.getParameter("seat_tag").getBytes("ISO8859_1"),"UTF-8");

        if(seat.updateEntity(seat_id,seat_num,seat_section,seat_state,seat_tag)==1)
        {
            try {
                response.sendRedirect(path+"/SeatHandle?type=4");//成功更新数据后跳转至SeatMsg.jsp页面
            } catch (IOException e) {
                e.printStackTrace();//异常处理
            }
        }
    }

    //插入数据操作
    public static void insertEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss");
        String seat_id=dateFormat.format(new Date());
        String seat_num=new String(request.getParameter("seat_num").getBytes("ISO8859_1"),"UTF-8");
        String seat_section=new String(request.getParameter("seat_section").getBytes("ISO8859_1"),"UTF-8");
        String seat_state="0";
        String seat_tag=new String(request.getParameter("seat_tag").getBytes("ISO8859_1"),"UTF-8");

        if(!seat.checkExist(seat_id))
        {
            if(seat.insertEntity(seat_id,seat_num,seat_section,seat_state,seat_tag)==1)
            {
                out.write("<script>alert('数据添加成功！'); location.href = '"+path+"/SeatHandle?type=4';</script>");
            }
            else {
                out.write("<script>alert('数据添失败！'); location.href = '"+path+"/SeatHandle?type=4';</script>");
            }
        }
        else {
            out.write("<script>alert('主键重复，数据添加失败！'); location.href = '"+path+"/SeatHandle?type=4';</script>");
        }
    }

    //获取对象所有数据列表
    public static void getEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
        int totalPage=Integer.parseInt(seat.getPageCount().toString()) ;//获取分页总数
        List<Object> list=seat.getEntity(page);//获取数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
        request.getRequestDispatcher("/seat/SeatMsg.jsp").forward(request, response);//请求转发
    }

    //根据查询条件获取对象所有数据列表
    public static void getEntityByWhere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        String condition=request.getParameter("condition");//获取查询字段的名称
        //String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
        String value = request.getParameter("value");
        if(value.equals("闲置")){
            String where=condition+"=0";
            System.out.println("where="+where);
            int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
            int wherePage=Integer.parseInt(seat.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
            List<Object> list=seat.getEntityByWhere(where, page);//获取查询后的数据列表
            request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
            request.setAttribute("wherePage",wherePage );
            request.setAttribute("condition",condition);
            request.setAttribute("value",value);
            request.getRequestDispatcher("/seat/SeatMsg.jsp").forward(request, response);
        }if(value.equals("占用")){
        String where=condition+"=1";
        System.out.println("where="+where);
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
        int wherePage=Integer.parseInt(seat.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
        List<Object> list=seat.getEntityByWhere(where, page);//获取查询后的数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("wherePage",wherePage );
        request.setAttribute("condition",condition);
        request.setAttribute("value",value);
        request.getRequestDispatcher("/seat/SeatMsg.jsp").forward(request, response);
    }else{
        String where=condition+"=\""+value+"\"";//拼接查询字符串
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
        int wherePage=Integer.parseInt(seat.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
        List<Object> list=seat.getEntityByWhere(where, page);//获取查询后的数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("wherePage",wherePage );
        request.setAttribute("condition",condition);
        request.setAttribute("value",value);
        request.getRequestDispatcher("/seat/SeatMsg.jsp").forward(request, response);
    }
        //String where=condition+"=\""+value+"\"";//拼接查询字符串
    }
}
