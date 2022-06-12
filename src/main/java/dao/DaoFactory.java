package dao;

import Impl.*;

public class DaoFactory {
    public static CourseDao getCourseDao() {
        return new CourseDaoImpl();
    }
    public static ClazzDao getClazzDao(){
        return new ClazzDaoImpl();
    }
    public static LoginDao getLoginDao(){
        return new LoginDaoImpl();
    }
    public static TeacherDao getTeacherDao(){
        return new TeacherDaoImpl();
    }
    public static StudentDao getStudentDao(){
        return new StudentDaoImpl();
    }
    public static StudyCourseDao getStudyCourseDao(){
        return new StudyCourseDaoImpl();
    }
    public static TeacherCourseDao getTeacherCourseDao(){
        return new TeacherCourseDaoImpl();
    }
}
