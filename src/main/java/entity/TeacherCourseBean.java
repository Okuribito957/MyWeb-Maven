package entity;

import lombok.Data;

import java.sql.Date;

@Data
public class TeacherCourseBean {
    private int teachercourseId = 0;

    private int teacherId = 0;
    private TeacherBean teacher = null;

    private int courseId = 0;
    private CourseBean course = null;

    private Date beginDate = null;
    private Date endDate = null;
    private Date examDate = null;
    private String description = "";
}
