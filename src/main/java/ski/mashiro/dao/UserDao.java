package ski.mashiro.dao;

import org.apache.ibatis.annotations.Param;
import ski.mashiro.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询用户是否存在
     * @param user 需验证用户
     * @return 返回用户对象
     */
    User getUser(@Param("user") User user);
    /**
     * 添加新用户
     * @param user 新用户对象
     * @return 返回添加的用户对象
     */
    int saveUser(@Param("user") User user);
    /**
     * 获取所有用户
     * @return 返回所有用户集合
     */
    List<User> listAllUser();

    /**
     * 删除指定id的用户
     * @param id 入参
     * @return 返回影响的行数
     */
    int deleteUserById(@Param("id") int id);
}
