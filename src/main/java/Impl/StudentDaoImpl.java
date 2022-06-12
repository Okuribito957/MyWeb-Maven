package Impl;

import dao.DaoFactory;
import dao.MyConnection;
import dao.StudentDao;
import entity.StudentBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    public int insert(StudentBean student) {
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;

        String strSql = "INSERT INTO STUDENTS values(NULL,?,?,?,?,?,?,?)";
        try {
            conn = MyConnection.getConnection();
            int count = 1;
            ps = conn.prepareStatement(strSql);
            ps.setString(count++, student.getNumber());
            ps.setString(count++, student.getName());
            ps.setString(count++, student.getSex());
            ps.setDate(count++, student.getBirthday());
            ps.setString(count++, student.getPhoneNumber());
            ps.setString(count++, student.getAddress());
            if (student.getClazzId() == 0){
                ps.setNull(count++, Types.INTEGER);
            }else {
                ps.setInt(count++,student.getClazzId());
            }
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("添加学生信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    @Override
    public int delete(StudentBean student) {
        return 0;
    }

    @Override
    public int delete(int studentId) {
        Connection conn = null;
        PreparedStatement ps = null;

        String strSql = "DELETE FROM STUDENTS WHERE STUDENTID = ?";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setInt(1, studentId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("删除学生信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    @Override
    public int update(StudentBean student) {
        Connection conn = null;
        PreparedStatement ps = null;
        String strSql = "UPDATE STUDENTS " +
                "SET Number=?,Name=?,Sex=?,Birthday=?,PhoneNumber=?,Address=?,ClazzId=? " +
                "WHERE STUDENTID=?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            int count = 1;
            ps.setString(count++, student.getNumber());
            ps.setString(count++, student.getName());
            ps.setString(count++, student.getSex());
            ps.setDate(count++, student.getBirthday());
            ps.setString(count++, student.getPhoneNumber());
            ps.setString(count++, student.getAddress());
            if (student.getClazzId() == 0){
                ps.setNull(count++, Types.INTEGER);
            }else {
                ps.setInt(count++,student.getClazzId());
            }
            ps.setInt(count++,student.getStudentId());
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("修改学生信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }

    @Override
    public List<StudentBean> select() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM STUDENTS";
        List<StudentBean> students = new ArrayList<StudentBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            rs = ps.executeQuery();
            while (rs.next()){
                StudentBean student = new StudentBean();
                int count = 1;
                student.setStudentId(rs.getInt(count++));
                student.setNumber(rs.getString(count++));
                student.setName(rs.getString(count++));
                student.setSex(rs.getString(count++));
                student.setBirthday(rs.getDate(count++));
                student.setPhoneNumber(rs.getString(count++));
                student.setAddress(rs.getString(count++));
                int clazzId = rs.getInt(count++);
                if (rs.wasNull()){
                    student.setClazzId(0);
                }else {
                    student.setClazzId(clazzId);
                    student.setClazz(DaoFactory.getClazzDao().select(clazzId));
                }
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询学生信息出错！");
        }finally {
            MyConnection.close(ps,conn);
        }
        return students;
    }

    private StudentBean getStudent(ResultSet rs) throws SQLException{
        StudentBean student = new StudentBean();
        int count = 1;
        student.setStudentId(rs.getInt(count++));
        student.setNumber(rs.getString(count++));
        student.setName(rs.getString(count++));
        student.setSex(rs.getString(count++));
        student.setBirthday(rs.getDate(count++));
        student.setPhoneNumber(rs.getString(count++));
        student.setAddress(rs.getString(count++));
        int clazzId = rs.getInt(count++);
        if (rs.wasNull()){
            student.setClazzId(0);
        }else {
            student.setClazzId(clazzId);
            student.setClazz(DaoFactory.getClazzDao().select(clazzId));
        }
        return student;
    }

    @Override
    public List<StudentBean> select(String number, String name) {
        return null;
    }

    @Override
    public StudentBean select(int studentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM STUDENTS WHERE STUDENTID = ?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setInt(1,studentId);
            rs = ps.executeQuery();
            if (rs.next()){
                return getStudent(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentBean> select(String txtSearchNumber, String txtSearchName, String hidFieldName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM STUDENTS " +
                "WHERE Number LIKE ? AND Name LIKE ? " +
                "ORDER BY " + hidFieldName;
        List<StudentBean> students = new ArrayList<StudentBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setString(1,"%" + txtSearchNumber + "%");
            ps.setString(2,"%" + txtSearchName + "%");
            rs = ps.executeQuery();
            while (rs.next()){
                students.add(getStudent(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询学生信息出错");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return students;
    }
}
