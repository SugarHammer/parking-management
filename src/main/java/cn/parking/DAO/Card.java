package cn.parking.DAO;
import cn.parking.DBUtil.SQLUtil;
import java.util.List;
public class Card {
    //获取IC卡表信息列表
    public List<Object> getEntity() {
        String sqlCmd="select *from card";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }
    //分页查询、获取分页后IC卡表信息列表
    public List<Object> getEntity(int page) {
        int size=(page-1)*15;
        String sqlCmd="select *from v_card limit "+size+",15";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }
    //根据查询条件sqlWhere获取分页后IC卡表信息列表
    public List<Object> getEntityByWhere(String sqlWhere,int page)
    {
        int size=(page-1)*15;
        String sqlCmd="select *from v_card where "+sqlWhere+" limit "+ size+",15";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }
    //删除IC卡表信息
    public int deleteEntity(String card_id) {
        String sqlCmd="delete from card where card_id='"+card_id+"'";
        return SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
    }
    //根据IC卡表编号获取IC卡表信息
    public List<Object> getEntityById(String card_id) {
        String sqlCmd="select *From v_card where card_id='"+card_id+"'";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }
    //更新IC卡表信息
    public int updateEntity(String card_id,String seat_id,String user_name,String user_gender,String user_addr,String car_num) {
        String sqlCmd="Update card set seat_id='" + seat_id + "',user_name='" + user_name + "',user_gender='" + user_gender + "',user_addr='" + user_addr + "',car_num='" + car_num + "' where card_id='"+card_id+"'";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }
    //插入IC卡表信息
    public int insertEntity(String card_id,String seat_id,String user_name,String user_gender,String user_addr,String car_num) {
        String sqlCmd="Insert into card values('" + card_id + "','" + seat_id + "','" + user_name + "','" + user_gender + "','" + user_addr + "','"+car_num+"')";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }
    //检查插入主键是否重复
    public boolean checkExist(String card_id) {
        String sqlCmd="select count(*) from v_card where card_id='"+card_id+"'";
        if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) ) {
            return true;
        }
        return false;
    }
    //获取分页总数
    public Object getPageCount() {
        String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM v_card ";
        return SQLUtil.excuteScalar(sqlCmd, null);
    }
    //根据查询条件获取分页总数
    public Object getPageCountByWhere(String sqlWhere) {
        String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM v_card where "+sqlWhere;
        return SQLUtil.excuteScalar(sqlCmd, null);
    }
}
