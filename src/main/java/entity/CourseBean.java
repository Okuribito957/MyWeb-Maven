package entity;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseBean {
    private int courseId = 0;
    private String number = "";
    private String name = "";
    private int term = 0;
    private float credit = 0;
    private int hourse = 0;
    private String description = "";

}
