package Operationdao.Temp;
import cn.parking.DAO.Temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;
public class tempOperation {
    Temp temp=new Temp();
    //删除数据操作
    public void deleteEntity(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String path = request.getContextPath();//获取项目名称
        String temp_id=request.getParameter("temp_id");//获取前台通过get方式传过来的JId
        temp.deleteEntity(temp_id);//执行删除操作
        response.sendRedirect(path+"/TempHandle?type=4");//删除成功后跳转至管理页面
    }

    //更新数据操作
    public void updateEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        String path = request.getContextPath();//获取项目名称
        String temp_id=new String(request.getParameter("temp_id").getBytes("ISO8859_1"),"UTF-8");
        String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
        String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
        String entry_date=new String(request.getParameter("entry_date").getBytes("ISO8859_1"),"UTF-8");
        String entry_time=new String(request.getParameter("entry_time").getBytes("ISO8859_1"),"UTF-8");
        String out_date=new String(request.getParameter("out_date").getBytes("ISO8859_1"),"UTF-8");
        String out_time=new String(request.getParameter("out_time").getBytes("ISO8859_1"),"UTF-8");
        String temp_money=new String(request.getParameter("temp_money").getBytes("ISO8859_1"),"UTF-8");

        if(temp.updateEntity(temp_id,card_id,car_num,entry_date,entry_time,out_date,out_time,temp_money)==1)
        {
            try {
                response.sendRedirect(path+"/TempHandle?type=4");//成功更新数据后跳转至TempMsg.jsp页面
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
        String temp_id=dateFormat.format(new Date());
        //String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
        //String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
        String card_id = request.getParameter("card_id");
        String car_num = request.getParameter("car_num");
        SimpleDateFormat dFormat  =new    SimpleDateFormat("yyyy-MM-dd");
        String entry_date=dFormat.format(new Date());
        SimpleDateFormat tFormat =new    SimpleDateFormat("HH:mm:ss");
        String entry_time=tFormat.format(new Date());
        String out_date=null;
        String out_time=null;
        String temp_money="0";

        if(!temp.checkExist(card_id))
        {
            System.out.println("11111");
            if((card_id!=null &&card_id!="")&&(car_num!=null&&car_num!="")){
                if(temp.insertEntity(temp_id,card_id,car_num,entry_date,entry_time,out_date,out_time,temp_money)==1)
                {	System.out.println("card_id="+card_id);
                    System.out.println("car_num="+car_num);
                    out.write("<script>alert('数据添加成功！'); location.href = '"+path+"/TempHandle?type=4';</script>");
                }
                else {
                    out.write("<script>alert('数据添失败！'); location.href = '"+path+"/TempHandle?type=4';</script>");
                }
            }
            else{
                out.write("<script>alert('临时IC卡号或者车牌号不能为空'); location.href = '"+path+"/TempHandle?type=4';</script>");
            }

        }
        else {
            out.write("<script>alert('主键重复，数据添加失败！'); location.href = '"+path+"/TempHandle?type=4';</script>");
        }
    }

    //获取对象所有数据列表
    public void getEntity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        request.setCharacterEncoding("UTF-8");
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
        int totalPage=Integer.parseInt(temp.getPageCount().toString()) ;//获取分页总数
        List<Object> list=temp.getEntity(page);//获取数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
        request.getRequestDispatcher("/temp/TempMsg.jsp").forward(request, response);//请求转发
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
        int wherePage=Integer.parseInt(temp.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
        List<Object> list=temp.getEntityByWhere(where, page);//获取查询后的数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("wherePage",wherePage );
        request.setAttribute("condition",condition);
        request.setAttribute("value",value);
        request.getRequestDispatcher("/temp/TempMsg.jsp").forward(request, response);
    }
}
