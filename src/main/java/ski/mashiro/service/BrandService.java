package ski.mashiro.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ski.mashiro.dao.BrandDao;
import ski.mashiro.pojo.Brand;
import ski.mashiro.util.SqlSessionFactoryUtils;

import java.util.List;

/**
 * @author FeczIne
 */
public class BrandService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public boolean saveBrand(Brand brand) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int rs = sqlSession.getMapper(BrandDao.class).saveBrand(brand);
            if (rs != 0) {
                sqlSession.commit();
            }
            return rs != 0 && brand.getId() != 0;
        }
    }

    public boolean deleteBrandById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int rs = sqlSession.getMapper(BrandDao.class).deleteBrandById(id);
            if (rs != 0) {
                sqlSession.commit();
                return true;
            }
            return false;
        }
    }

    public List<Brand> listAllBrands() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(BrandDao.class).listAllBrands();
        }
    }

    public Brand getBrandById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(BrandDao.class).getBrandById(id);
        }
    }

    public List<Brand> listBrandsByBrandName(String brandName) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(BrandDao.class).listBrandsByBrandName(brandName);
        }
    }
}
