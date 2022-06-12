package Impl;

import dao.CourseDao;
import dao.MyConnection;
import entity.ClazzBean;
import entity.CourseBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseDaoImpl implements CourseDao {
    public int insert(CourseBean course){
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;

        String strSql = "INSERT INTO COURSES values(NULL,?,?,?,?,?,?)";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setString(1, course.getNumber());
            ps.setString(2, course.getName());
            ps.setInt(3, course.getTerm());
            ps.setFloat(4, course.getCredit());
            ps.setInt(5, course.getHourse());
            ps.setString(6,course.getDescription());

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("添加课程信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }
    public int delete(CourseBean course){
        return 0;
    }
    public int delete(int courseId){
        Connection conn = null;
        PreparedStatement ps = null;

        String strSql = "DELETE FROM COURSES WHERE CourseId = ?";
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strSql);
            ps.setInt(1, courseId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("删除课程信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }
    public int update(CourseBean course){
        Connection conn = null;
        PreparedStatement ps = null;
        String strSql = "UPDATE COURSES " +
                "SET Number=?,Name=?,Term=?,Credit=?,Hourse=?,Description=? " +
                "WHERE CourseId=?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            int count = 1;
            ps.setString(1, course.getNumber());
            ps.setString(2, course.getName());
            ps.setInt(3, course.getTerm());
            ps.setFloat(4, course.getCredit());
            ps.setInt(5, course.getHourse());
            ps.setString(6,course.getDescription());
            ps.setInt(7,course.getCourseId());
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("修改课程信息时出错");
        }finally {
            MyConnection.close(ps,conn);
        }
        return 0;
    }
    public int update(CourseBean course,int oldCourseId){
        return 0;
    }

    @Override
    public List<CourseBean> select(String searchNumber, String searchName, String fieldName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM Courses WHERE Number LIKE ? AND Name LIKE ? ORDER BY " + fieldName;
        List<CourseBean> courses = new ArrayList<CourseBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setString(1,"%" + searchNumber + "%");
            ps.setString(2,"%" + searchName + "%");
            rs = ps.executeQuery();
            while (rs.next()){
                CourseBean course = new CourseBean();
                int count = 1;
                course.setCourseId(rs.getInt(count++));
                course.setNumber(rs.getString(count++));
                course.setName(rs.getString(count++));
                course.setTerm(rs.getInt(count++));
                course.setCredit(rs.getFloat(count++));
                course.setHourse(rs.getInt(count++));
                course.setDescription(rs.getString(count++));
                courses.add(course);
                //return clazzes;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询课程信息出错！");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return courses;
    }

    public List<CourseBean> select(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null ;
        String strSql = "SELECT * FROM COURSES";
        List<CourseBean> courses = new ArrayList<CourseBean>();
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            rs = ps.executeQuery();
            while (rs.next()){
                CourseBean course = new CourseBean();
                int count = 1;
                course.setCourseId(rs.getInt(count++));
                course.setNumber(rs.getString(count++));
                course.setName(rs.getString(count++));
                course.setTerm(rs.getInt(count++));
                course.setCredit(rs.getFloat(count++));
                course.setHourse(rs.getInt(count++));
                course.setDescription(rs.getString(count++));

                courses.add(course);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("查询课程信息出错！");
        }finally {
            MyConnection.close(ps,conn);
        }
        return courses;
    }
    public CourseBean select(int courseId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSql = "SELECT * FROM COURSES WHERE CourseId = ?";
        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strSql);
            ps.setInt(1,courseId);
            rs = ps.executeQuery();
            while (rs.next()){
                CourseBean course = new CourseBean();
                int count = 1;
                course.setCourseId(rs.getInt(count++));
                course.setNumber(rs.getString(count++));
                course.setName(rs.getString(count++));
                course.setTerm(rs.getInt(count++));
                course.setCredit(rs.getFloat(count++));
                course.setHourse(rs.getInt(count++));
                course.setDescription(rs.getString(count++));

                return course;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("获取课程信息出错！");
        }finally {
            MyConnection.close(rs,ps,conn);
        }
        return null;
    }
}
