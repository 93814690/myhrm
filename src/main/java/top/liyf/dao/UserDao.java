package top.liyf.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.liyf.domain.User;

/**
 * @version V1.0
 * @Description: UserMapper接口
 */
public interface UserDao {

    // 根据登录名和密码查询员工
    User selectByLoginnameAndPassword(HashMap map);

    // 根据id查询用户
    User selectById(Integer id);

    // 根据id删除用户
    void deleteById(Integer id);

    // 动态修改用户
    void update(User user);

    // 动态查询
    List<User> selectByPage(Map<String, Object> params);

    // 根据参数查询用户总数
    Integer count(Map<String, Object> params);

    // 动态插入用户
    void save(User user);

}
