package dao;

import entity.LoginBean;

import java.util.List;

public interface LoginDao {
    int insert(LoginBean login);
    int delete(LoginBean login);
    int delete(int loginId);
    int delete(String loginName);
    int update(LoginBean login);
    int update(int loginId);
    List<LoginBean> select();
    LoginBean select(int loginId);
    LoginBean select(String loginName);
}
