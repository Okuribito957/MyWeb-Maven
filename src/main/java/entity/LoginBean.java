package entity;

import lombok.*;

import java.sql.Timestamp;

@Data
public class LoginBean {
    private int loginId = 0;
    private String loginName = "";
    private String password = "";
    private String email = "";
    private Timestamp registerDateTime = null;
    private String registerIp = "";
    private Timestamp lastLoginDateTime = null;
    private String lastLoginIp = "";
    private int loginTime = 0;
    private String loginType = "";
}
