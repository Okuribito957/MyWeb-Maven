package entity;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentBean {
    private int studentId = 0;
    private String number = "";
    private String name = "";
    private String sex = "";
    private Date birthday = null;
    private String PhoneNumber = "";
    private String address = "";

    private int clazzId = 0;
    private ClazzBean clazz = null;
}
