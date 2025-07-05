package Operationdao.Role;
import cn.parking.DAO.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class roleOperation {
    Role role=new Role();
    //删除数据操作
    public void deleteEntity(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String role_id=request.getParameter("role_id");//获取前台通过get方式传过来的JId
        role.deleteEntity(role_id);//执行删除操作
        response.sendRedirect(basePath+"RoleHandle?type=4");//删除成功后跳转至管理页面
    }

    //更新数据操作
    public void updateEntity(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
        String role_name=new String(request.getParameter("role_name").getBytes("ISO8859_1"),"UTF-8");

        if(role.updateEntity(role_id,role_name)==1)
        {
            try {
                response.sendRedirect(basePath+"RoleHandle?type=4");//成功更新数据后跳转至RoleMsg.jsp页面
            } catch (IOException e) {
                e.printStackTrace();//异常处理
            }
        }
    }

    //插入数据操作
    public void insertEntity(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
        String role_name=new String(request.getParameter("role_name").getBytes("ISO8859_1"),"UTF-8");

        if(!role.checkExist(role_id))
        {
            if(role.insertEntity(role_id,role_name)==1)
            {
                out.write("<script>alert('数据添加成功！'); location.href = '"+path+"/RoleHandle?type=4';</script>");
            }
            else {
                out.write("<script>alert('数据添失败！'); location.href = '"+path+"RoleHandle?type=4';</script>");
            }
        }
        else {
            out.write("<script>alert('主键重复，数据添加失败！'); location.href = '"+basePath+"RoleHandle?type=4';</script>");
        }
    }

    //获取对象所有数据列表
    public void getEntity(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.setCharacterEncoding("UTF-8");
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
        int totalPage=Integer.parseInt(role.getPageCount().toString()) ;//获取分页总数
        List<Object> list=role.getEntity(page);//获取数据列表
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
        request.getRequestDispatcher("/role/RoleMsg.jsp").forward(request, response);//请求转发
    }

    //根据查询条件获取对象所有数据列表
    public void getEntityByWhere(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getContextPath();//获取项目名称
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.setCharacterEncoding("UTF-8");
        String condition=request.getParameter("condition");//获取查询字段的名称
        System.out.println(condition);
        //String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
        String value = request.getParameter("value");
        System.out.println("value  "+value);
        String where=condition+"=\""+value+"\"";//拼接查询字符串
        System.out.println("where  "+where);
        int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
        //System.out.println("page +"+page );
        int wherePage=Integer.parseInt(role.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
        List<Object> list=role.getEntityByWhere(where, page);//获取查询后的数据列表
        System.out.println();
        request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
        request.setAttribute("wherePage",wherePage );
        request.setAttribute("condition",condition);
        request.setAttribute("value",value);
        request.getRequestDispatcher("/role/RoleMsg.jsp").forward(request, response);
    }
}

