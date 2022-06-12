package dao;

import entity.StudentBean;

import java.util.List;

public interface StudentDao {
    int insert(StudentBean student);
    int delete(StudentBean student);
    int delete(int studentId);
    int update(StudentBean student);
    List<StudentBean> select();
    List<StudentBean> select(String number,String name);
    StudentBean select(int studentId);
    List<StudentBean> select(String txtSearchNumber,String txtSearchName,String hidFieldName);
}
