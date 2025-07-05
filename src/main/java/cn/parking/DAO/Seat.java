        package cn.parking.DAO;

        import cn.parking.DBUtil.SQLUtil;

        import java.util.List;

        public class Seat {

		//获取车位表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from seat";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
		
		//获取未分配的车位
		public List<Object> getNoUseSeat()
		{
			String sqlCmd="SELECT *FROM seat WHERE seat_id NOT IN(SELECT seat_id FROM card)";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
        //获取分页后车位表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from seat limit "+size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
        //根据查询条件sqlWhere获取分页后车位表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from seat where "+sqlWhere+" limit "+ size+",15";
			System.out.println("sqlWhere="+sqlWhere);
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //删除车位表信息
        public int deleteEntity(String seat_id)
        {
            String sqlCmd="delete from seat where seat_id='"+seat_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
        }
        
        //根据车位表编号获取车位表信息
        public List<Object> getEntityById(String seat_id)
        {
            String sqlCmd="select *From seat where seat_id='"+seat_id+"'";
            return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //更新车位表信息
        public int updateEntity(String seat_id,String seat_num,String seat_section,String seat_state,String seat_tag)
        {
            String sqlCmd="Update seat set seat_num='" + seat_num + "',seat_section='" + seat_section + "',seat_state='" + seat_state + "',seat_tag='" + seat_tag + "' where seat_id='"+seat_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }

        //插入车位表信息
        public int insertEntity(String seat_id,String seat_num,String seat_section,String seat_state,String seat_tag)
        {
            String sqlCmd="Insert into seat values('" + seat_id + "','" + seat_num + "','" + seat_section + "','" + seat_state + "','"+seat_tag+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        //检查插入主键是否重复
        public boolean checkExist(String seat_id)
        {
            String sqlCmd="select count(*) from seat where seat_id='"+seat_id+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM seat ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM seat where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
        }
