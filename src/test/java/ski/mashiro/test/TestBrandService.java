package ski.mashiro.test;

import org.junit.Test;
import ski.mashiro.pojo.Brand;
import ski.mashiro.service.BrandService;

import java.lang.reflect.Method;
import java.util.List;

public class TestBrandService {
    @Test
    public void testListBrandsByBrandName() {
        BrandService brandService = new BrandService();
        List<Brand> brandList = brandService.listBrandsByBrandName("阿里巴");
        System.out.println(brandList);
    }
    @Test
    public void testSaveBrand() throws Exception {
        Class<BrandService> brandServiceClass = BrandService.class;
        BrandService brandService = brandServiceClass.getConstructor().newInstance();
        Method saveBrand = brandServiceClass.getDeclaredMethod("saveBrand", Brand.class);
        Object rs = saveBrand.invoke(brandService, new Brand("魅族", "魅族科技", 1000, "精致", 1));
        System.out.println(rs);
    }
}
