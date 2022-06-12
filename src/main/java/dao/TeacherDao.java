package dao;

import entity.TeacherBean;

import java.util.List;

public interface TeacherDao {
    int insert(TeacherBean teacher);
    int delete(TeacherBean teacher);
    int delete(int teacherId);
    int update(TeacherBean teacher);
    List<TeacherBean> select();
    List<TeacherBean> select(String number,String name);
    TeacherBean select(int teacherId);
    List<TeacherBean> select(String txtSearchNumber,String txtSearchName,String hidFieldName);
}
