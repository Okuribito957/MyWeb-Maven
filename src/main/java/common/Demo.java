package common;

import dao.DaoFactory;
import entity.CourseBean;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CourseBean course = new CourseBean(6,"12188","域和伽罗瓦理论",
                1,1,0,"世界图书出版公司");
        if (DaoFactory.getCourseDao().insert(course) > 0){
            System.out.println("添加成功！");
        }
        if (DaoFactory.getCourseDao().delete(1) > 0){
            System.out.println("删除成功！");
        }
        course = new CourseBean(1,"12100","泛函分析",
                1,1,0,"世界图书出版公司");
        if (DaoFactory.getCourseDao().update(course) > 0 ){
            System.out.println("修改成功");
        }
        List<CourseBean> courses = DaoFactory.getCourseDao().select();
        for (CourseBean item : courses){
            System.out.println(item);
        }
    }
}
