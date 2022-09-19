package ski.mashiro.dao;

import org.apache.ibatis.annotations.Param;
import ski.mashiro.pojo.Brand;

import java.util.List;

/**
 * @author FeczIne
 */
public interface BrandDao {
    /**
     * 添加品牌
     * @param brand 新品牌对象
     * @return 返回品牌对象
     */
    int saveBrand(@Param("brand") Brand brand);
    /**
     * 获取所有品牌集合
     * @return 返回集合
     */
    List<Brand> listAllBrands();

    /**
     * 通过id获取品牌集合
     * @param id 条件
     * @return 返回集合
     */
    Brand getBrandById(@Param("id") int id);

    /**
     * 通过品牌名获取品牌集合
     * @param brandName 条件
     * @return 返回集合
     */
    List<Brand> listBrandsByBrandName(@Param("brandName") String brandName);

    /**
     * 通过id删除品牌
     * @param id 入参
     * @return 返回受影响行数
     */
    int deleteBrandById(@Param("id") int id);
}
