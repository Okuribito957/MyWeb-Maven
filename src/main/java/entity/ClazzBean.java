package entity;

import lombok.*;
import java.sql.Date;

@Data
public class ClazzBean {
    private int clazzId = 0;
    private String number = "";
    private String name = "";
    private Date beginDate = Date.valueOf("1946-10-01");
    private Date endDate = Date.valueOf("2049-10-01");
    private int studentCount = 0;

}
