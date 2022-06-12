package dao;

import entity.ClazzBean;
import java.util.List;

public interface ClazzDao {
    public int insert(ClazzBean clazz);
    public int delete(int clazzId);
    public int delete(ClazzBean clazz);
    public int update(ClazzBean clazz);
    public int update(ClazzBean clazz, int oldClazzId);
    public List<ClazzBean> select();
    public List<ClazzBean> select(String searchNumber,String searchName);
    public ClazzBean select(int clazzId);//根据主键查询获取对象
    public List<ClazzBean> select(String searchNumber,String searchName,String fieldName);
}
