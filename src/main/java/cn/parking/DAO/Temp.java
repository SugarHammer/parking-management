        package cn.parking.DAO;

        import cn.parking.DBUtil.SQLUtil;

        import java.util.List;


        public class Temp {

		//获取零时车主出入记录表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from temp";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
               //获取分页后零时车主出入记录表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from temp limit "+size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
               //根据查询条件sqlWhere获取分页后零时车主出入记录表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from temp where "+sqlWhere+" limit "+ size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
                //删除零时车主出入记录表信息
                public int deleteEntity(String temp_id)
                {
                    String sqlCmd="delete from temp where temp_id='"+temp_id+"'";
                    return SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
                }
                
                //根据零时车主出入记录表编号获取零时车主出入记录表信息
                public List<Object> getEntityById(String temp_id)
                {
                    String sqlCmd="select *From temp where temp_id='"+temp_id+"'";
                    return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
                //更新零时车主出入记录表信息
                public int updateEntity(String temp_id,String card_id,String car_num,String entry_date,String entry_time,String out_date,String out_time,String temp_money)
                {
                    String sqlCmd="Update temp set card_id='" + card_id + "',car_num='" + car_num + "',entry_date='" + entry_date + "',entry_time='" + entry_time + "',out_date='" + out_date + "',out_time='" + out_time + "',temp_money='" + temp_money + "' where temp_id='"+temp_id+"'";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                //插入零时车主出入记录表信息
                public int insertEntity(String temp_id,String card_id,String car_num,String entry_date,String entry_time,String out_date,String out_time,String temp_money)
                {
                    String sqlCmd="Insert into temp values('" + temp_id + "','" + card_id + "','" + car_num + "','" + entry_date + "','" + entry_time + "'," + out_date + "," + out_time + ","+temp_money+")";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                //检查插入主键是否重复
                public boolean checkExist(String card_id)
                {
                    String sqlCmd="select count(*) from temp where card_id='"+card_id+"'";
                    if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
                    {
                        return true;
                    }
                    return false;
                }

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM temp ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM temp where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
            
        }
