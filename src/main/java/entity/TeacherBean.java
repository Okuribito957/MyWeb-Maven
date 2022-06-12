package entity;

import lombok.*;

import java.sql.Date;

@Data
public class TeacherBean {
    private int teacherId = 0;
    private String number = "";
    private String name = "";
    private String sex = "";
    private Date birthday = null;
    private String phoneNumber = "";
    private String address = "";

    private int clazzId = 0;
    private ClazzBean clazz = null;
}
