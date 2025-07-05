        package cn.parking.DAO;

        import cn.parking.DBUtil.SQLUtil;

        import java.util.List;

        public class Role {

		//获取角色表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from role";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
               //获取分页后角色表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select * from role limit "+size+",15";
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
               //根据查询条件sqlWhere获取分页后角色表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select * from role where "+sqlWhere+" limit "+ size+",15";
			System.out.println("sqlCmd =="+sqlCmd);
			return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
                //删除角色表信息
                public int deleteEntity(String role_id)
                {
                    String sqlCmd="delete from role where role_id='"+role_id+"'";
                    return SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
                }
                
                //根据角色表编号获取角色表信息
                public List<Object> getEntityById(String role_id)
                {
                    String sqlCmd="select *From role where role_id='"+role_id+"'";
                    System.out.println(sqlCmd);
                    return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
              //根据角色表编号获取角色表信息
                public List<Object> getEntityByName(String role_name)
                {
                    String sqlCmd="select * From role where role_name='"+role_name+"'";
                    System.out.println(sqlCmd);
                    return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
                //更新角色表信息
                public int updateEntity(String role_id,String role_name)
                {
                    String sqlCmd="Update role set role_name='" + role_name + "' where role_id='"+role_id+"'";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                //插入角色表信息
                public int insertEntity(String role_id,String role_name)
                {
                    String sqlCmd="Insert into role values('" + role_id + "','"+role_name+"')";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                //检查插入主键是否重复
                public boolean checkExist(String role_id)
                {
                    String sqlCmd="select count(*) from role where role_id='"+role_id+"'";
                    if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
                    {
                        return true;
                    }
                    return false;
                }

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM role ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM role where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
            
        }
