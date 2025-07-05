			package ServletHandle;

			import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

			import javax.servlet.ServletException;
			import javax.servlet.annotation.WebServlet;
			import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

			import Operationdao.Role.roleOperation;
			import Operationdao.Seat.seatOperation;

import java.text.SimpleDateFormat;
import java.util.*;
			@WebServlet(name="SeatHandle",value = "/SeatHandle")
			public class SeatHandle extends HttpServlet {

			HttpServletRequest request;
			HttpServletResponse response;
			Operationdao.Seat.seatOperation roleOperation = new seatOperation();
	
			//通过表单get方式传值 将进入doGet函数（method="get"）
			public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
					this.response=response;
					this.request=request;
					int handleType=Integer.parseInt(request.getParameter("type").toString());
					switch (handleType) {
					case 1://类型1代表删除表中的数据
						seatOperation.deleteEntity(request,response);
						break;
					case 4://类型4代表获取表中信息
						seatOperation.getEntity(request,response);
						break;
					case 5://类型5代表根据查询条件获取表中信息
						seatOperation.getEntityByWhere(request,response);
						break;
					default:
						break;
					}
			}
	
			//通过表单post方式传值 将进入doPost函数（method="post"）
			public void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
					this.request=request;
					this.response=response;
					int handleType=Integer.parseInt(request.getParameter("type").toString());//将前台页面传过来的type类型转化成整型
					switch (handleType) {
					case 2://类型2代表更新表中的数据
						seatOperation.updateEntity(request,response);
						break;
					case 3://类型3代表向表中添加数据
						seatOperation.insertEntity(request,response);
						break;
					default:
						break;
					}
			}


		}
