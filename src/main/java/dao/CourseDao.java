package dao;

import entity.ClazzBean;
import entity.CourseBean;

import java.util.List;

public interface CourseDao {
    public int insert(CourseBean course);
    public int delete(CourseBean course);
    public int delete(int courseId);
    public List<CourseBean> select();
    public CourseBean select(int courseId);
    public int update(CourseBean course);
    public int update(CourseBean course,int oldCourseId);
    List<CourseBean> select(String searchNumber, String searchName, String fieldName);
}
