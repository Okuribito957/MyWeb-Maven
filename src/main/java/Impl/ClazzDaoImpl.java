package Impl;

import dao.ClazzDao;
import dao.MyConnection;
import entity.ClazzBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClazzDaoImpl implements ClazzDao {

    public int insert(ClazzBean clazz){
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;

        String strSql = "INSERT INTO Clazzes values(NULL,?,?,?,?,?)";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setString(1, clazz.getNumber());
            ps.setString(2, clazz.getName());
            ps.setDate(3, clazz.getBeginDate());
            ps.setDate(4, clazz.getEndDate());
            ps.setInt(5, clazz.getStudentCount());

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("添加班级信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }
    public int delete(int clazzId){
        Connection conn = null;
        PreparedStatement ps = null;

        String strSql = "DELETE FROM Clazzes WHERE ClazzId = ?";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setInt(1, clazzId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("删除班级信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }
    public int delete(ClazzBean clazz){
        return delete(clazz.getClazzId());
    }

    public int update(ClazzBean clazz){
        Connection conn = null;
        PreparedStatement ps = null;
        String strSql = "UPDATE Clazzes " +
                        "SET Number=?,Name=?,BeginDate=?,EndDate=?,StudentCount=? " +
                        "WHERE ClazzId=?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            int count = 1;
            ps.setString(count++, clazz.getNumber());
            ps.setString(count++, clazz.getName());
            ps.setDate(count++, clazz.getBeginDate());
            ps.setDate(count++, clazz.getEndDate());
            ps.setInt(count++, clazz.getStudentCount());
            ps.setInt(count++, clazz.getClazzId());
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("修改班级信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    public int update(ClazzBean clazz, int oldClazzId){
        Connection conn = null;
        PreparedStatement ps = null;

        String strSql = "UPDATE Clazzes SET ClazzId=?,Number=?,Name=?,BeginDate=?,EndDate=?,StudentCount=? WHERE ClazzId=?";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setInt(1,clazz.getClazzId());
            ps.setString(2, clazz.getNumber());
            ps.setString(3, clazz.getName());
            ps.setDate(4, clazz.getBeginDate());
            ps.setDate(5, clazz.getEndDate());
            ps.setInt(6, clazz.getStudentCount());
            ps.setInt(7,oldClazzId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("修改班级信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    public List<ClazzBean> select(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM Clazzes";
        List<ClazzBean> clazzes = new ArrayList<ClazzBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            rs = ps.executeQuery();
            while (rs.next()){
                ClazzBean clazz = new ClazzBean();
                int count = 1;
                clazz.setClazzId(rs.getInt(count++));
                clazz.setNumber(rs.getString(count++));
                clazz.setName(rs.getString(count++));
                clazz.setBeginDate(rs.getDate(count++));
                clazz.setEndDate(rs.getDate(count++));
                clazz.setStudentCount(rs.getInt(count++));

                clazzes.add(clazz);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询班级信息出错！");
        }finally {
            MyConnection.close(ps,conn);
        }
        return clazzes;
    }

    public List<ClazzBean> select(String searchNumber, String searchName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM Clazzes WHERE Number LIKE ? AND Name LIKE ?";
        List<ClazzBean> clazzes = new ArrayList<ClazzBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setString(1,"%" + searchNumber + "%");
            ps.setString(2,"%" + searchName + "%");
            rs = ps.executeQuery();
            while (rs.next()){
                ClazzBean clazz = new ClazzBean();
                int count = 1;
                clazz.setClazzId(rs.getInt(count++));
                clazz.setNumber(rs.getString(count++));
                clazz.setName(rs.getString(count++));
                clazz.setBeginDate(rs.getDate(count++));
                clazz.setEndDate(rs.getDate(count++));
                clazz.setStudentCount(rs.getInt(count++));
                clazzes.add(clazz);
                //return clazzes;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询班级信息出错！");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return clazzes;
    }

    public ClazzBean select(int clazzId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM Clazzes WHERE ClazzId = ?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setInt(1,clazzId);
            rs = ps.executeQuery();
            while (rs.next()){
                ClazzBean clazz = new ClazzBean();
                int count = 1;
                clazz.setClazzId(rs.getInt(count++));
                clazz.setNumber(rs.getString(count++));
                clazz.setName(rs.getString(count++));
                clazz.setBeginDate(rs.getDate(count++));
                clazz.setEndDate(rs.getDate(count++));
                clazz.setStudentCount(rs.getInt(count++));

                return clazz;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("获取班级信息出错！");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return null;
    }

    @Override
    public List<ClazzBean> select(String searchNumber, String searchName, String fieldName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM Clazzes WHERE Number LIKE ? AND Name LIKE ? ORDER BY " + fieldName;
        List<ClazzBean> clazzes = new ArrayList<ClazzBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setString(1,"%" + searchNumber + "%");
            ps.setString(2,"%" + searchName + "%");
            rs = ps.executeQuery();
            while (rs.next()){
                ClazzBean clazz = new ClazzBean();
                int count = 1;
                clazz.setClazzId(rs.getInt(count++));
                clazz.setNumber(rs.getString(count++));
                clazz.setName(rs.getString(count++));
                clazz.setBeginDate(rs.getDate(count++));
                clazz.setEndDate(rs.getDate(count++));
                clazz.setStudentCount(rs.getInt(count++));
                clazzes.add(clazz);
                //return clazzes;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询班级信息出错！");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return clazzes;
    }

}
