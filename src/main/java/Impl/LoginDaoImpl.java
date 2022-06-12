package Impl;

import dao.LoginDao;
import dao.MyConnection;
import entity.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDaoImpl implements LoginDao {
    public int insert(LoginBean login){
        return 0;
    }
    public int delete(LoginBean login){
        return 0;
    }
    public int delete(int loginId){
        return 0;
    }
    public int delete(String loginName){
        return 0;
    }
    public int update(LoginBean login){
        return 0;
    }
    public int update(int loginId){
        return 0;
    }
    public List<LoginBean> select(){
        return null;
    }
    public LoginBean select(int loginId){
        return null;
    }

    public LoginBean select(String LoginName){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM LOGINS WHERE LoginName = ?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setString(1,LoginName);
            rs = ps.executeQuery();
            if (rs.next()){
                LoginBean login = new LoginBean();
                int count = 1;
                login.setLoginId(rs.getInt(count++));
                login.setLoginName(rs.getString(count++));
                login.setPassword(rs.getString(count++));
                login.setEmail(rs.getString(count++));
                login.setRegisterDateTime(rs.getTimestamp(count++));
                login.setRegisterIp(rs.getString(count++));
                login.setLastLoginDateTime(rs.getTimestamp(count++));
                login.setLastLoginIp(rs.getString(count++));
                login.setLoginTime(rs.getInt(count++));
                login.setLoginType(rs.getString(count++));
                //System.out.println(login);
                return login;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("获取登录信息时出错");
        }finally {
            MyConnection.close(rs,ps,conn);
        }

        return null;
    }
}
