package Impl;

import dao.DaoFactory;
import dao.MyConnection;
import dao.TeacherDao;
import entity.ClazzBean;
import entity.TeacherBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    @Override
    public int insert(TeacherBean teacher) {
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;

        String strSql = "INSERT INTO TEACHERS values(NULL,?,?,?,?,?,?,?)";
        try {
            conn = MyConnection.getConnection();
            int count = 1;
            ps = conn.prepareStatement(strSql);
            ps.setString(count++, teacher.getNumber());
            ps.setString(count++, teacher.getName());
            ps.setString(count++, teacher.getSex());
            ps.setDate(count++, teacher.getBirthday());
            ps.setString(count++, teacher.getPhoneNumber());
            ps.setString(count++, teacher.getAddress());
            if (teacher.getClazzId() == 0){
                ps.setNull(count++, Types.INTEGER);
            }else {
                ps.setInt(count++,teacher.getClazzId());
            }
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("添加教师信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    @Override
    public int delete(TeacherBean teacher) {
        return 0;
    }

    @Override
    public int delete(int teacherId) {
        Connection conn = null;
        PreparedStatement ps = null;

        String strSql = "DELETE FROM TEACHERS WHERE TEACHERID = ?";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setInt(1, teacherId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("删除教师信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    @Override
    public int update(TeacherBean teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        String strSql = "UPDATE teachers " +
                "SET Number=?,Name=?,Sex=?,Birthday=?,PhoneNumber=?,Address=?,ClazzId=? " +
                "WHERE TeacherId=?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            int count = 1;
            ps.setString(count++, teacher.getNumber());
            ps.setString(count++, teacher.getName());
            ps.setString(count++, teacher.getSex());
            ps.setDate(count++, teacher.getBirthday());
            ps.setString(count++, teacher.getPhoneNumber());
            ps.setString(count++, teacher.getAddress());
            if (teacher.getClazzId() == 0){
                ps.setNull(count++, Types.INTEGER);
            }else {
                ps.setInt(count++,teacher.getClazzId());
            }
            ps.setInt(count++,teacher.getTeacherId());
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("修改教师信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    @Override
    public List<TeacherBean> select() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM TeacherS";
        List<TeacherBean> teachers = new ArrayList<TeacherBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            rs = ps.executeQuery();
            while (rs.next()){
                TeacherBean teacher = new TeacherBean();
                int count = 1;
                teacher.setTeacherId(rs.getInt(count++));
                teacher.setNumber(rs.getString(count++));
                teacher.setName(rs.getString(count++));
                teacher.setSex(rs.getString(count++));
                teacher.setBirthday(rs.getDate(count++));
                teacher.setPhoneNumber(rs.getString(count++));
                teacher.setAddress(rs.getString(count++));
                int clazzId = rs.getInt(count++);
                if (rs.wasNull()){
                    teacher.setClazzId(0);
                }else {
                    teacher.setClazzId(clazzId);
                    teacher.setClazz(DaoFactory.getClazzDao().select(clazzId));
                }
                teachers.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询教师信息出错！");
        }finally {
            MyConnection.close(ps,conn);
        }
        return teachers;
    }

    private TeacherBean getTeacher(ResultSet rs) throws SQLException{
        TeacherBean teacher = new TeacherBean();
        int count = 1;
        teacher.setTeacherId(rs.getInt(count++));
        teacher.setNumber(rs.getString(count++));
        teacher.setName(rs.getString(count++));
        teacher.setSex(rs.getString(count++));
        teacher.setBirthday(rs.getDate(count++));
        teacher.setPhoneNumber(rs.getString(count++));
        teacher.setAddress(rs.getString(count++));
        int clazzId = rs.getInt(count++);
        if (rs.wasNull()){
            teacher.setClazzId(0);
        }else {
            teacher.setClazzId(clazzId);
            teacher.setClazz(DaoFactory.getClazzDao().select(clazzId));
        }
        return teacher;
    }

    @Override
    public List<TeacherBean> select(String number, String name) {
        return null;
    }

    @Override
    public TeacherBean select(int teacherId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM TeacherS WHERE TeacherId = ?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setInt(1,teacherId);
            rs = ps.executeQuery();
            if (rs.next()){
                return getTeacher(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TeacherBean> select(String txtSearchNumber, String txtSearchName, String hidFieldName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM TeacherS " +
                        "WHERE Number LIKE ? AND Name LIKE ? " +
                        "ORDER BY " + hidFieldName;
        List<TeacherBean> teachers = new ArrayList<TeacherBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setString(1,"%" + txtSearchNumber + "%");
            ps.setString(2,"%" + txtSearchName + "%");
            rs = ps.executeQuery();
            while (rs.next()){
                teachers.add(getTeacher(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询教师信息出错");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return teachers;
    }
}
