package cn.parking.DAO;

import cn.parking.DBUtil.SQLUtil;

import java.util.List;

public class Fixed {

		//获取固定车主出入记录表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from fixed";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
         //获取分页后固定车主出入记录表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from v_fixed limit "+size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
		
		 //获取未出场车辆
		public List<Object> getNoOut(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from v_fixed where out_date='1111-11-11' limit "+size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
        //根据查询条件sqlWhere获取分页后固定车主出入记录表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from v_fixed where "+sqlWhere+" limit "+ size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //删除固定车主出入记录表信息
        public int deleteEntity(String fixed_id)
        {
            String sqlCmd="delete from fixed where fixed_id='"+fixed_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
        }
        
        //根据固定车主出入记录表编号获取固定车主出入记录表信息
        public List<Object> getEntityById(String fixed_id)
        {
            String sqlCmd="select *From v_fixed where fixed_id='"+fixed_id+"'";
            return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //更新固定车主出入记录表信息
        public int updateEntity(String fixed_id,String card_id,String entry_date,String entry_time,String out_date,String out_time)
        {
            String sqlCmd="Update fixed set card_id='" + card_id + "',entry_date='" + entry_date + "',entry_time='" + entry_time + "',out_date='" + out_date + "',out_time='" + out_time + "' where fixed_id='"+fixed_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        public int setOut(String fixed_id,String out_date,String out_time )
        {
        	String sqlCmd="update fixed set out_date='"+out_date+"',out_time='"+out_time+"' where fixed_id='"+fixed_id+"'";
        	 return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        //插入固定车主出入记录表信息
        public int insertEntity(String fixed_id,String card_id,String entry_date,String entry_time,String out_date,String out_time)
        {
            String sqlCmd="Insert into fixed values('" + fixed_id + "','" + card_id + "','" + entry_date + "','" + entry_time + "','" + out_date + "','"+out_time+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        //检查插入主键是否重复
        public boolean checkExist(String fixed_id)
        {
            String sqlCmd="select count(*) from v_fixed where fixed_id='"+fixed_id+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM v_fixed ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM v_fixed where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
            
        }
