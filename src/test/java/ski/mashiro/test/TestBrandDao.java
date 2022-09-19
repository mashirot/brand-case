package ski.mashiro.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import ski.mashiro.dao.BrandDao;
import ski.mashiro.pojo.Brand;
import ski.mashiro.util.SqlSessionFactoryUtils;

import java.util.List;

public class TestBrandDao {
    @Test
    public void testListBrands() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<Brand> brandList = sqlSession.getMapper(BrandDao.class).listBrandsByBrandName("阿里巴");
            System.out.println(brandList);
        }
    }
}
