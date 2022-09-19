package ski.mashiro.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ski.mashiro.dao.UserDao;
import ski.mashiro.pojo.User;
import ski.mashiro.util.SqlSessionFactoryUtils;

import java.util.List;

/**
 * @author FeczIne
 */
public class UserService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public boolean saveUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int rs = sqlSession.getMapper(UserDao.class).saveUser(user);
            if (rs != 0) {
                sqlSession.commit();
            }
            return rs != 0;
        }
    }

    public boolean getUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            User rsUser = sqlSession.getMapper(UserDao.class).getUser(user);
            return rsUser.getId() != null;
        }
    }

    public boolean deleteUserById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            if (sqlSession.getMapper(UserDao.class).deleteUserById(id) != 0) {
                sqlSession.commit();
                return true;
            }
            return false;
        }
    }

    public List<User> listAllUser() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(UserDao.class).listAllUser();
        }
    }

}
