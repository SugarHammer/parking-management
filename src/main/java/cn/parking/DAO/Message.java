package cn.parking.DAO;
import cn.parking.DBUtil.SQLUtil;
import java.util.List;

/*
 * @Author:cxs
 * @Date:2020/12/26  16:10
 * @Version 1.0
 * */
public class Message {
    //添加留言
    public int addmessage(String user_id,String text){
        String sqlCmd = "insert into message values(null,'"+user_id+"','"+text+"')";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }
    //修改留言
    public int updatemessage(int message_id,String text){
        String sqlCmd = "update message set text = '"+text+"' where message_id = '"+message_id+"'";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }
    //删除留言
    public int deletebyid(int message_id){
        String sqlCmd = "delete from message where message_id = '"+message_id+"'";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }
    //查询留言
    public List<Object> queryall(){
        String sqlCmd = "select *from message";
        return SQLUtil.executeQuery(sqlCmd, null);
    }
    //根据id查询
    public List<Object> queryallbyid(int id){
        String sqlCmd = "select *from message where message_id = '"+id+"'";
        return SQLUtil.executeQuery(sqlCmd, null);
    }
}
